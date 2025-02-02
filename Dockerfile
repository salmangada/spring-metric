# Stage 1: Build
FROM openjdk:17 AS build

# Copy the Maven configuration files and wrapper
COPY pom.xml mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

# Copy the application source code
COPY src src

# Package the application
RUN ./mvnw package -Dmaven.test.skip

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /metric-app

# Copy the packaged JAR from the build stage
COPY --from=build target/*.jar metric-app.jar

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "metric-app.jar"]