FROM openjdk:8-jre-alpine
ADD tin-backend/target/tin-backend-0.0.1-SNAPSHOT.jar tin-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tin-backend-0.0.1-SNAPSHOT.jar"]
