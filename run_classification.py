from typing import Literal, Tuple
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score


from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC

import torch
import pandas as pd
import numpy as np
import os

df = pd.read_csv("./metadata.csv")
df = df[["file_name", "complexity"]]
df["codebert_embeddings_path"] = df["file_name"].apply(lambda x: f"./embeddings-codebert/{x}.pth")
df["bert_embeddings_path"] = df["file_name"].apply(lambda x: f"./embeddings-bert/{x}.pth")

# make sure that the matching embeddings file exists
# for each items in df, if codebert_embeddings_path doesn't exist, delete it.
# for each items in df, if bert_embeddings_path doesn't exist, delete it.
df = df[df["codebert_embeddings_path"].apply(lambda x: os.path.exists(x))]
df = df[df["bert_embeddings_path"].apply(lambda x: os.path.exists(x))]

# load complexity labels
labels: Tuple[str] = tuple(df["complexity"])  # type: ignore

# load codebert embeddings
codebert_embeddings: Tuple[torch.Tensor] = tuple(df["codebert_embeddings_path"].apply(lambda x: torch.load(x)))  # type: ignore

# load bert embeddings
bert_embeddings: Tuple[torch.Tensor] = tuple(df["bert_embeddings_path"].apply(lambda x: torch.load(x)))  # type: ignore


def classify(model: Literal["bert", "codebert"], embeddings: Tuple[torch.Tensor], labels: Tuple[str]) -> None:
    max_seq_length = max([emb.shape[1] for emb in embeddings])
    # Pad each embedding to max_seq_length and then flatten
    X = np.array(
        [
            torch.nn.functional.pad(emb, pad=(0, 0, 0, max_seq_length - emb.shape[1]))
                .squeeze(0)
                .reshape(-1)
                .detach()
                .cpu()
                .numpy()
            for emb in embeddings
        ]
    )
    label_encoder = LabelEncoder()
    # Determine maximum sequence length in the dataset
    y = label_encoder.fit_transform(labels)
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=16
    )

    # Train a Random Forest classifier
    clf = RandomForestClassifier(n_estimators=200, random_state=21)  # Using 100 trees
    clf.fit(X_train, y_train)

    # Predict on the test set
    y_pred = clf.predict(X_test)

    # Calculate and print accuracy
    accuracy = accuracy_score(y_test, y_pred)
    print(f"Random Forest Accuracy ({model}): {accuracy * 100:.2f}%")

    # SVC classifier test
    clf = SVC()
    clf.fit(X_train, y_train)

    # Predict on the test set
    y_pred = clf.predict(X_test)

    # Calculate and print accuracy
    accuracy = accuracy_score(y_test, y_pred)
    print(f"SVC Classifier Accuracy ({model}): {accuracy * 100:.2f}%")

if __name__ == "__main__":
    classify("bert", bert_embeddings, labels)
    classify("codebert", codebert_embeddings, labels)
