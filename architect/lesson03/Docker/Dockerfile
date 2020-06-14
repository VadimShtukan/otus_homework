#FROM ubuntu:18.04
FROM openjdk:8-slim
#RUN apt-get -y update && apt-get -y upgrade \
#	&& apt-get install -y openjdk-8-jre
ADD lesson3-0.0.3.jar /usr/local/svm/lesson3-0.0.3.jar

CMD ["java", "-jar", "/usr/local/svm/lesson3-0.0.3.jar"]
EXPOSE 8000
