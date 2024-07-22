#FROM ubuntu:latest
#LABEL authors="MSI"

FROM maven:3.8.5-openjdk-22 AS build
COPY . .
RUN mvn clean package -DskipTests
COPY --from=build /app/target/Backend-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demo.jar"]

#ENTRYPOINT ["top", "-b"]
