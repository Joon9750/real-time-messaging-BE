![image](https://github.com/user-attachments/assets/f3b2fccf-4922-4ee9-841a-c038c3da09bf)

# 실시간 채팅 솔루션
> _updated at 2024.10.21_

Sendbird 채팅 솔루션의 가격이 부담되는<br/>
학생 개발팀 및 스타트업을 위한 실시간 채팅 솔루션입니다.

## 핵심 기능
- 루트 애플리케이션 (Admin 페이지)
  - API를 사용하는 개발자를 위한 Admin 페이지를 제공합니다.
  - 실시간 채팅 솔루션 사용을 위한 인증 기능을 제공합니다.
  - 애플리케이션에 등록된 채팅방과 회원을 관리할 수 있습니다.
    
- 채팅방 관리
  - 1:1 채팅방과 단체 채팅방을 생성하고, 회원을 초대할 수 있습니다.
    
- 회원 관리
  - 실시간 채팅에 참여할 회원 등록 및 관리 기능을 제공합니다.
    
## 기술 스택
- Gradle 프로젝트

- Spring Boot 3.x
- Java 21
- JPA
- Spring WebSocket with STOMP protocol
- Apache Kafka for message broker
- Mockito for unit testing
- H2 Database for testing (배포용 DB는 미정)

## 핵심 기술 구현
- **실시간성**: Polling이나 Long-Polling 방식 대신 **WebSocket**을 사용해 실시간 채팅 기능을 구현했습니다.

- **CQS 패턴**: Command(명령)와 Query(조회)를 분리하는 **Command Query Separation (CQS)** 패턴을 적용했습니다.
- **메시지 브로커**: 휘발성인 **Spring WebSocket**의 **SimpMessagingTemplate** 대신, **Kafka**를 사용하여 안정적인 메시지 처리를 구현했습니다.
- **DB 연결 관리**: 데이터베이스 커넥션 풀이 고갈되지 않도록 **OSIV (Open Session In View)** 설정을 OFF하여 효율적인 DB 연결 관리를 수행했습니다.
- **단위 테스트**: **Mockito**를 활용해 단위 테스트를 작성하여 비즈니스 로직의 안정성을 확보했습니다.
- **API 문서화**: **Swagger**를 통해 API 문서를 자동으로 생성하고 관리했습니다.
