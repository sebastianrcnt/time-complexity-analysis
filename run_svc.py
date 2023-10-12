from typing import Tuple
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score


from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split

import torch
import pandas as pd
import numpy as np
import os

EMBEDDINGS_FOLDER = "./embeddings-bert"
DATASET_FOLDER = "./dataset"

df = pd.read_csv("./metadata.csv")
df = df[['file_name', 'complexity']]
df['embeddings_path'] = df['file_name'].apply(lambda x: f"{EMBEDDINGS_FOLDER}/{x}.pth")


# for each items in df, if embeddings_path doesn't exist, delete it.
df = df[df['embeddings_path'].apply(lambda x: os.path.exists(x))]

# for each itesm in df, if file_name doesn't exist, delete it.
df = df[df['file_name'].apply(lambda x: os.path.exists(f"{DATASET_FOLDER}/{x}"))]

# set 'file_name' as index
df.set_index('file_name', inplace=True)

labels: Tuple[str] = tuple(df['complexity']) # type: ignore
embeddings: Tuple[torch.Tensor] = tuple(df['embeddings_path'].apply(lambda x: torch.load(x))) # type: ignore


label_encoder = LabelEncoder()
# Determine maximum sequence length in the dataset
max_seq_length = max([emb.shape[1] for emb in embeddings])

# Pad each embedding to max_seq_length and then flatten
X = np.array(
    [
        torch.nn.functional.pad(emb, pad=(0, 0, 0, max_seq_length - emb.shape[1]))\
            .squeeze(0).reshape(-1).detach().cpu().numpy()
        for emb in embeddings
    ]
)
y = label_encoder.fit_transform(labels)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=27)

clf = SVC(verbose=True)
clf.fit(X_train, y_train)

# Predict on the test set
y_pred = clf.predict(X_test)

# Calculate and print accuracy
accuracy = accuracy_score(y_test, y_pred)
print(f"Accuracy: {accuracy * 100:.2f}%")
