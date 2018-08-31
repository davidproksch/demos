#!/bin/bash

. ./.env

oc delete secret warehouse-rest-svc

oc create secret generic warehouse-rest-svc --type=string \
    --from-literal=warehouse_rest_svc="http://warehouse-00-mbodemo.6923.rh-us-east-1.openshiftapps.com"

oc create secret generic warehouse-rest-path --type=string \
    --from-literal=warehouse_rest_path="/all"
