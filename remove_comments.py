import re
from glob import glob

from tqdm import tqdm

def remove_java_comments(code: str) -> str:
    # Remove multi-line comments
    code = re.sub(r"/\*.*?\*/", "", code, flags=re.DOTALL)

    # Remove single-line comments
    code = re.sub(r"//.*", "", code)
    
    return code

if __name__ == "__main__":
    java_files = glob("dataset/*.java")
    for java_file in tqdm(java_files):
        with open(java_file, "r") as file:
            java_code = file.read()

        clean_code = remove_java_comments(java_code)
        with open(java_file, "w") as file:
            file.write(clean_code)
