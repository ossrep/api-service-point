# api-service-point

Utility/energy service point management REST API built with Quarkus.

## Technology Stack

- Java 25 (LTS)
- Quarkus 3.33 (LTS)
- PostgreSQL with Hibernate ORM / Panache
- Kafka via Smallrye Reactive Messaging
- Flyway for schema migrations
- OIDC for authentication

## Local Development

### Prerequisites

- Java 25
- Maven 3.9+
- Podman (for Dev Services)

### Run in dev mode

```shell
JAVA_HOME=/usr/lib/jvm/java-25-openjdk ./mvnw quarkus:dev
```

Dev Services will automatically start PostgreSQL (port 5431) and Kafka containers.

- API: [http://localhost:8080](http://localhost:8080)
- OpenAPI: [http://localhost:8080/q/openapi](http://localhost:8080/q/openapi)
- Swagger UI: [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

### Build

```shell
JAVA_HOME=/usr/lib/jvm/java-25-openjdk ./mvnw package
```

### Container build

```shell
JAVA_HOME=/usr/lib/jvm/java-25-openjdk ./mvnw package
podman build -f Containerfile -t quay.io/ossrep/api-service-point .
```

## REST Endpoints

### ISOs - `/api/v1/isos`

| Method | Path        | Roles       | Description      |
| ------ | ----------- | ----------- | ---------------- |
| `GET`  | `/`         | admin, user | List all ISOs    |
| `GET`  | `/{isoId}`  | admin, user | Get ISO by ID    |

### TDSPs - `/api/v1/tdsps`

| Method | Path         | Roles       | Description                      |
| ------ | ------------ | ----------- | -------------------------------- |
| `GET`  | `/`          | admin, user | List all TDSPs (filter: `?code=`)|
| `GET`  | `/{tdspId}`  | admin, user | Get TDSP by ID                   |

### Service Points - `/api/v1/service-points`

| Method   | Path                | Roles       | Description               |
| -------- | ------------------- | ----------- | ------------------------- |
| `GET`    | `/`                 | admin, user | List all service points   |
| `GET`    | `/{servicePointId}` | admin, user | Get service point by ID   |
| `POST`   | `/`                 | admin       | Create a service point    |
| `PUT`    | `/{servicePointId}` | admin       | Update a service point    |
| `PUT`    | `/bulk`             | admin       | Bulk upsert by ESIID      |
| `DELETE` | `/{servicePointId}` | admin       | Delete a service point    |

