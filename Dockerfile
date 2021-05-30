FROM maven:3.5-jdk-8-alpine as builder

WORKDIR /app

RUN mvn clean

FROM openjdk:11-jre-slim

COPY --from=builder /home/gradle/project/build/libs/app.jar app.jar

VOLUME /tmp

EXPOSE 8080

ENV TIMEOUT=30

CMD echo "The application will start in ${TIMEOUT}s..." && sleep ${TIMEOUT} && \
     java -Djava.security.egd=file:/dev/./urandom -jar app.jar
