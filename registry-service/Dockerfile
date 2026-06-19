# ============================================================================
#  Dockerfile pentru REGISTRY-SERVICE
#  Build multi-stage: compileaza cu Maven, apoi ruleaza jar-ul pe un JRE mic.
#  Contextul de build trebuie sa fie RADACINA proiectului (vezi docker-compose.yml),
#  pentru ca avem nevoie de pom-ul parinte si de structura multi-modul.
# ============================================================================

FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn -q -pl registry-service -am clean package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/registry-service/target/registry-service-1.0.0.jar registry-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "registry-service.jar"]
