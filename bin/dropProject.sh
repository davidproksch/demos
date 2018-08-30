#!/bin/bash

. ./.env

projectCount=`oc get projects | grep -v grep | grep ${PROJECT_NAME} | wc -l`

if [ ${projectCount} -ne 1 ]
then
	echo "Project ${PROJECT_NAME} does not exist"
else
	oc delete project ${PROJECT_NAME}
fi
