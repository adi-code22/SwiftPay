FROM openjdk:11
ADD ./target/ApiGateway-0.0.1-SNAPSHOT.jar /usr/src/ApiGateway-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "ApiGateway-0.0.1-SNAPSHOT.jar"]