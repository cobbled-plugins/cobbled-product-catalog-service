FROM amazoncorretto:17-alpine3.17-jdk

EXPOSE 8080

VOLUME /usr/local/application
COPY ./build/libs/*.jar /usr/local/application/server.jar

WORKDIR /usr/local/application

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "server.jar"]
