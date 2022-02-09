FROM openjdk:11-jre-slim-buster
MAINTAINER zemec:kafka_consumer
COPY target/kafka_consumer-1.0-SNAPSHOT.jar kafka_consumer-1.0-SNAPSHOT.jar
CMD exec java -jar /kafka_consumer-1.0-SNAPSHOT.jar