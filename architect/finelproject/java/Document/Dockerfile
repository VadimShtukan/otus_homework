FROM openjdk:14

ADD ./target/Document-0.0.3.jar /usr/local/svm/Document-0.0.3.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/Document-0.0.3.jar
EXPOSE 8002