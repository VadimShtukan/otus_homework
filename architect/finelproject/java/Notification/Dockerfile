FROM openjdk:14

ADD ./target/Notification-0.0.1.jar /usr/local/svm/Notification-0.0.1.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/Notification-0.0.1.jar
EXPOSE 8005