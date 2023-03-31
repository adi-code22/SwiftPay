FROM openjdk:11
ADD ./target/User-Service-2.7.9.jar /usr/src/User-Service-2.7.9.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "User-Service-2.7.9.jar"]