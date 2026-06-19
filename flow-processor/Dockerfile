# ============================================================================
#  Dockerfile pentru FLOW-PROCESSOR (procesorul de flux / nodul de chat)
#  Aceeasi imagine este pornita de mai multe ori (alice, bob, charlie, ...),
#  fiecare instanta in propria masina virtuala (container).
#  Contextul de build trebuie sa fie RADACINA proiectului (vezi docker-compose.yml).
# ============================================================================

FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn -q -pl flow-processor -am clean package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/flow-processor/target/flow-processor-1.0.0.jar flow-processor.jar
ENTRYPOINT ["java", "-jar", "flow-processor.jar"]
