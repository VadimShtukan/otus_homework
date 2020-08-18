FROM openjdk:14

ADD ./target/Document-0.0.4.jar /usr/local/svm/Document-0.0.4.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/Document-0.0.4.jar
EXPOSE 8002