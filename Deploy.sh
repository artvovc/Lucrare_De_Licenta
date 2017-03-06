#!/bin/bash

mvn clean install

ssh root@207.154.210.175 '/home/artvovc/apache-tomcat*/bin/./shutdown.sh ;rm -rf /home/artvovc/apache-tomcat*/webapps/ROOT*'

cp nnServer-WebServer/target/*war ROOT.war

scp ROOT.war root@207.154.210.175:/home/artvovc/apache-tomcat*/webapps/

ssh root@207.154.210.175 '/home/artvovc/apache-tomcat*/bin/./startup.sh '
