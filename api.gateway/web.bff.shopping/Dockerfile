FROM openjdk:8-alpine
VOLUME /tmp
COPY build/libs/web.bff.shopping-0.1.0.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]