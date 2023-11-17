from transformers import AutoModelForCausalLM, AutoTokenizer
import os
import re   
import torch
import pandas as pd
import logging

logging.basicConfig(level=logging.DEBUG, format="%(asctime)s %(levelname)s %(message)s", filename="inference.log", filemode="w")

device = "cuda"  # the device to load the model onto

model = AutoModelForCausalLM.from_pretrained(
    "mistralai/Mistral-7B-Instruct-v0.1",
    torch_dtype=torch.float16,
    low_cpu_mem_usage=True,
    # use_flash_attention_2=True,
)

tokenizer = AutoTokenizer.from_pretrained("mistralai/Mistral-7B-Instruct-v0.1")

example_code = """
def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)
"""

def prompt_template(code: str) -> str:
    return f"""
Analyize the time complexity of the given code. Include your choice in the [choice][/choice] tag, and only include the number of the option. (e.g. [choice]1[/choice])


Given Code:
{code}

Options:
    0. O(1)
    1. O(logn)
    2. O(n)
    3. O(nlogn)
    4. O(n^2)

Among the Options above, the time complexity of the given code is [choice]"""



def do_generate(prompt: str) -> str:
    model_inputs = tokenizer([prompt], return_tensors="pt").input_ids.to(device)
    model.to(device)
    generated_ids = model.generate(model_inputs, max_new_tokens=500, do_sample=True, pad_token_id=tokenizer.eos_token_id)
    output = tokenizer.batch_decode(generated_ids)[0]
    return str(output)

df = pd.read_csv("./labels.csv")

total_count = len(df)

logging.info(f"starting inference of codes. total count is {total_count}")

for index, row in df.iterrows():
    logging.debug(f"starting iteration {index}/{total_count}")
    inference_file_name = row["file_name"].replace("/", "__")
    inference_file_path = f"./inference/{inference_file_name}.txt"

    if not os.path.exists(inference_file_path):

        with open(row["file_name"], "r", encoding="utf-8") as f:
            code = "".join(f.readlines())
            prompt = prompt_template(code)
            token_length = len(tokenizer.encode(prompt))

            logging.debug(f"token length: {token_length}")

            if token_length > 4096:
                logging.debug(f"token length too long, skipping... file name: {row['file_name']}")
                continue

            output = do_generate(prompt)
            # parse the second [choice][/choice] tag

            choices = re.findall(r'\[choice\](.*?)\[/choice\]', output)
            if len(choices) < 3:
                logging.error(f"choices length less than 3, file name: {row['file_name']}")
            else:
                logging.debug(f"inferenced complexity {choices[2]} for file {row['file_name']}, actual is {row['complexity']}")

            logging.debug(f"file: {row['file_name']}, complexity: {row['complexity']}")

            # write to file
            with open(inference_file_path, "w", encoding="utf-8") as f:
                f.write(output)