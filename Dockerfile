FROM openjdk:19-jdk-slim-bullseye
MAINTAINER Matthias Schneider (matthias.schneider@ipa.fraunhofer.de)

ENV PROMETHEUS_HOST="prometheus" \
    PROMETHEUS_PORT=9090

RUN apt update && \
    apt install -y curl jq

COPY target/*-exec.jar /app/app.jar
COPY src/main/docker/startup.sh /app/startup.sh
RUN chmod +x /app/startup.sh

WORKDIR /app

ENTRYPOINT ["/bin/bash", "-c", "/app/startup.sh"]
