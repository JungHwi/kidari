# 키다리 스튜디오 과제

### Language

Kotlin

### Framework

Spring Boot

### RDBMS

MySQL 을 사용하였는데 docker-compose.yaml 에 정의되어 있습니다.

### 데이타 설계

resources/db/ 하위의 .sql 파일 참고

### 그 밖의 설명

RDBMS 와 Test Container 사용을 위해 Docker 가 설치되어 있어야 합니다.
test container 를 사용하였고, 초기 설정을 위해 flyway 도 함께 사용하였습니다.
원래는 RestDoc 도 함께 사용하는데 문서 얘기는 없어서 따로 작성하지는 않았습니다.

0. Docker 설치
1. RDMBS 설치 - docker-compose.yaml 2이용
2. build - TestContainer 에서 Test 실행
3. Spring Boot 실행

### 그리고

과제 내용 중에 동시성 이슈 처리는 따로 하지 않았습니다.
예전에 queue 사용해서 타임딜 같은 것들의 재고 처리를 했었는데
queue 없이 하려니 시간을 너무 많이 쓰는 것 같아서 이쯤에서 제출하려고 합니다. 