FROM ubuntu:18.04
RUN apt-get -y update && apt-get -y upgrade \
	&& apt-get install -y openjdk-8-jre
ADD lesson2-0.0.1.jar /usr/local/svm/lesson2-0.0.1.jar

CMD ["java", "-jar", "/usr/local/svm/lesson2-0.0.1.jar"]
EXPOSE 8000
