# Spring Cloud Gateway

- Spring Cloud Gateway(SCG) : MSA 환경에서 사용하는 API Gateway 중 하나
- Spring5, Spring Boot2, Project Reactor로 구축된 API Gateway
- 라우팅 / 보안 / 모니터링 / 메트릭 기능 제공



## 1. API Gateway

- 유입되는 모든 요청/응답에 대응 -> 인증 / 보안 적용

- URI에 따라 서비스 엔드포인트 변화 -> 동적 라우팅 

  - 도메인 변경없이 레거시 시스템을 신규 시스템으로 점진적으로 교체해 나아갈 수 있음

- 트래픽 집중에 따른 시스템 구성 단순화

- 동적 라우팅에 따른 신규 스펙 차등 적용 가능

- 스트레스 테스트 등 신규 서비스에 대한 테스트 편리

  

## 2. Spring Cloud Zuul과의 차이점

* Zuul은 서블릿 프레임워크 기반 : Synchronous, Blocking 방식
* Zuul2에서는 비동기, 논블로킹 방식 지원
* SCG는 스프링 기반 : 스프링 서비스간의 호환성이 높음
* **Spring Cloud Gateway는 Netty 런타입 기반으로 동작 -> 서블릿 컨테이너나 WAR로 빌드된 경우 동작안함**



## 3. Spring Cloud Gateway 용어 정리

- Route : 게이트웨이의 기본 골격
  - ID
  - 목적지 URI
  - 조건부(predicate) 집합
  - 필터(filter) 집합
- Predicate (조건부) : Java8의 Function Predicate
  - Input Type = Spring Framework ServerWebExchange
  - Header 나 Parameter 같은 HTTP 요청의 모든 항목 비교 가능
- Filter (필터) : 특정 팩토리 구성된 Spring Framework GatewayFilter 인스턴스
  - Fileter에서는 다운스트림 요청 전후에 요청/응답 수정 가능



## 4. 코드 작성 방식

* application.yml 작성
* Java Code 작성