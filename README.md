# eshop

Springboot 2 based microservices application running on docker

## How to run it

1. java 8
2. node/npm
3. docker and docker-compose
4. ./build.sh
5. open the application in browser: http://localhost:8085

### Use curl

```Shell
curl -v -H'Content-Type:application/json' -d'{"name": "haha"}' http://localhost:8085/api/v1/catalog/items
curl -v http://localhost:8085/api/v1/catalog/items
```

## development notes

1. [Spring 5](spring5-notes.md)
2. javascript work flow
   1. start application by running ./build.sh
   2. npm run debug
   3. vs code debug in chrome

## Misc

Stop all services and clean up dangling images

```Shell
docker-compose down && docker rmi $(docker images -f dangling=true -q)
```
