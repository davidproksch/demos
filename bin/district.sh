#!/bin/bash

. /tmp/.env

PODNAME=`hostname`

mysql -h ${PODNAME} -u ${MYSQL_USER} \
 --password=${MYSQL_PASSWORD} ${MYSQL_DATABASE} < /tmp/district.sql
