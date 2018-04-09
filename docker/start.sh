#!/bin/bash

#ls -l /
#cat /etc/init.d/filebeat
/etc/init.d/filebeat start
java -jar SpringBootKafkaListener-0.0.1-SNAPSHOT.jar
