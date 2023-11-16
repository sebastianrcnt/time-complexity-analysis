import torch
import pandas as pd
from transformers import BertTokenizer, BertModel
import glob
import os
from typing import List
from tqdm import tqdm
import torch

import logging

# set logging level to debug
# only log to file
logging.basicConfig(filename='app.log', filemode='w', format='%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.DEBUG)

type_ = 'cpu'
if torch.backends.mps.is_available():
    print(f"mps is available. using mps...")
    type_ = 'mps'
elif torch.cuda.is_available():
    print(f"cuda is available. using cuda...")
    type_ = 'cuda'
else:
    print("mps is not available. fallback to cpu")

device = torch.device(type_)
tokenizer = BertTokenizer.from_pretrained("bert-base-cased")
model: BertModel = BertModel.from_pretrained("bert-base-cased") # type: ignore
model.to(device) # type: ignore

OVERLAP = 50
EMBEDDINGS_FOLDER = "./embeddings-bert"

# create embeddings folder if not exists
if not os.path.exists(EMBEDDINGS_FOLDER):
    os.makedirs(EMBEDDINGS_FOLDER)

# load labels
df = pd.read_csv("./labels.csv")

for index, row in tqdm(df.iterrows()):
    file_name, complexity, language = row['file_name'], row['complexity'], row['language']
    assert os.path.exists(file_name), f"{file_name} doesn't exist"

    logging.debug(f"start processing filename: {file_name}")

    with open(file_name, "r", encoding='utf-8') as f:
        code = ''.join(f.readlines())
        code_tokens = tokenizer.tokenize(code)
        code_tokens_ids: List[int] = tokenizer.convert_tokens_to_ids(code_tokens)
        logging.debug(f"token length: {len(code_tokens_ids)}")

    start = 0
    embeddings: List[torch.Tensor] = []

    while start < len(code_tokens_ids):
        end = start + 512
        chunk_tokens = code_tokens_ids[start:end]

        # Extract only the last_hidden_state from the model's output
        input_tensor = torch.tensor(chunk_tokens)[None, :].to(device)
        chunk_embedding: torch.Tensor = model(input_tensor).last_hidden_state
        del input_tensor
        embeddings.append(chunk_embedding)

        start = end - OVERLAP
    
    max_len = max([embedding.size(1) for embedding in embeddings])
    padded_embeddings: List[torch.Tensor] = [torch.nn.functional.pad(emb, pad=(0, 0, 0, max_len - emb.size(1))) for emb in embeddings]
    aggerated_embedding = torch.mean(torch.stack(padded_embeddings), dim=0)
    file_name_replaced = file_name.replace('/', '__')

    logging.debug(f"saving embeddings to: {file_name_replaced}")
    torch.save(aggerated_embedding, f"{EMBEDDINGS_FOLDER}/{file_name_replaced}.pth")

    
print(f"Processed {len(df)} files")
    


# for java_file in tqdm(java_files):
#     filename = os.path.basename(java_file)

#     if not OVERWRITE_CACHE:
#         if os.path.exists(f"{EMBEDDINGS_FOLDER}/{filename}.pth"):
#             continue

#     with open(java_file, "r") as f:
#         code = ''.join(f.readlines())
#         code_tokens = tokenizer.tokenize(code)
#         code_tokens_ids: List[int] = tokenizer.convert_tokens_to_ids(code_tokens) # type: ignore

#     # Handle the chunks
#     start = 0
#     embeddings: List[torch.Tensor] = []

#     while start < len(code_tokens_ids):
#         end = start + MAX_LEN
#         chunk_tokens = code_tokens_ids[start:end]

#     # Extract only the last_hidden_state from the model's output
#         input_tensor = torch.tensor(chunk_tokens)[None, :].to(device) # type: ignore
#         chunk_embedding: torch.Tensor = model(input_tensor).last_hidden_state
#         del input_tensor
#         embeddings.append(chunk_embedding)

#         start = end - OVERLAP

#     # Determine the maximum sequence length in embeddings
#     max_len: int = max([embedding.size(1) for embedding in embeddings])

#     # Pad all embeddings to max_len
#     padded_embeddings: List[torch.Tensor] = [torch.nn.functional.pad(emb, pad=(0, 0, 0, max_len - emb.size(1))) for emb in embeddings]

#     # Aggregate embeddings
#     aggregated_embedding = torch.mean(torch.stack(padded_embeddings), dim=0)

#     torch.save(aggregated_embedding, f"{EMBEDDINGS_FOLDER}/{filename}.pth")

# print(f"Processed {total_file_cnt} files")

