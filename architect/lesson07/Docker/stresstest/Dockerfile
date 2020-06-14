FROM openjdk:10
ADD rest.client.load.lesson-07-0.1.2.jar /usr/local/svm/rest.client.load.lesson-07-0.1.2.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar -DRATE_SCAN=${RATE_SCAN} -DURL_FOR_SCAN=${URL_FOR_SCAN} -DAPP_HOST=${APP_HOST} /usr/local/svm/rest.client.load.lesson-07-0.1.2.jar
EXPOSE 8000
