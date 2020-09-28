

FROM gradle as compile
COPY . /home/source/java
WORKDIR /home/source/java
USER root
RUN chown -R gradle /home/source/java
USER gradle
RUN gradle clean build
FROM openjdk:8-jre-alpine
ENV SPRING_PROFILES_ACTIVE=prod
COPY --from=compile "/home/source/java/build/libs/synchro-0.0.1-SNAPSHOT.jar" synchro.jar
EXPOSE ${PORT}
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","synchro.jar"]