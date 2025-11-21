# Date-Time Service

This Spring Boot application exposes:

- `GET /api/time` – returns the current date and time for four major cities (New York, London, Tokyo, Sydney). Each response entry includes the country, city, IANA time zone, and an ISO-8601 timestamp.
- `POST /api/currency/convert` – converts an amount from one supported currency into another using bundled USD-relative rates and returns the converted amount plus the rate that was applied.

## Running Locally

```bash
./mvnw spring-boot:run
```

Then visit `http://localhost:8080/api/time` to view the time payload or `POST http://localhost:8080/api/currency/convert` with a JSON body such as:

```json
{
  "fromCurrency": "USD",
  "toCurrency": "EUR",
  "amount": 50
}
```

The response includes the normalized currency codes, original amount, the from→to conversion rate, and the converted amount (rounded to four decimals).

## Testing

```bash
./mvnw test
```

## Learn More

- [Spring Boot Web Starter](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web) – auto-configured web stack used to host the REST controller.
- [Project Lombok](https://projectlombok.org/) – generates immutable DTO boilerplate via annotations like `@Value`, `@Builder`, and `@Jacksonized`.
- [Maven Wrapper](https://maven.apache.org/wrapper/) – ships a reproducible Maven runtime so contributors don’t need Maven pre-installed.
