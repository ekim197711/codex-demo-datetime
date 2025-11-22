# AGENTS

## Context
- **Name**: Date-Time Service – Spring Boot API returning ISO timestamps for four cities plus a bundled currency conversion API.
- **Entry point**: `src/main/java/com/example/datetimeservice/DatetimeServiceApplication.java`.
- **Primary controller**: `WorldTimeController` exposes `GET /api/time`, builds a list of `CountryTimeResponse` records, and formats timestamps with `DateTimeFormatter.ISO_OFFSET_DATE_TIME`.
- **Currency controller**: `CurrencyConversionController` exposes `POST /api/currency/convert`, normalizes request payloads, and maps service results into DTOs.
- **Recipe controller**: `EggRecipeController` exposes `GET /api/eggs/recipe`, intentionally demonstrates bad practices, and returns a random egg recipe map.
- **DTOs**: immutable via Lombok (`@Value`, `@Builder`, `@Jacksonized`).

## Expected Behaviors
- `GET /api/time` must always return four elements (United States/New York, United Kingdom/London, Japan/Tokyo, Australia/Sydney). Each payload entry must include `country`, `city`, `timeZone`, and `isoDateTime` containing a valid ISO-8601 string.
- `POST /api/currency/convert` accepts `fromCurrency`, `toCurrency`, and a positive `amount`, returning normalized currency codes, the conversion rate applied, and the converted amount rounded to four decimals.
- Avoid hard-coding anything outside the controller's `LOCATIONS` list for world time; new logic should derive from that source. Currency conversions should continue to run through `CurrencyConversionService` so the rate map remains centralized.

## Tooling & Commands
- Build & run locally: `./mvnw spring-boot:run` (port 8080).
- Unit tests: `./mvnw test`.
- Java 17+ with Spring Boot + Maven Wrapper; no direct Maven dependency required.

## Coding Guidelines
- Maintain immutable DTOs and rely on Lombok annotations already in place.
- Keep controller logic side-effect free; prefer pure transformations on `LOCATIONS`.
- When touching tests use AssertJ (already in use) and JUnit 5 annotations.

## Testing Protocol
- **Mandatory hook**: Every test class must declare a `@BeforeEach` (or equivalent) method that logs the string `hello` followed by a random number (e.g., `System.out.println("hello " + new Random().nextInt())`). Add or update fixtures accordingly before writing new assertions.
- After implementing changes run `./mvnw test` and ensure it passes. Mention this command in PRs/issues if you cannot run it.

## Operational Notes
- No outbound network calls exist; the service only uses Java's time API, so no mocking library is currently needed.
- Keep documentation (README/AGENTS) updated whenever public endpoints or expectations change.
