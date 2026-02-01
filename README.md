# 🛒 Precamp Shop API

Spring Boot 기반의 상품 및 주문 관리 API 시스템입니다. 본 프로젝트는 **REST API 설계, 엔티티 연관관계, 그리고 계층화된 패키지 구조**를 중심으로 구성되었습니다.

---

## 📦 패키지 구조 (Package Structure)

```text
com.precamp.shop
 ├── 📁 common
 │   ├── ApiResponse             # 공통 응답 규격
 │   ├── BaseEntity              # 등록/수정일 공통 엔티티
 │   ├── GlobalExceptionHandler  # 전역 예외 처리
 │   └── InitDb                  # 테스트 데이터 초기화
 │
 ├── 📁 controller
 │   ├── ProductController       # 상품 API 엔드포인트
 │   └── OrderController         # 주문 API 엔드포인트
 │
 ├── 📁 domain
 │   ├── Product                 # 상품 엔티티 (재고/상태 관리)
 │   ├── Order                   # 주문 엔티티 (생명주기 관리)
 │   └── 📁 status               # 도메인 상태값 (Enum)
 │
 ├── 📁 dto
 │   ├── ProductRequest/Response # 상품 관련 DTO
 │   └── OrderRequest/Response   # 주문 관련 DTO
 │
 ├── 📁 exception
 │   └── BusinessException       # 커스텀 비즈니스 예외들
 │
 ├── 📁 repository
 │   ├── ProductRepository       # 상품 데이터 접근
 │   └── OrderRepository         # 주문 데이터 접근
 │
 ├── 📁 service
 │   ├── ProductService          # 상품 비즈니스 로직
 │   └── OrderService            # 주문 비즈니스 로직
 │
 └── ShopApplication             # 메인 실행 클래스
```
### 1\. 패키지 설계 기준

-   **controller**: HTTP 요청/응답 처리 및 DTO 매핑을 담당합니다.
-   **service**: 비즈니스 로직을 수행하고 트랜잭션을 관리합니다.
-   **domain**: 핵심 도메인 모델(JPA Entity) 및 도메인 로직을 포함합니다.
-   **dto**: 계층 간 데이터 전송을 위한 전용 객체입니다.
-   **exception**: 도메인 및 비즈니스 관련 커스텀 예외를 정의합니다.
-   **common**: 공통 응답 구조 및 베이스 엔티티를 관리합니다.

---

## 🔗 엔티티 연관관계 (Entity Relationship)

### Product ↔ Order (1:N)

-   **Product (One)**
    -   하나의 상품은 여러 주문을 가질 수 있습니다.
    -   재고(stockQuantity)와 상태(ProductStatus)를 관리합니다.
    -   **논리 삭제(Soft Delete)** 방식(DELETED)으로 데이터를 보존합니다.
-   **Order (Many)**
    -   주문은 하나의 상품에만 속합니다. (ManyToOne)
    -   주문 생성/수정/취소 시 **상품 재고를 직접 제어**합니다.
    -   주문 상태(ORDER, CANCEL)로 생명주기를 관리합니다.

---

## 🌐 API 엔드포인트

### 📦 Product API (상품 관리)

| **Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| GET | /products | 전체 상품 목록 조회 |
| GET | /products/{id} | 상품 상세 정보 조회 |
| POST | /products | 신규 상품 등록 |
| PATCH | /products/{id} | 상품 정보 수정 |
| DELETE | /products/{id} | 상품 논리 삭제 (DELETED) |

### 🧾 Order API (주문 관리)

| **Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| GET | /orders | 전체 주문 목록 조회 |
| GET | /orders/{id} | 주문 상세 정보 조회 |
| POST | /orders | 주문 생성 (재고 차감) |
| PATCH | /orders/{id} | 주문 수량 변경 |
| DELETE | /orders/{id} | 주문 취소 (재고 복구) |

---

## 📌 설계 포인트

-   **도메인 중심 설계**: 재고 차감 및 복구 로직을 엔티티 내부에 구현하여 응집도를 높였습니다.
-   **안정적인 응답**: ApiResponse를 통해 모든 API 응답 형식을 통일했습니다.
-   **데이터 보존**: Soft Delete를 적용하여 삭제 시 실제 데이터를 지우지 않고 상태값만 변경합니다.

---

## 🚀 기술 스택

-   **Framework**: Spring Boot 3.x
-   **Language**: Java 17
-   **ORM**: Spring Data JPA (Hibernate)
-   **Database**: H2 (In-memory)
-   **Library**: Lombok, Jakarta Validation
