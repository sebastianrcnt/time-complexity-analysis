from typing import List, Literal, Tuple
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score


from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC

import torch
import pandas as pd
import numpy as np
import os


def load_metadata():
    df = pd.read_csv("./metadata.csv")
    df = df[["file_name", "complexity"]]
    df["codebert_embeddings_path"] = df["file_name"].apply(
        lambda x: f"./embeddings-codebert/{x}.pth"
    )
    df["bert_embeddings_path"] = df["file_name"].apply(lambda x: f"./embeddings-bert/{x}.pth")

    # make sure that the matching embeddings file exists
    # for each items in df, if codebert_embeddings_path doesn't exist, delete it.
    # for each items in df, if bert_embeddings_path doesn't exist, delete it.
    df = df[df["codebert_embeddings_path"].apply(lambda x: os.path.exists(x))]
    df = df[df["bert_embeddings_path"].apply(lambda x: os.path.exists(x))]

    return df


def load_1d_np_arrary_embedding_from_path(path: str) -> np.ndarray:
    print(f"loading {path}")
    arr = torch.load(path).detach().cpu().numpy()
    assert arr.shape[0] == 1
    assert arr.shape[2] == 768

    arr = np.concatenate(
        (arr, np.zeros((arr.shape[0], 512 - arr.shape[1], arr.shape[2]))),
        axis=1,
    )

    return arr.reshape(-1)


def classify_svm(labels: List[str], embeddings: List[np.array]) -> None:
    """
    df should be:
        file_name: (index), str
        complexity: str
        embeddings: numpy array
    """

    rows_count = len(labels)
    training_rows_count = int(rows_count * 0.8)

    X_train = embeddings[:training_rows_count]
    y_train = labels[:training_rows_count]
    X_test = embeddings[training_rows_count:]
    y_test = labels[training_rows_count:]

    # Train a SVC classifier
    clf = SVC()  # Using 100 trees

    clf.fit(X_train, y_train)

    # Predict on the test set

    y_pred_test = clf.predict(X_test)
    y_pred_train = clf.predict(X_train)

    train_accuracy = accuracy_score(y_train, y_pred_train)
    test_accuracy = accuracy_score(y_test, y_pred_test)
    predictions = np.concatenate((y_pred_train, y_pred_test))

    return train_accuracy, test_accuracy, predictions

def classify_random_forest(labels: List[str], embeddings: List[np.array]):
    rows_count = len(labels)
    training_rows_count = int(rows_count * 0.8)
    testing_rows_count = rows_count - training_rows_count

    X_train = embeddings[:training_rows_count]
    y_train = labels[:training_rows_count]
    X_test = embeddings[training_rows_count:]
    y_test = labels[training_rows_count:]

    # Train a Random Forest classifier
    clf = RandomForestClassifier(n_estimators=200, random_state=21)  # Using 100 trees
    clf.fit(X_train, y_train)

    # Predict on the test set
    y_pred_test = clf.predict(X_test)
    y_pred_train = clf.predict(X_train)

    train_accuracy = accuracy_score(y_train, y_pred_train)
    test_accuracy = accuracy_score(y_test, y_pred_test)
    predictions = np.concatenate((y_pred_train, y_pred_test))

    return train_accuracy, test_accuracy, predictions

if __name__ == "__main__":
    df = load_metadata()

    # shuffle df
    shuffled_df = df.sample(frac=1)
    
    # load complexities
    complexities = list(shuffled_df["complexity"])
    bert_embeddings = list(
        shuffled_df["bert_embeddings_path"].apply(load_1d_np_arrary_embedding_from_path)
    )
    codebert_embeddings = list(
        shuffled_df["codebert_embeddings_path"].apply(load_1d_np_arrary_embedding_from_path)
    )

    bert_train_accuracy_svm, bert_test_accuracy_svm, bert_predictions_svm = classify_svm(
        complexities, bert_embeddings
    )

    print(
        f"bert svm train accuracy: {bert_train_accuracy_svm}, test accuracy: {bert_test_accuracy_svm}"
    )

    (
        codebert_train_accuracy_svm,
        codebert_test_accuracy_svm,
        codebert_predictions_svm,
    ) = classify_svm(
        complexities,
        codebert_embeddings
    )

    print(
        f"codebert svm train accuracy: {codebert_train_accuracy_svm}, test accuracy: {codebert_test_accuracy_svm}"
    )


    bert_train_accuracy_rf, bert_test_accuracy_rf, bert_predictions_rf = classify_random_forest(
        complexities, bert_embeddings
    )

    print(
        f"bert rf train accuracy: {bert_train_accuracy_rf}, test accuracy: {bert_test_accuracy_rf}"
    )

    (
        codebert_train_accuracy_rf,
        codebert_test_accuracy_rf,
        codebert_predictions_rf,
    ) = classify_random_forest(
        complexities,
        codebert_embeddings
    )

    print(
        f"codebert rf train accuracy: {codebert_train_accuracy_rf}, test accuracy: {codebert_test_accuracy_rf}"
    )


    shuffled_df['bert_predictions_svm'] = bert_predictions_svm
    shuffled_df['codebert_predictions_svm'] = codebert_predictions_svm
    shuffled_df['bert_predictions_rf'] = bert_predictions_rf
    shuffled_df['codebert_predictions_rf'] = codebert_predictions_rf

    shuffled_df.to_csv("./svm_rf_predictions.csv")