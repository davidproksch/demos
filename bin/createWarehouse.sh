#!/bin/bash

. ./.env

podCnt=`oc get pods | grep "demo-mysql" | wc -l`

if [ ${podCnt} -eq 0 ]
then
	echo "Service demo-mysql is not running"
else
	podName=`oc get pods | grep "demo-mysql" | awk '{print $1}'`
	oc cp ../ddl/warehouse.sql mbodemo/${podName}:/tmp/warehouse.sql
	oc cp ../data/warehouse.txt mbodemo/${podName}:/tmp/warehouse.txt
	oc cp ../bin/warehouse.sh mbodemo/${podName}:/tmp/warehouse.sh
	oc cp ../bin/.env mbodemo/${podName}:/tmp/.env

	oc rsh ${podName} "/tmp/warehouse.sh"
fi
