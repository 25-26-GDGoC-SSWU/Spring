# 🌱 Spring Study Repository

이 레포지토리는 **스프링 스터디 실습용 repository**입니다.  
각자 강의를 수강하며 실습한 내용을 정리하고, 학습 과정을 공유하기 위해 사용합니다.

---

## 📂 실습 시 브랜치 사용 규칙

> ⚠️ **절대 main 브랜치에 직접 push 또는 merge하지 마세요!**  
> 모든 실습 코드는 개인 브랜치를 만들어 올려야 합니다.

### ✅ 브랜치 네이밍 규칙

- 강의 실습 시:  
  **이름_강의번호**  
  예) `EUNWOO_1`

- 워크북 실습 시 (주차별 과제):  
  **이름_워크북**  
  예) `EUNWOO_WORKBOOK`  

### 📘 강의 번호 안내

| 강의명 | 번호 |
|:--------|:----:|
| 스프링 입문 | 1 |
| JPA 활용 1 | 2 |
| 스프링 핵심 원리 | 3 |

<br>

## 🛠️ 실습 방법
- 새로 시작하는 경우 → 실습 방법 1  
- 기존 프로젝트 업로드 → 실습 방법 2

<br>

## 🧑‍💻 실습 방법 1 (코드 작성 시작 전)

### 1️⃣ 레포지토리 클론

```bash
git clone https://github.com/25-26-GDGoC-SSWU/Spring.git
cd Spring
```

### 2️⃣ 브랜치 생성
```bash
# 예: EUNWOO_1 브랜치 생성
git checkout -b EUNWOO_1
```

### 3️⃣ 코드 작성 및 커밋
```bash
git add .
git commit -m "스프링 입문 실습 1차 업로드"
```

### 4️⃣ 브랜치 푸시
```bash
# 예: EUNWOO_1 브랜치로 푸시
git push origin EUNWOO_1
```

<br>

## 🧑‍💻 실습 방법 2 (이미 존재하는 프로젝트를 업로드 하고 싶을 때)

### 1️⃣ Git 초기화 및 원격 리포지토리 연결
```bash
# 현재 로컬 프로젝트 폴더로 이동
cd [내_프로젝트_폴더명]

# 만약 이미 git이 연결되어 있다면 .git 폴더 삭제
rm -rf .git

# Git 초기화
git init

# 원격 저장소 연결
git remote add origin https://github.com/25-26-GDGoC-SSWU/Spring.git
```

### 2️⃣ 브랜치 생성
- 브랜치가 원격 리포지토리에 없을 경우
```bash
# 예: EUNWOO_1 브랜치 생성 및 이동
git checkout -b EUNWOO_1
```
- 브랜치가 원격 리포지토리에 있을 경우
```bash
# 원격 브랜치 목록 갱신
git fetch origin

# 예: 원격의 EUNWOO_1 브랜치를 로컬에 동일하게 생성 및 연결
git checkout -b EUNWOO_1 origin/EUNWOO_1
```

### 3️⃣ 코드 추가 및 커밋
```bash
# 모든 파일 스테이징 후 커밋
git add .
git commit -m "커밋 메시지 자유롭게 입력"
```

### 4️⃣ 브랜치 푸시
```bash
# 브랜치를 원격 저장소에 푸시
git push -u origin EUNWOO_1
```

<br>

## 📝 실습 결과 기록

각자의 브랜치 내에 README.md를 생성하거나 수정하여,
실습 과정과 결과, 느낀 점 등을 자유롭게 기록해 주세요.

### 예시:
> # EUNWOO_1 실습 기록
> - 스프링 입문 강의 1강 실습 완료
> - 스프링 프로젝트 기본 구조 이해
> - 실행 결과 정상 확인
