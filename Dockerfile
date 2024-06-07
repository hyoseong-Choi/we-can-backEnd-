FROM amazoncorretto:22.0.1-alpine
COPY build/libs/wecan-0.0.1-SNAPSHOT.jar app.jar
ENV TZ Asia/Seoul
ENTRYPOINT ["java", "-jar", "app.jar"]
