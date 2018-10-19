#!/bin/bash
./gradlew clean :api.gateway:web.bff.shopping:docker :services:catalog:docker :services:ordering:docker
cd webSPA && ./build.sh
cd .. && docker-compose -f docker-compose.yml up