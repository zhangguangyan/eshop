FROM openjdk:8-jdk
VOLUME /tmp
COPY build/libs/basket-0.1.0.jar /app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
