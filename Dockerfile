# the first stage of our build will use a maven 3 parent image
FROM maven:3.9.9-amazoncorretto-21-debian-bookworm  AS MAVEN_BUILD
# copy the pom and src code to the container
COPY ./ ./
# package our application code
RUN mvn clean package

FROM openjdk:21-ea-jdk-oracle
WORKDIR /home
EXPOSE 9000
# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /target/poc-0.0.1-SNAPSHOT.jar  poc.jar
ENTRYPOINT ["java","-jar","poc.jar"]

