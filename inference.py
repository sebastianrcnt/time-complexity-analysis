from transformers import AutoModelForCausalLM, AutoTokenizer
import os
import torch

device = "cuda"  # the device to load the model onto

model = AutoModelForCausalLM.from_pretrained(
    "mistralai/Mistral-7B-Instruct-v0.1",
    torch_dtype=torch.float16,
    low_cpu_mem_usage=True,
    use_flash_attention_2=True,
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

Options:
    0. O(1)
    1. O(logn)
    2. O(n)
    3. O(nlogn)
    4. O(n^2)

Given Code:
    {code}

[choice]"""



def do_generate(prompt: str) -> str:
    model_inputs = tokenizer([prompt], return_tensors="pt").input_ids.to(device)
    model.to(device)
    generated_ids = model.generate(model_inputs, max_new_tokens=500, do_sample=True, pad_token_id=tokenizer.eos_token_id)
    output = tokenizer.batch_decode(generated_ids)[0]
    return str(output)

import pandas as pd

df = pd.read_csv("./labels.csv")

total_count = len(df)

print(f"total count: {total_count}")

for index, row in df.iterrows():
    print(f"iteration {index}/{total_count}")
    inference_file_name = row["file_name"].replace("/", "__")
    inference_file_path = f"./inference/{inference_file_name}.txt"

    if not os.path.exists(inference_file_path):

        with open(row["file_name"], "r", encoding="utf-8") as f:
            code = "".join(f.readlines())
            prompt = prompt_template(code)
            output = do_generate(prompt)
            
            # parse the second <choice> tag

            print(f"file: {row['file_name']}, complexity: {row['complexity']}")

            # write to file
            with open(inference_file_path, "w", encoding="utf-8") as f:
                f.write(output)