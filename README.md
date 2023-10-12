### 파이썬 가상 환경 활성화
```sh
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```
### Embeddings 생성
```sh
# bert 임베딩 생성
python create_embeddings_bert.py

# codebert 임베딩 생성
python create_embeddings_codebert.py
```

### Classification
- codebert, bert 모두 임베딩 생성 후 실행
```sh
# run random forest, svc classification
python run_classification.py
```
