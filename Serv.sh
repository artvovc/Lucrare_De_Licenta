#!/bin/bash 

ssh root@207.154.210.175 'rm -f /home/artvovc/tomcat/apache-tomcat-7.0.70/webapps/*war'  

cp nnServer-WebServer/target/*war serv.war

scp serv.war root@207.154.210.175:/home/artvovc/tomcat/apache-tomcat-7.0.70/webapps/
