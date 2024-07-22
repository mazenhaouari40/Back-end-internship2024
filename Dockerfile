# Use a Maven image with JDK 22
FROM maven:3.8.5-openjdk-22 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the Docker image
COPY . .

# Build the project, skipping tests
RUN mvn clean package -DskipTests

# Use a second stage to create a smaller final image
FROM openjdk:22-jdk

# Copy the built JAR file from the build stage to the final image
COPY --from=build /app/target/Backend-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose the application port
EXPOSE 8081

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
