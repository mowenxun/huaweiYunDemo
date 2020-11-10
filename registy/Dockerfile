#FROM openjdk:8-jre-slim
#MAINTAINER xuxueli
#
#ENV PARAMS=""
#
#ENV TZ=PRC
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#
## ADD target/xxl-job-admin-*.jar /app.jar
#ADD xxl-job-admin-*.jar /app.jar
#
#ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]

# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
FROM iron/java:1.8

USER root

COPY demo.jar /home/