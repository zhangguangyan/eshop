FROM adoptopenjdk/openjdk8:alpine-jre
VOLUME /tmp
RUN apk update \
    && apk upgrade
COPY build/libs/web.bff.shopping-0.1.0.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
