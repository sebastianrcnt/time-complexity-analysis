import torch
from transformers import AutoTokenizer, LlamaModel
import glob
import os
from typing import List
from tqdm import tqdm
import torch

type_ = 'cpu'
if torch.backends.mps.is_available():
    print(f"mps is available. using mps...")
    type_ = 'mps'
else:
    print("mps is not available")

device = torch.device(type_)
tokenizer = AutoTokenizer.from_pretrained("codellama/CodeLlama-7b-hf")
model: LlamaModel = LlamaModel.from_pretrained("codellama/CodeLlama-7b-hf") # type: ignore
model.to(device) # type: ignore


OVERWRITE_CACHE = True
EMBEDDINGS_FOLDER = "./embeddings-codellama"
DATASET_FOLDER = "./dataset"

os.makedirs(EMBEDDINGS_FOLDER, exist_ok=True)
java_files = glob.glob(f"{DATASET_FOLDER}/*.java")
skipped_file_cnt: int = 0
total_file_cnt = len(java_files)

for java_file in tqdm(java_files):
    filename = os.path.basename(java_file)

    if not OVERWRITE_CACHE:
        if os.path.exists(f"{EMBEDDINGS_FOLDER}/{filename}.pth"):
            continue

    with open(java_file, "r") as f:
        code = ''.join(f.readlines())
        code_tokens = tokenizer.tokenize(code)
        code_tokens_ids: List[int] = tokenizer.convert_tokens_to_ids(code_tokens) # type: ignore

    embedding = model(torch.tensor(code_tokens_ids)[None, :].to(device)).last_hidden_state
    torch.save(embedding, f"{EMBEDDINGS_FOLDER}/{filename}.pth")
    del embedding

print(f"Processed {total_file_cnt} files")

