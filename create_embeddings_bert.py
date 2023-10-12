import torch
from transformers import BertTokenizer, BertModel
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
tokenizer = BertTokenizer.from_pretrained("bert-base-cased")
model: BertModel = BertModel.from_pretrained("bert-base-cased") # type: ignore
model.to(device) # type: ignore


MAX_LEN = 512  # This value might vary depending on the model; please check the documentation
OVERLAP = 50
OVERWRITE_CACHE = True
EMBEDDINGS_FOLDER = "./embeddings-bert"
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

    # Handle the chunks
    start = 0
    embeddings: List[torch.Tensor] = []

    while start < len(code_tokens_ids):
        end = start + MAX_LEN
        chunk_tokens = code_tokens_ids[start:end]

    # Extract only the last_hidden_state from the model's output
        input_tensor = torch.tensor(chunk_tokens)[None, :].to(device) # type: ignore
        chunk_embedding: torch.Tensor = model(input_tensor).last_hidden_state
        del input_tensor
        embeddings.append(chunk_embedding)

        start = end - OVERLAP

    # Determine the maximum sequence length in embeddings
    max_len: int = max([embedding.size(1) for embedding in embeddings])

    # Pad all embeddings to max_len
    padded_embeddings: List[torch.Tensor] = [torch.nn.functional.pad(emb, pad=(0, 0, 0, max_len - emb.size(1))) for emb in embeddings]

    # Aggregate embeddings
    aggregated_embedding = torch.mean(torch.stack(padded_embeddings), dim=0)

    torch.save(aggregated_embedding, f"{EMBEDDINGS_FOLDER}/{filename}.pth")

print(f"Processed {total_file_cnt} files")

