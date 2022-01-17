FROM openjdk:11-jre-slim-buster
MAINTAINER zemec:kafka_producer
COPY target/kafka_producer-1.0-SNAPSHOT.jar kafka_producer-1.0-SNAPSHOT.jar
CMD exec java -jar /kafka_producer-1.0-SNAPSHOT.jar