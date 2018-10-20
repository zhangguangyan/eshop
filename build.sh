#!/bin/bash
set -e
#build jars and minify js
./gradlew clean build
cd webSPA && ./build.sh
cd ..
#run the applications
docker-compose -f docker-compose.yml build && docker-compose -f docker-compose.yml up
