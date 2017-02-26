#!/bin/bash 
#remove target folderc
rm -rf nnServer-WebServer/target
#make clean install of server
mvn clean install
# shutdown remote server and remove war file
ssh root@207.154.210.175 '/home/artvovc/tomcat/apache-tomcat-7.0.70/bin/./shutdown.sh ;rm -f /home/artvovc/tomcat/apache-tomcat-7.0.70/webapps/*war'  
# copy war serv on root
cp nnServer-WebServer/target/*war serv.war
#send war folder to remote serv
scp serv.war root@207.154.210.175:/home/artvovc/tomcat/apache-tomcat-7.0.70/webapps/
# start remote server
ssh root@207.154.210.175 '/home/artvovc/tomcat/apache-tomcat-7.0.70/bin/./startup.sh '  
