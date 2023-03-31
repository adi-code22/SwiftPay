FROM openjdk:11
ADD ./target/ProductWebapp-0.0.1-SNAPSHOT.jar /usr/src/ProductWebapp-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "ProductWebapp-0.0.1-SNAPSHOT.jar"]