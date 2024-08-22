FROM eclipse-temurin:17
ADD target/event-registration-0.0.1-SNAPSHOT.jar event-registration.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "event-registration.jar"]