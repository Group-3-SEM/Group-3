#FROM openjdk:latest
#COPY ./target/Group-3-0.1.0.1-jar-with-dependencies.jar /tmp
#WORKDIR /tmp
#ENTRYPOINT ["java", "-jar", "Group-3-0.1.0.1-jar-with-dependencies.jar"]

FROM mysql
WORKDIR /tmp
COPY world.sql /tmp/
COPY world.sql /docker-entrypoint-initdb.d
ENV MYSQL_ROOT_PASSWORD example