# 실시간 채팅 솔루션 💬
> _updated at 2025.01.26_

학생 개발팀과 스타트업을 위한 실시간 채팅 솔루션입니다.

## 📄 핵심 기능

- 루트 애플리케이션(Admin 페이지)
  - API를 사용하는 개발자를 위한 Admin 페이지를 제공합니다.
  - 실시간 채팅 솔루션 사용을 위한 인증 기능을 제공합니다.
  - 애플리케이션에 등록된 채팅방과 회원을 관리할 수 있습니다.
    
- 채팅방 관리
  - 1:1 채팅방과 단체 채팅방을 생성하고, 회원을 초대할 수 있습니다.
 
- 실시간 채팅 기능
  - 채팅방 사용자들끼리 실시간 채팅이 가능합니다.
    
- 회원 관리
  - 실시간 채팅에 참여할 회원 등록 및 관리 기능을 제공합니다.

## 🛠️ 핵심 기술 구현 목표
- 실시간성 : Polling이나 Long-Polling 방식 대신 WebSocket과 STOMP 프로토콜을 사용해 실시간 채팅 기능을 구현했습니다.

- CQS 패턴 : Command(명령)와 Query(조회)를 분리하는 CQS(Command Query Separation) 패턴을 적용했습니다.
- 메시지 브로커 : 데이터 휘발성 문제로 Spring WebSocket의 SimpMessagingTemplate 대신, Redis를 사용하여 안정적인 메시지 처리를 구현했습니다.
- 데이터베이스 커넥션 관리 : 데이터베이스 커넥션 풀이 고갈되지 않도록 OSIV(Open Session In View) 설정을 OFF하여 효율적인 DB 연결 관리를 수행했습니다.
- 테스트 : Mockito를 활용한 단위 테스트와 SpringBootTest를 활용한 통합 테스트를 작성하여 코드의 변경 가능성에 대응했습니다.
- API 문서화 : Swagger를 통해 API 문서를 자동으로 생성하고 관리했습니다.

## ⚙️ 기술 스택
- Spring Boot 3.x, Java 21, Gradle
- MySql, MongoDB(메시징 기능용), H2 DataBase(테스트용)
- JPA
- Redis Pub/Sub
- Mockito

## 샘플 앱
- 해당 오픈 API를 사용한 iOS 샘플 앱 제작 예정입니다.
