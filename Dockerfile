FROM openjdk:8-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/beers-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 5101

CMD java -jar beers-api-1.0.0-SNAPSHOT.jar