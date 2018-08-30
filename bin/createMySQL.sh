#!/bin/bash

. ./.env

oc new-app \
    -e MYSQL_USER=${MYSQL_USER} \
    -e MYSQL_PASSWORD=${MYSQL_PASSWORD} \
    -e MYSQL_DATABASE=${MYSQL_DATABASE} \
    registry.access.redhat.com/openshift3/mysql-55-rhel7 \
    --name=demo-mysql
