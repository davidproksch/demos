#!/bin/bash

. ./.env

projectCount=`oc get projects | grep -v grep | grep ${PROJECT_NAME} | wc -l`

if [ ${projectCount} -ne 0 ]
then
	echo "Project ${PROJECT_NAME} already exists"
else
	oc new-project ${PROJECT_NAME}
fi
