# Spring Cloud Gateway

- Spring Cloud Gateway(SCG) : MSA 환경에서 사용하는 API Gateway 중 하나
- Spring5, Spring Boot2, Project Reactor로 구축된 API Gateway
- 라우팅 / 보안 / 모니터링 / 메트릭 기능 제공



## 개발 진행 사항 (Port)

* Spring cloud gateway : 9000
* Member(User): 9001



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
  - Handler Mapping 시에 필요한 URI 정보나, Path 정보를 확인하는 주체
- Filter (필터) : 특정 팩토리 구성된 Spring Framework GatewayFilter 인스턴스
  - Handler Mapping이 된 후 들어온 요청에 대한 필터 작업 수행
  - Fileter에서는 다운스트림 요청 전후에 요청/응답 수정 가능
  - Pre Filter : 특정 작업이 일어나기 전에 지정
  - Post Filter : 특정 작업이 끝난 후에 지정
- Gateway Handler Mapping
  - Gateway가 Client로부터 어떤 요청이 왔는지 확인하고 Mapping 하는 작업 수행



## 4. 코드 작성 방식

* build.gradle

  * pendencyManagement.imports : Spring Boot 2.2.x 버전을 사용하게 되면 Spring Cloud를 Hoxton 버전을 사용(Spring Boot 2.1.X 버전에서는 Spring Cloud Greenwich 버전을 사용)

  * spring-cloud-starter-gateway : Spring Cloud Gateway를 위한 라이브러리
  * ~~spring-cloud-starter-netflix-hystrix : 더 이상 개발하지 않는다 함~~

  * spring-cloud-starter-contract-stub-runner : Contract Test를 하기 위한 라이브러리. Contract Test(서비스 제공자와 사용자간의 계약을 검증하는 테스트. 자세한 설명은 생략한다) 를 하지 않으려면 제외해도 된다

* application.yml 작성

  * server.port : Spring Cloud Gateway가 동작하는 port 번호
  * spring.cloud.gateway.default-filters : Spring Cloud Gateway 공통 Filter
  * spring.cloud.gateway.routes : 마이크로 소비스 라우팅
  * spring.cloud.gateway.routes.filters : 각 서비스 호출 전 호출되는 필터(filter). 해당 필터들도 Java Class로 추가 구현을 해줘야 한다. agrs는 필터 소스 내에서 사용되는 인자값이다. args는 필요없으면 작성하지 않아도 된다.

* Java Code 작성

  * AbstractGatewayFilterFactory : Gateway를 구현하기 위해서는 GatewayFilterFactory를 구현해야 하며, 상속할 수 있는 추상 클래스가 바로 AbstractGatewayFilterFactory이다.
  * exchange : 서비스 요청/응답값을 담고있는 변수로, 요청/응답값을 출력하거나 변환할 때 사용. 요청값은 **(exchange, chain) ->** 구문 이후에 얻을 수 있으며, 서비스로부터 리턴받은 응답값은 **Mono.fromRunnable(()->** 구문 이후부터 얻을 수 있다.
  * config : application.yml에 선언한 각 filter의 args(인자값) 사용을 위한 클래스

### 5. 참고

* https://twofootdog.tistory.com/64
* https://wonit.tistory.com/497