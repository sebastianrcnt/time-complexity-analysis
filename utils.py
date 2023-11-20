from typing import Tuple, List, Union
from transformers import RobertaTokenizer, RobertaModel, BertTokenizer, BertModel

import torch


def autoload_device():
    type_ = 'cpu'
    if torch.backends.mps.is_available():
        print(f"mps is available. using mps...")
        type_ = 'mps'
    elif torch.cuda.is_available():
        print(f"cuda is available. using cuda...")
        type_ = 'cuda'
    else:
        print("mps is not available. fallback to cpu")

    return torch.device(type_)

DEVICE = autoload_device()

def load_codebert_model() -> Tuple[RobertaTokenizer, RobertaModel]:
    tokenizer = RobertaTokenizer.from_pretrained("microsoft/codebert-base")
    model = RobertaModel.from_pretrained("microsoft/codebert-base")
    model.to(DEVICE) # type: ignore
    return tokenizer, model # type: ignore

def load_bert_model() -> Tuple[BertTokenizer, BertModel]:
    tokenizer = BertTokenizer.from_pretrained("bert-base-cased")
    model = BertModel.from_pretrained("bert-base-cased")
    model.to(DEVICE) # type: ignore
    return tokenizer, model # type: ignore

def create_embeddings_with_model(input_: str, model: BertModel, tokenizer: BertTokenizer, overlap: int = 50) -> List[torch.Tensor]:
    code_tokens = tokenizer.tokenize(input_)
    code_tokens_ids: List[int] = tokenizer.convert_tokens_to_ids(code_tokens) # type: ignore

    start = 0
    embeddings: List[torch.Tensor] = []

    while start < len(code_tokens_ids):
        end = start + 512
        chunk_tokens = code_tokens_ids[start:end]

        # Extract only the last_hidden_state from the model's output
        input_tensor = torch.tensor(chunk_tokens)[None, :].to(DEVICE)
        output = model(input_tensor)[0] # type: ignore
        embeddings.append(output)

        start = end - overlap

    return embeddings


def count_tokens(input_: str, tokenizer: Union[BertTokenizer, RobertaTokenizer]) -> int:
    code_tokens = tokenizer.tokenize(input_)
    code_tokens_ids: List[int] = tokenizer.convert_tokens_to_ids(code_tokens) # type: ignore
    return len(code_tokens_ids)
