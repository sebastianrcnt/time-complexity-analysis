import os
import re   
import pandas as pd
import logging

logging.basicConfig(level=logging.DEBUG, format="%(asctime)s %(levelname)s %(message)s")
df = pd.read_csv("./labels.csv")
df['mistral_baseline_predictions'] = [None] * len(df)

total_count = len(df)

logging.info(f"starting inference of codes. total count is {total_count}")

res = {
    'right': 0,
    'wrong': 0,
    'none': 0,
}

for index, row in df.iterrows():
    logging.debug(f"starting iteration {index}/{total_count}")
    inference_file_name = row["file_name"].replace("/", "__")
    inference_file_path = f"./inference/{inference_file_name}.txt"
    
    if row['language'] != 'cpp':
        logging.debug(f"skipping file {row['file_name']}, language is {row['language']}")
        continue

    if os.path.exists(inference_file_path):
        with open(inference_file_path, "r", encoding="utf-8") as f:
            code = "".join(f.readlines())
            choices = re.findall(r'\[choice\](.*?)\[/choice\]', code)
            if len(choices) < 3:
                logging.error(f"choices length less than 3, file name: {row['file_name']}")
                res['none'] += 1
            else:
                logging.debug(f"inferenced complexity {choices[2]} for file {row['file_name']}, actual is {row['complexity']}")
                choice = choices[2][0]
                df.loc[index, 'mistral_baseline_predictions'] = choice

                if str(row['complexity']) == choice:
                    res['right'] += 1
                else:
                    res['wrong'] += 1

            logging.debug(f"file: {row['file_name']}, complexity: {row['complexity']}")
    
logging.info(f"total: {total_count}, right: {res['right']}, wrong: {res['wrong']}, none: {res['none']}")
print(df)