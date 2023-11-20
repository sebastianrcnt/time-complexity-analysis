from typing import Literal
from sklearn.metrics import classification_report, accuracy_score
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
import torch
from logger import logger
import pandas as pd
import numpy as np
import os
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier

import warnings

warnings.filterwarnings(
    "always"
)  # "error", "ignore", "always", "default", "module" or "once"

RANDOM_STATE = 42


class HyperParams:
    metadata_path = "./labels.csv"


def load_metadata():
    df = pd.read_csv(HyperParams.metadata_path)

    df = df[["file_name", "complexity"]]

    df["codebert_embeddings_path"] = df["file_name"].apply(
        lambda x: f"./embeddings/codebert/{x.replace('/', '__')}.pth"
    )

    df["bert_embeddings_path"] = df["file_name"].apply(
        lambda x: f"./embeddings/bert/{x.replace('/', '__')}.pth"
    )

    # find only existing embeddings
    df = df[df["codebert_embeddings_path"].apply(lambda x: os.path.exists(x))]
    df = df[df["bert_embeddings_path"].apply(lambda x: os.path.exists(x))]

    return df


def load_ndarray(path: str) -> np.ndarray:
    tensor = torch.load(path)
    if isinstance(tensor, list):
        tensor = tensor[0]
    tensor = tensor.detach().cpu()
    return tensor.numpy()


def reshape_ndarray(arr: np.ndarray) -> np.ndarray:
    assert arr.shape[0] == 1
    assert arr.shape[2] == 768

    arr = np.concatenate(
        (arr, np.zeros((arr.shape[0], 512 - arr.shape[1], arr.shape[2]))),
        axis=1,
    )

    return arr.reshape(-1)


def do_svm_classification(
    model: Literal["bert", "codebert"],
    train: pd.DataFrame,
    val: pd.DataFrame,
    test: pd.DataFrame,
) -> None:
    logger.info(f"starting SVM classification for {model}")

    # Load and reshape embeddings for training data
    X_train = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in train.iterrows()
        ]
    )
    y_train = train["complexity"].values

    # Load and reshape embeddings for validation data
    X_val = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in val.iterrows()
        ]
    )
    y_val = val["complexity"].values

    # Load and reshape embeddings for test data
    X_test = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in test.iterrows()
        ]
    )
    y_test = test["complexity"].values

    # Initialize and train the SVM classifier
    classifier = SVC(kernel="linear", random_state=RANDOM_STATE)
    classifier.fit(X_train, y_train)  # type: ignore

    # Validate the classifier
    y_val_pred = classifier.predict(X_val)
    val_accuracy = accuracy_score(y_val, y_val_pred)  # type: ignore
    logger.info(f"{model} SVM Validation Accuracy: {val_accuracy}")

    # Test the classifier
    y_test_pred = classifier.predict(X_test)
    test_accuracy = accuracy_score(y_test, y_test_pred)  # type: ignore
    logger.info(f"{model} SVM Test Accuracy: {test_accuracy}")

    # Additional performance metrics can be logged if necessary
    logger.info(f"{model} SVM Classification Report (Validation Set):\n" + classification_report(y_val, y_val_pred))  # type: ignore
    logger.info(f"{model} SVM Classification Report (Test Set):\n" + classification_report(y_test, y_test_pred))  # type: ignore


def do_random_forest_classification(
    model: Literal["bert", "codebert"],
    train: pd.DataFrame,
    val: pd.DataFrame,
    test: pd.DataFrame,
) -> None:
    logger.info(f"Starting Random Forest classification for {model}")

    # Load and reshape embeddings for training data
    X_train = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in train.iterrows()
        ]
    )
    y_train = train["complexity"].values

    # Load and reshape embeddings for validation data
    X_val = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in val.iterrows()
        ]
    )
    y_val = val["complexity"].values

    # Load and reshape embeddings for test data
    X_test = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in test.iterrows()
        ]
    )
    y_test = test["complexity"].values

    # Initialize and train the Random Forest classifier
    rf_classifier = RandomForestClassifier(n_estimators=100, random_state=RANDOM_STATE)
    rf_classifier.fit(X_train, y_train)  # type: ignore

    # Validate the classifier
    y_val_pred = rf_classifier.predict(X_val)
    val_accuracy = accuracy_score(y_val, y_val_pred)  # type: ignore
    logger.info(f"{model} Random Forest Validation Accuracy: {val_accuracy}")

    # Test the classifier
    y_test_pred = rf_classifier.predict(X_test)
    test_accuracy = accuracy_score(y_test, y_test_pred)  # type: ignore
    logger.info(f"{model} Random Forest Test Accuracy: {test_accuracy}")

    # Additional performance metrics can be logged if necessary
    logger.info(f"{model} Random Forest Classification Report (Validation Set):\n" + classification_report(y_val, y_val_pred))  # type: ignore
    logger.info(f"{model} Random Forest Classification Report (Test Set):\n" + classification_report(y_test, y_test_pred))  # type: ignore

def do_knn_classification(
    model: Literal["bert", "codebert"],
    train: pd.DataFrame,
    val: pd.DataFrame,
    test: pd.DataFrame,
) -> None:
    logger.info(f"Starting KNN classification for {model}")

    # Load and reshape embeddings for training data
    X_train = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in train.iterrows()
        ]
    )
    y_train = train["complexity"].values

    # Load and reshape embeddings for validation data
    X_val = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in val.iterrows()
        ]
    )
    y_val = val["complexity"].values

    # Load and reshape embeddings for test data
    X_test = np.array(
        [
            reshape_ndarray(load_ndarray(row[f"{model}_embeddings_path"]))
            for _, row in test.iterrows()
        ]
    )
    y_test = test["complexity"].values

    # Initialize and train the KNN classifier
    knn_classifier = KNeighborsClassifier(n_neighbors=5)
    knn_classifier.fit(X_train, y_train)  # type: ignore

    # Validate the classifier
    y_val_pred = knn_classifier.predict(X_val)
    val_accuracy = accuracy_score(y_val, y_val_pred)  # type: ignore
    logger.info(f"{model} KNN Validation Accuracy: {val_accuracy}")

    # Test the classifier
    y_test_pred = knn_classifier.predict(X_test)
    test_accuracy = accuracy_score(y_test, y_test_pred)  # type: ignore
    logger.info(f"{model} KNN Test Accuracy: {test_accuracy}")

    # Additional performance metrics can be logged if necessary
    logger.info(f"{model} KNN Classification Report (Validation Set):\n" + classification_report(y_val, y_val_pred))  # type: ignore
    logger.info(f"{model} KNN Classification Report (Test Set):\n" + classification_report(y_test, y_test_pred))  # type: ignore




def main():
    md = load_metadata()
    md = md.sample(frac=1.0, random_state=RANDOM_STATE)
    train_data, testval_data = train_test_split(md, test_size=0.3, random_state=RANDOM_STATE)
    test_data, val_data = train_test_split(testval_data, test_size=0.5, random_state=RANDOM_STATE)

    logger.info(
        f"total data: {len(md)}, test data: {len(test_data)}, val data: {len(val_data)}, train data: {len(train_data)}"
    )
    # do a SVM classification

    do_svm_classification("bert", train_data, val_data, test_data)
    do_svm_classification("codebert", train_data, val_data, test_data)
    do_random_forest_classification("bert", train_data, val_data, test_data)
    do_random_forest_classification("codebert", train_data, val_data, test_data)
    do_knn_classification("bert", train_data, val_data, test_data)
    do_knn_classification("codebert", train_data, val_data, test_data)


if __name__ == "__main__":
    main()
