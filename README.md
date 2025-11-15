# Date-Time Service

This Spring Boot application exposes a `GET /api/time` endpoint that returns the current date and time for four major cities (New York, London, Tokyo, Sydney). Each response entry includes the country, city, IANA time zone, and an ISO-8601 timestamp, making it a handy starter for time-aware dashboards or sample REST integrations.

## Running Locally

```bash
./mvnw spring-boot:run
```

Then visit `http://localhost:8080/api/time` to view the JSON payload.

## Testing

```bash
./mvnw test
```

## Learn More

- [Spring Boot Web Starter](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web) – auto-configured web stack used to host the REST controller.
- [Project Lombok](https://projectlombok.org/) – generates immutable DTO boilerplate via annotations like `@Value`, `@Builder`, and `@Jacksonized`.
- [Maven Wrapper](https://maven.apache.org/wrapper/) – ships a reproducible Maven runtime so contributors don’t need Maven pre-installed.

