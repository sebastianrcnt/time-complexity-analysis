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


def split_data(model, train, val, test):
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

    return X_train, y_train, X_val, y_val, X_test, y_test


def do_svm_classification(
    model: Literal["bert", "codebert"],
    train: pd.DataFrame,
    val: pd.DataFrame,
    test: pd.DataFrame,
):
    logger.info(f"starting SVM classification for {model}")

    X_train, y_train, X_val, y_val, X_test, y_test = split_data(model, train, val, test)

    # Initialize and train the SVM classifier
    classifier = SVC(kernel="linear", random_state=RANDOM_STATE)
    classifier.fit(X_train, y_train)  # type: ignore

    y_train_pred = classifier.predict(X_train)

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

    return y_train_pred, y_val_pred, y_test_pred


def do_random_forest_classification(
    model: Literal["bert", "codebert"],
    train: pd.DataFrame,
    val: pd.DataFrame,
    test: pd.DataFrame,
):
    logger.info(f"Starting Random Forest classification for {model}")

    X_train, y_train, X_val, y_val, X_test, y_test = split_data(model, train, val, test)

    # Initialize and train the Random Forest classifier
    rf_classifier = RandomForestClassifier(n_estimators=100, random_state=RANDOM_STATE)
    rf_classifier.fit(X_train, y_train)  # type: ignore

    y_train_pred = rf_classifier.predict(X_train)

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

    return y_train_pred, y_val_pred, y_test_pred


def do_knn_classification(
    model: Literal["bert", "codebert"],
    train: pd.DataFrame,
    val: pd.DataFrame,
    test: pd.DataFrame,
):
    logger.info(f"Starting KNN classification for {model}")
    X_train, y_train, X_val, y_val, X_test, y_test = split_data(model, train, val, test)

    # Initialize and train the KNN classifier
    knn_classifier = KNeighborsClassifier(n_neighbors=5)
    knn_classifier.fit(X_train, y_train)  # type: ignore

    y_train_pred = knn_classifier.predict(X_train)

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

    return y_train_pred, y_val_pred, y_test_pred


def main():
    md = load_metadata()
    md = md.sample(frac=1, random_state=RANDOM_STATE)
    train_data, testval_data = train_test_split(
        md, test_size=0.3, random_state=RANDOM_STATE
    )
    test_data, val_data = train_test_split(
        testval_data, test_size=0.5, random_state=RANDOM_STATE
    )

    logger.info(
        f"total data: {len(md)}, test data: {len(test_data)}, val data: {len(val_data)}, train data: {len(train_data)}"
    )
    # do a SVM classification

    # bert_svm_train_pred, bert_svm_val_pred, bert_svm_test_pred = do_svm_classification(
    #     "bert", train_data, val_data, test_data
    # )

    # (
    #     codebert_svm_train_pred,
    #     codebert_svm_val_pred,
    #     codebert_svm_test_pred,
    # ) = do_svm_classification("codebert", train_data, val_data, test_data)

    # (
    #     bert_rf_train_pred,
    #     bert_rf_val_pred,
    #     bert_rf_test_pred,
    # ) = do_random_forest_classification("bert", train_data, val_data, test_data)

    # (
    #     codebert_rf_train_pred,
    #     codebert_rf_val_pred,
    #     codebert_rf_test_pred,
    # ) = do_random_forest_classification("codebert", train_data, val_data, test_data)

    # bert_knn_train_pred, bert_knn_val_pred, bert_knn_test_pred = do_knn_classification(
    #     "bert", train_data, val_data, test_data
    # )

    # (
    #     codebert_knn_train_pred,
    #     codebert_knn_val_pred,
    #     codebert_knn_test_pred,
    # ) = do_knn_classification("codebert", train_data, val_data, test_data)

    # # save the predictions
    # train_data["bert_svm_train_pred"] = bert_svm_train_pred
    # train_data["codebert_svm_train_pred"] = codebert_svm_train_pred
    # train_data["bert_rf_train_pred"] = bert_rf_train_pred
    # train_data["codebert_rf_train_pred"] = codebert_rf_train_pred
    # train_data["bert_knn_train_pred"] = bert_knn_train_pred
    # train_data["codebert_knn_train_pred"] = codebert_knn_train_pred

    # train_data.to_csv("train_data_with_predictions.csv", index=False)

    # val_data["bert_svm_val_pred"] = bert_svm_val_pred
    # val_data["codebert_svm_val_pred"] = codebert_svm_val_pred
    # val_data["bert_rf_val_pred"] = bert_rf_val_pred
    # val_data["codebert_rf_val_pred"] = codebert_rf_val_pred
    # val_data["bert_knn_val_pred"] = bert_knn_val_pred
    # val_data["codebert_knn_val_pred"] = codebert_knn_val_pred

    # val_data.to_csv("val_data_with_predictions.csv", index=False)

    # test_data["bert_svm_test_pred"] = bert_svm_test_pred
    # test_data["codebert_svm_test_pred"] = codebert_svm_test_pred
    # test_data["bert_rf_test_pred"] = bert_rf_test_pred
    # test_data["codebert_rf_test_pred"] = codebert_rf_test_pred
    # test_data["bert_knn_test_pred"] = bert_knn_test_pred
    # test_data["codebert_knn_test_pred"] = codebert_knn_test_pred

    # test_data.to_csv("test_data_with_predictions.csv", index=False)

    methods = ['svm', 'rf', 'knn', 'blue']
    models = ['bert', 'codebert']

    __gb__ = globals()

    for model in models:
        for method in methods:
            # classify
            train_data[f"{model}_{method}_train_pred"] = __gb__[f"classify_{method}"](train_data, model)
            val_data[f"{model}_{method}_val_pred"] = __gb__[f"classify_{method}"](val_data, model)
            test_data[f"{model}_{method}_test_pred"] = __gb__[f"classify_{method}"](test_data, model)

    train_data.to_csv("train_data_with_predictions.csv", index=False)
    val_data.to_csv("val_data_with_predictions.csv", index=False)
    test_data.to_csv("test_data_with_predictions.csv", index=False)







if __name__ == "__main__":
    main()
