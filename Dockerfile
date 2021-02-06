FROM openjdk:latest
COPY ./target/classes/Group3 /tmp/Group3
WORKDIR /tmp
ENTRYPOINT ["java", "Group3.App"]