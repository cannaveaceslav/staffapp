# First stage: complete build environment
FROM maven:3.8.4-openjdk-11 AS builder

# copy pom.xml and source code
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# package jar
RUN mvn clean package -DskipTests

RUN ls -lah target

# Second stage: minimal runtime environment
FROM openjdk:11-jre-slim

# copy jar from the first stage
COPY --from=builder ./target/backend-0.1.jar  backend-0.1.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","backend-0.1.jar"]

