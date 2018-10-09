FROM openjdk:9
MAINTAINER Marcin Kasiński <marcin.kasinski@gmail.com> 



RUN mkdir /usr/src/myapp
RUN mkdir /usr/src/myapp/logs
RUN touch /usr/src/myapp/logs/application.log

########################################## starting Logstash  ##########################################

# install Filebeat
#ENV FILEBEAT_VERSION=filebeat-5.5.2-amd64.deb
ENV FILEBEAT_VERSION=filebeat-6.4.2-amd64.deb
RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/${FILEBEAT_VERSION}

#ADD ${FILEBEAT_VERSION} /${FILEBEAT_VERSION}
RUN dpkg -i ${FILEBEAT_VERSION} \
  && rm ${FILEBEAT_VERSION}

# configure Filebeat
ADD filebeatstokafka.yml /etc/filebeat/filebeat.yml
#ADD filebeatstologstash.yml /etc/filebeat/filebeat.yml
#ADD filebeatstoelasticsearch.yml /etc/filebeat/filebeat.yml

#RUN /etc/init.d/filebeat start

ADD start.sh /usr/src/myapp/start.sh
RUN sed -i -e 's/\r//g' /usr/src/myapp/start.sh

COPY SpringBootKafkaListener-0.0.1-SNAPSHOT.jar /usr/src/myapp/SpringBootKafkaListener-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/myapp
#CMD /etc/init.d/filebeat start && java -jar SpringBootWeb-0.0.1-SNAPSHOT.jar
#CMD ls -l /
#CMD wget -O https://artifacts.elastic.co/downloads/beats/filebeat/${FILEBEAT_VERSION} 
#CMD cat /filebeat_5.5.2_amd64.deb
#CMD /etc/init.d/filebeat start 
EXPOSE 9191


RUN chmod +x /usr/src/myapp/start.sh
ENTRYPOINT [ "/usr/src/myapp/start.sh" ]