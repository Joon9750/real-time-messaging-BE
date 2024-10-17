![image](https://github.com/user-attachments/assets/f3b2fccf-4922-4ee9-841a-c038c3da09bf)

# 실시간 채팅 솔루션
> _updated at 2024.10.17_

Sendbird 채팅 솔루션의 가격이 부담되는<br/>
학생 개발팀 그리고 스타트업을 위한 실시간 채팅 솔루션입니다.


## 핵심 기능
### 1. 루트 애플리케이션(Admin 페이지)
- API를 사용하는 클라이언트(개발자)를 위한 Admin 페이지를 제공합니다.
- 실시간 채팅 솔루션을 사용하기 위한 인증을 제공합니다.
- 애플리케이션에 등록된 채팅방, 회원을 관리합니다.

### 2. 채팅방 관리
- 1:1 채팅방과 단체 채팅방을 생성하고 회원을 초대합니다.

### 3. 회원 관리
- 실시간 채팅에 참여할 회원을 등록하고 관리합니다.

## 기술 스택
- Gradle project
- Springboot 3.x
- Java 21
- Jpa
- Spring WebSocket with STOMP protocol
- Apache Kafka for message broker
- H2 Database for testing, 아직 배포에 사용할 DB는 미정

## 기술적 도전
- 소켓 프로그래밍으로 '실시간' 성격을 살린 채팅방 구현
- OSIV 설정을 끄고 Command(명령)와 Query 분리하는 CQS(Command Query Separation) Pattern 적용
- 휘발성인 simpMessagingTemplate을 kafka message broker로 대체
