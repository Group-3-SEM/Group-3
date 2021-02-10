FROM openjdk:latest
COPY ./target/Group-3-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group-3-0.1.0.1-jar-with-dependencies.jar"]