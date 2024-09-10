FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/nesine_cucumber_mobil-1.0-SNAPSHOT.jar /app/nesine_cucumber_mobil.jar

ENTRYPOINT ["java", "-jar", "nesine_cucumber_mobil.jar"]
