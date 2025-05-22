FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]