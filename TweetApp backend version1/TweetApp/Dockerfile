FROM openjdk:8
EXPOSE 8081
ADD target/tweetapp-spring-boot.jar tweetapp-spring-boot.jar
ENTRYPOINT ["java","-jar","/tweetapp-spring-boot.jar"]
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
