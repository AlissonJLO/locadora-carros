FROM openjdk:22-jdk
WORKDIR /app
COPY target/locadora-carros-1.0-SNAPSHOT.jar /app/locadora-carros.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "locadora-carros.jar"]
