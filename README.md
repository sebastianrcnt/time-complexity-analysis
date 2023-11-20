##  실행 방법
### Embeddings 생성
- `--skip-existing`: 이미 존재하는 임베딩은 생성하지 않음
- `--skip-over-512`: 토큰 수가 512 초과인 소스 파일에 대해서는 임베딩을 생성하지 않음
- `--embeddings-folder`: 임베딩이 저장될 폴더 (`model` 하위 폴더 내에 저장됨)
- 자세한 사용법은 `--help` 를 참고하세요

```sh
# bert 임베딩 생성
python create_embeddings.py --model bert --skip-existing --skip-over-512 --embeddings-folder ./embeddings/
# codebert 임베딩 생성
python create_embeddings.py --model codebert --skip-existing --skip-over-512 --embeddings-folder ./embeddings/
```

### Classification
- codebert, bert 모두 임베딩 생성 후 실행
```sh
python run_classification_v3.py
```

## 실험 결과

### 데이터셋 구성

1. token size < 512 (total: 604)

| Language | Count |
|----------|-------|
| java     | 528   |
| js       | 27    |
| py       | 24    |
| cpp      | 19    |
| c        | 6     |

2. original dataset (total: 1461)

| Language | Count |
|----------|-------|
| java     | 925   |
| cpp      | 296   |
| py       | 134   |
| c        | 56    |
| js       | 50    |


### Result Overview

| Model/Embedding Type | Classification Method | Validation Accuracy | Test Accuracy |
|----------------------|-----------------------|---------------------|---------------|
| bert                 | SVM                   | 60.4%               | 57.1%         |
| codebert             | SVM                   | 46.2%               | 52.7%         |
| bert                 | Random Forest         | 52.7%               | 56.0%         |
| codebert             | Random Forest         | 49.5%               | 57.1%         |
| bert                 | KNN                   | 59.3%               | 52.7%         |
| codebert             | KNN                   | 38.5%               | 46.2%         |
