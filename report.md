total data: 604, test data: 91, val data: 91, train data: 422
starting SVM classification for bert
train data: 422, val data: 91, test data: 91
bert SVM Validation Accuracy: 0.6043956043956044
bert SVM Test Accuracy: 0.5714285714285714
bert SVM Classification Report (Validation Set):
              precision    recall  f1-score   support

           0       0.71      0.56      0.63         9
           1       0.60      0.43      0.50         7
           2       0.56      0.79      0.66        39
           3       0.80      0.29      0.42        14
           4       0.63      0.55      0.59        22

    accuracy                           0.60        91
   macro avg       0.66      0.52      0.56        91
weighted avg       0.63      0.60      0.59        91

bert SVM Classification Report (Test Set):
              precision    recall  f1-score   support

           0       0.85      0.69      0.76        16
           1       1.00      0.25      0.40         4
           2       0.55      0.74      0.63        43
           3       0.67      0.29      0.40        14
           4       0.31      0.29      0.30        14

    accuracy                           0.57        91
   macro avg       0.67      0.45      0.50        91
weighted avg       0.60      0.57      0.56        91

starting SVM classification for codebert
train data: 422, val data: 91, test data: 91
codebert SVM Validation Accuracy: 0.46153846153846156
codebert SVM Test Accuracy: 0.5274725274725275
codebert SVM Classification Report (Validation Set):
              precision    recall  f1-score   support

           0       0.43      0.33      0.38         9
           1       0.67      0.29      0.40         7
           2       0.43      0.69      0.53        39
           3       0.50      0.14      0.22        14
           4       0.57      0.36      0.44        22

    accuracy                           0.46        91
   macro avg       0.52      0.36      0.39        91
weighted avg       0.49      0.46      0.44        91

codebert SVM Classification Report (Test Set):
              precision    recall  f1-score   support

           0       0.73      0.69      0.71        16
           1       0.00      0.00      0.00         4
           2       0.51      0.67      0.58        43
           3       0.50      0.21      0.30        14
           4       0.38      0.36      0.37        14

    accuracy                           0.53        91
   macro avg       0.43      0.39      0.39        91
weighted avg       0.51      0.53      0.50        91

