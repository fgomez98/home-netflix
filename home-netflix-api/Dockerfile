#FROM gradle:7.1.1-jdk8 as builder
#
#WORKDIR /builder
#
#ADD . /builder
#
#RUN /builder/gradlew build  --stacktrace

FROM openjdk:8 AS builder
ENV BUILDER_HOME=/builder
WORKDIR $BUILDER_HOME
COPY build.gradle.kts $BUILDER_HOME
COPY settings.gradle.kts $BUILDER_HOME
COPY gradlew $BUILDER_HOME
COPY gradle $APP_HOME

RUN ls

RUN ./gradlew build || return 0

COPY . .

RUN ./gradlew build

#--no-daemon bootJar


FROM openjdk:8-jre-alpine

RUN mkdir -p /var/mp4s

WORKDIR /app

EXPOSE 8080

COPY --from=builder /builder/build/libs/home-netflix-api-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "home-netflix-api-0.0.1-SNAPSHOT.jar"]