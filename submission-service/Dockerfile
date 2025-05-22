FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY target/submission-service-0.0.1-SNAPSHOT.jar /app/submission-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "submission-service.jar"]