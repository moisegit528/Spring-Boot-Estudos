FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

COPY . .

RUN mvn clean install

FROM eclipse-temurin:17-jdk
EXPOSE 8080
COPY --from=build /target/todolist-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
