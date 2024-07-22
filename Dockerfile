# Stage 1: Build the application using Maven with JDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the Docker image
COPY . .

# Build the project, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create a smaller final image using JDK 22
FROM openjdk:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage to the final image
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the application port
EXPOSE 8081

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
