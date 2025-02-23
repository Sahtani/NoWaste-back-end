FROM openjdk:21-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/NoWaste-back-end-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
