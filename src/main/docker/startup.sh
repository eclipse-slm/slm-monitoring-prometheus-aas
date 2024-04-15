#! /bin/bash

# Wait until Prometheus is running
until curl -m 5 -s --location --request GET "http://$PROMETHEUS_HOST:$PROMETHEUS_PORT/-/healthy"; do
  echo "Prometheus is unavailable -> sleeping"
  sleep 1
done

# Start App
java -jar -Djava.security.egd=file:/dev/./urandom /app/app.jar
