# Spring Cloud - Eureka

![img](https://blog.kakaocdn.net/dn/mE68z/btq2x5fyU7T/V3c1fkauieHK3Wch39ckR1/img.png)

## 정의

- Eureka : Discovery Server
  - 각각의 서비스 인스턴스들이 동적으로 확장, 축소 되더라도 인스턴스의 상태를 하나의 서비스로 관리할 수 있는 서비스



## 구성

* Service Discovery
  * 각각의 서비스의 위치가 등록된 서버에서 특정 작업을 위한 서버의 위치를 파악하는 작업
  * Service Discovery를 위해서 **Spring Cloud Netflix - Eureka Server**를 사용
  * **현재 이 Eureka 프로젝트는 Service Discovery 서버로 가용됨**

- Service Registry
  - 각각의 서비스가 자신의 위치(IP) 정보를 특정 서버에 등록 **Registry** 하는 작업
  - Service Registry를 위해서 **Spring Cloud Netflix - Eureka Client**를 사용



## 동작순서

1. Service Registry 기능을 할 Eureka Server가 최초에 기동
2. Service Registry 서버인 Eureka Server에 등록될 서비스 기동
   * 등록된 서비스는 Eureka Client라고 부름
3. Eureka 서버는 자신에게 등록된 Eureka Client에게 30초마다 Ping 보냄
   * Health check 수행
4. 만약 30초마다 보내는 Heart Heat가 일정 횟수 이상으로 동작되지 않으면 Eureka Server는 해당 Client를 삭제

Eureka는 단지 서비스의 위치만을 표현하는 역할로 보통 Spring Cloud Gateway나 Zuul과 같은 Gateway서비스, Ribbon과 같은 클라이언트 사이드 로드밸런서와 함께 동작함.



## 작성 순서

1. 프로젝트 생성
2. 의존성 추가
3. application.yml 추가
4. DiscoveryApplication 에 `@EnableEurekaServer` 등록
5. Eureka Dashboard에서 확인하기
   - localhost:포트번호

6. 클라이언트 구성하기



## 클라이언트 구성 방법

1. 프로젝트생성

2. 의존성 추가

   ```apl
   ext {
   	set('springCloudVersion', "2021.0.0")
   }
   ```

   ```apl
   dependencies {
   	implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
   }
   ```

   ```apl
   dependencyManagement {
   	imports {
       	mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"    
       }
   }
   ```

3. application.yml 작성

   ```apl
   server:
     port: 포트번호
   
   spring:
     application:
       name: 어플리케이션_이름
   
   eureka:
     instance:
       instance-id: 대쉬보드에 식별하기 위한 인스턴스 아이디
   
     client:
       register-with-eureka: true
       fetch-registry: true
       service-url:
         defaultZone: http://localhost:8999/eureka
   ```

   - **server.port** : 서버의 포트 번호
   - **spring.application.name** : 애플리케이션 서버의 이름
   - **eureka.instance.instance-id** 유레카가 인식할 instance id, 대쉬보드에서 식별하는 용도
   - **eureka.client.register-with-eureka & fetch-registry** 이번에는 해당 서비스가 클라이언트로 인식되어야 하므로 true
   - **eureka.client.service-url.defaultZone** : eureka server 가 위치하고 있는 기본 주소 기재
     - **defaultZone 같은 경우 꼭 Camel Case로 기재해야 함**

4. 해당 Application에 @EnableDiscoveryClient 어노테이션 추가

5. 각각 서버의 Controller 생성



# 참고

* https://wonit.tistory.com/495?category=854728