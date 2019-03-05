#!/bin/bash
set -e
#build jars and minify js
./gradlew clean build
cd webSPA && ./build.sh
cd ..
#build images
docker-compose -f docker-compose.yml build
