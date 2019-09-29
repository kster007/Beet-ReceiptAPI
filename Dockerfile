FROM openjdk:8-jdk-alpine
RUN apk add --no-cache tzdata
ENV TZ America/Mexico_City
VOLUME /tmp
ADD target/ReceiptApi-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xmx300m"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
