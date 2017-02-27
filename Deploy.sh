#!/bin/bash
#make clean install of server
mvn clean install
# shutdown remote server and remove war file
ssh root@207.154.210.175 '/home/artvovc/apache-tomcat*/bin/./shutdown.sh ;rm -rf /home/artvovc/apache-tomcat*/webapps/serv*'
# copy war serv on root
cp nnServer-WebServer/target/*war serv.war
#send war folder to remote serv
scp serv.war root@207.154.210.175:/home/artvovc/apache-tomcat*/webapps/
# start remote server
ssh root@207.154.210.175 '/home/artvovc/apache-tomcat*/bin/./startup.sh '

rm -f serv.war
