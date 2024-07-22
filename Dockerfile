FROM maven:3.8.5-openjdk-22 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package

#FROM openjdk:22-alpine
#WORKDIR /app
#COPY --from=build /app/target/*.jar /app/app.jar

# Copy the built JAR file from the build stage to the final image
#COPY --from=build /app/target/Backend-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose the application port
#EXPOSE 8081

# Set the entrypoint to run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]
