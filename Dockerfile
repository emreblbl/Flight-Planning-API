FROM openjdk:8-jdk-alpine
MAINTAINER emreblbl
COPY target/flight-0.0.1-SNAPSHOT.jar flight-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/flight-0.0.1-SNAPSHOT.jar"]
