FROM openjdk:17-slim
COPY build/libs/wecan-0.0.1-SNAPSHOT.jar app.jar
ENV TZ Asia/Seoul
ENTRYPOINT ["java", "-jar", "app.jar"]
