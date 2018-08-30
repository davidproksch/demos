#!/bin/bash

. ./.env

podCnt=`oc get pods | grep "demo-mysql" | wc -l`

if [ ${podCnt} -eq 0 ]
then
	echo "Service demo-mysql is not running"
else
	podName=`oc get pods | grep "demo-mysql" | awk '{print $1}'`
	oc cp ../ddl/customer.sql mbodemo/${podName}:/tmp/customer.sql
	oc cp ../data/customer.txt mbodemo/${podName}:/tmp/customer.txt
	oc cp ../bin/customer.sh mbodemo/${podName}:/tmp/customer.sh
	oc cp ../bin/.env mbodemo/${podName}:/tmp/.env

	oc rsh ${podName} "/tmp/customer.sh"
fi
