FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY target/problem-service-0.0.1-SNAPSHOT.jar /app/problem-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "problem-service.jar"]