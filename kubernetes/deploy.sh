#!/bin/bash

#aby wykonac skrypt bez podawania hasła:
#sudo visudo
# potem dodajesz wpis: 
#marcin ALL = NOPASSWD: /home/marcin/SpringBootKafkaListener/kubernetes/deploy.sh, /sbin/restart
#
#wc -l $(ls)
# echo  'ABC'$(echo "XXX" )

cd /home/marcin/SpringBootKafkaListener/docker
docker build -f dockerfile -t springbootkafkalistener . && docker tag springbootkafkalistener marcinkasinski/springbootkafkalistener && docker push marcinkasinski/springbootkafkalistener
echo "End pushing"


kubectl delete pod $( kubectl get pod | grep springbootkafkalistener-deployment  | head -n1 | sed -e 's/\s.*$//' )




echo "End deleting pod"
