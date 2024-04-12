FROM openjdk:18.0.2.1-jdk-slim-buster
MAINTAINER Tim Sokollek (tim.sokollek@ipa.fraunhofer.de)

ENV PROMETHEUS_HOST="prometheus" \
    PROMETHEUS_PORT=9090

RUN apt update && \
    apt install -y curl jq

COPY target/*-exec.jar /app/app.jar
COPY src/main/docker/startup.sh /app/startup.sh
RUN chmod +x /app/startup.sh

WORKDIR /app

ENTRYPOINT ["/bin/bash", "-c", "/app/startup.sh"]
