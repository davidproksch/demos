#!/bin/bash

. ./.env

oc create secret generic open-weather-api --type=string \
    --from-literal=weather_api_key=${WEATHER_API_KEY}

