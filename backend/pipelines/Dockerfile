# First stage: complete build environment
#FROM maven:3.8.4-openjdk-11 AS builder

FROM java:8
WORKDIR /srv/
ADD target/backend-0.1.jar backend-0.1.jar

# package jar
#RUN mvn clean package -DskipTests


# Second stage: minimal runtime environment
#FROM openjdk:11-jre-slim

# copy jar from the first stage
#COPY --from=builder ./target/backend-0.1.jar  backend.jar

EXPOSE 8088

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "backend-0.1.jar"]

