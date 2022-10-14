# Elecvery-Clone-Backend

## 목차

1. [프로젝트 설명](#프로젝트-설명)
2. [프로젝트 설치 및 실행 방법](#프로젝트-설치-및-실행-방법)
3. [팀원](#팀원)
4. [기타 문서](#기타-문서)

## 프로젝트 설명

### 개발 목표

- Elecvery 어플리케이션에 필요한 백엔드 기능을 개발한다.
- 자바를 통해 객체 지향적으로 코드를 작성하며 개발한다.
- Spring Framework를 사용해본다.

### 주요 기능

- 로그인 기능
- 프로필 & 닉네임 관련 기능
- 충전소 정보 조회
- 전기차 충전 예약 시스템
- 충전 이력 조회 기능
- 내 차 관리 기능
- 내 카드 & 충전 할인 카드 관련 기능

### 사용 기술

![JDK](https://img.shields.io/badge/JDK-17-gray?logo=openjdk&logoColor=white&labelColor=946e5b&style=plastic)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.4-gray?logo=springboot&logoColor=white&labelColor=6DB33F&style=plastic)
![Gradle](https://img.shields.io/badge/Gradle-7.5-gray?logo=gradle&logoColor=white&labelColor=02303A&style=plastic)
![MySQL](https://img.shields.io/badge/MySQL-8.0.30-gray?style=plastic&logo=MySQL&logoColor=white&labelColor=4479A1)
<br>
![Amazon EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=plastic&logo=AmazonEC2&logoColor=white)
![Amazon S3](https://img.shields.io/badge/Amazon_S3-569A31?style=plastic&logo=AmazonS3&logoColor=white)
![Amazon RDS](https://img.shields.io/badge/Amazon_RDS-527FFF?style=plastic&logo=AmazonRDS&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=plastic&logo=GitHub&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=plastic&logo=Git&logoColor=white)

### 인프라 구조도

![diagram](https://user-images.githubusercontent.com/92802207/195262194-89e8e8c8-463e-4039-bcda-afe938541f1c.png)

## 프로젝트 설치 및 실행 방법

1. **git clone**

   프로젝트를 저장하고 싶은 폴더에서 터미널을 연 후, 터미널에 아래 명령어를 입력합니다.

   ```
   git clone https://github.com/Pusan21/Elecvery-Clone-Backend.git
   ```

2. **IntelliJ IDE & openjdk-17**
   - `IntelliJ` IDE로, 클론한 프로젝트를 엽니다.
   - jdk 버전은 `17`로 설정합니다.
3. **application-private.yml 파일 생성**

   `\src\main\resources` 디렉토리에서, `applicaiton-private.yml` 파일을 생성합니다.

   ```
   spring:
     datasource:
       username: {mysql username}
       password: {mysql password}
   ```

   파일에 위 처럼 작성하고, username과 password에는 `로컬 mysql 서버`의 `username`과 `password`를 작성해줍니다.

4. **MySQL 실행 & main 테이블 생성**

   **버전 8 이상의 MySQL**을 실행한 후, `main` 테이블을 생성합니다.

5. **실행 확인**

   `ElecveryCloneBackendApplication`의 `main`을 실행하고, 웹 브라우저에 localhost/ping 을 입력합니다.

   **Request**

   ```
   GET /ping HTTP/1.1
   Host: localhost
   ```

   **Response**

   ```
   {
     "message": "pong",
     "zonedDateTime": "2022-10-03T12:59:44.42093+09:00"
   }
   ```

   테스트용 `PingPong API`가 정상적으로 동작함을 확인할 수 있습니다.

## 팀원

<table>
<tr>
<td align="center">
<a href="https://github.com/gilteunchoi">
<img src="https://github.com/gilteunchoi.png" width="100px;" alt=""/>
<br/><b>@gilteunchoi</b>
</a>
</td>
<td align="center">
<a href="https://github.com/fienestar">
<img src="https://github.com/fienestar.png" width="100px;" alt=""/>
<br/><b>@fienestar</b>
</a>
</td>
<td align="center">
<a href="https://github.com/shkisme">
<img src="https://github.com/shkisme.png" width="100px;" alt=""/>
<br/><b>@shkisme</b>
</a>
</td>
</tr>
</table>

## 기타 문서

- [노션 링크](https://www.notion.so/5db5b9d4af5f4df0b8084f551341cf19)
- API 문서
