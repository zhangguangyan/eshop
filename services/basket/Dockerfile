FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY build/libs/basket-0.1.0.jar /app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN apk update \
    && apk upgrade \
    && apk add --no-cache bash \
    && chmod +x /wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
