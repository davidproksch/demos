#!/bin/bash

. ./.env

oc create secret generic mysql-demo-user --type=string \
    --from-literal=mysql_user=${MYSQL_USER}

oc create secret generic mysql-demo-password --type=string \
    --from-literal=mysql_password=${MYSQL_PASSWORD}

oc create secret generic mysql-demo-database --type=string \
    --from-literal=mysql_database=${MYSQL_DATABASE}

oc create secret generic mysql-demo-service --type=string \
    --from-literal=mysql_service=${MYSQL_SERVICE}

oc create secret generic mysql-demo-port --type=string \
    --from-literal=mysql_port=${MYSQL_PORT}
