FROM openjdk:20-jdk

WORKDIR /app

COPY ./target/msvc-vehicles-0.0.1-SNAPSHOT.jar .

EXPOSE 5001

ENTRYPOINT ["java", "-jar", "msvc-vehicles-0.0.1-SNAPSHOT.jar"]
