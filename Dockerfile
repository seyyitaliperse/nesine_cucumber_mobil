# Base image with Maven and JDK 17
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Copy project files into the container
COPY . /app

# Set the working directory inside the container
WORKDIR /app

# Install dependencies and build the project
RUN mvn clean package

# Final stage: create a lightweight image with JRE
FROM eclipse-temurin:17-jre

# Copy the application JAR file from the build stage
COPY --from=build /app/target/nesine-cucumber.jar /app/nesine-cucumber.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/nesine-cucumber.jar"]
