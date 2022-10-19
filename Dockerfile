FROM openjdk:11
RUN mkdir /app
WORKDIR app
EXPOSE 8080
ARG JAR_FILE=build/libs/rsoi-2022-lab1-ci-cd-Shemich.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=$PORT","/app/app.jar"]