#!/bin/bash

. ./.env

podCnt=`oc get pods | grep "demo-mysql" | wc -l`

if [ ${podCnt} -eq 0 ]
then
	echo "Service demo-mysql is not running"
else
	podName=`oc get pods | grep "demo-mysql" | awk '{print $1}'`
	oc cp ../ddl/district.sql mbodemo/${podName}:/tmp/district.sql
	oc cp ../data/district.txt mbodemo/${podName}:/tmp/district.txt
	oc cp ../bin/district.sh mbodemo/${podName}:/tmp/district.sh
	oc cp ../bin/.env mbodemo/${podName}:/tmp/.env

	oc rsh ${podName} "/tmp/district.sh"
fi
