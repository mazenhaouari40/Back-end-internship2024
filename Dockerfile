#FROM ubuntu:latest
#LABEL authors="MSI"

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
COPY --from=build /target/Backend-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demo.jar"]

#ENTRYPOINT ["top", "-b"]