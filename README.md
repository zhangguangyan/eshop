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

## Misc

Stop all services and clean up dangling images

```Shell
docker-compose down && docker rmi $(docker images -f dangling=true -q)
```
