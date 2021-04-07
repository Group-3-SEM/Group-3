FROM openjdk:latest
COPY ./target/Group3.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group3.jar", "db:3306"]

