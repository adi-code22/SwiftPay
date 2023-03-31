FROM openjdk:11
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /usr/src/EurekaServer-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "EurekaServer-0.0.1-SNAPSHOT.jar"]