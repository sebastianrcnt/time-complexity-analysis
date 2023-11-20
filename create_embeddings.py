import torch
import pandas as pd
import os
from tqdm import tqdm
import torch
import argparse

from logger import logger


from utils import (
    count_tokens,
    create_embeddings_with_model,
    load_bert_model,
    load_codebert_model,
)


def create_embeddings(
    tokenizer,
    model,
    embeddings_folder: str,
    skip_existing: bool = False,
    skip_over_512: bool = False,
):
    if not os.path.exists(embeddings_folder):
        logger.info(f"since {embeddings_folder} doesn't exist, creating it")
        os.makedirs(embeddings_folder)

    # load labels
    df = pd.read_csv("./labels.csv")

    for index, row in tqdm(df.iterrows(), total=len(df)):
        file_name, complexity, language = (
            row["file_name"],
            row["complexity"],
            row["language"],
        )

        assert os.path.exists(file_name), f"source code file {file_name} doesn't exist"

        embedding_path = f"{embeddings_folder}/{file_name.replace('/', '__')}.pth"

        if skip_existing:
            if os.path.exists(embedding_path):
                logger.debug(f"skipping existing file {file_name}")
                continue

        logger.debug(f"start creating embedding for file: {file_name}")

        with open(file_name, "r", encoding="utf-8") as f:
            code = "".join(f.readlines())
            token_length = count_tokens(code, tokenizer)
            logger.debug(f"token length: {token_length}")

            if skip_over_512:
                if token_length > 512:
                    logger.warning(f"token length is greater than 512. skipping...")
                    continue

        embedding = create_embeddings_with_model(code, model, tokenizer)
        logger.debug(f"saving embeddings to: {embedding_path}")
        torch.save(embedding, embedding_path)

    logger.success(f"Processed {len(df)} files")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="""Create embeddings of source code files specified in 'labels.csv'""")

    parser.add_argument(
        "--skip-existing", action="store_true", help="Skip existing embeddings"
    )
    parser.add_argument(
        "--model",
        choices=["bert", "codebert"],
        default="bert",
        help="Which model to use for embedding",
    )
    parser.add_argument(
        "--skip-over-512",
        action="store_true",
        help="Skip files with token length over 512",
    )
    parser.add_argument(
        "--embeddings-folder", default="./embeddings/", help="Folder to save embeddings"
    )

    args = parser.parse_args()
    embeddings_folder = os.path.join(args.embeddings_folder, args.model)

    if args.model == "bert":
        tokenizer, model = load_bert_model()
        create_embeddings(
            tokenizer, model, embeddings_folder, args.skip_existing, args.skip_over_512
        )
    elif args.model == "codebert":
        tokenizer, model = load_codebert_model()
        create_embeddings(
            tokenizer, model, embeddings_folder, args.skip_existing, args.skip_over_512
        )
    else:
        raise Exception(f"Invalid model. model should be in ['bert', 'codebert']")
