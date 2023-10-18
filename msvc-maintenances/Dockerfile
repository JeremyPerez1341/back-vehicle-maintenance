FROM openjdk:20-jdk

WORKDIR /app

COPY ./target/msvc-maintenances-0.0.1-SNAPSHOT.jar .

EXPOSE 5002

ENTRYPOINT ["java", "-jar", "msvc-maintenances-0.0.1-SNAPSHOT.jar"]