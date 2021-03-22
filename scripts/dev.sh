#! /bin/bash
clear
echo "--------------------------------"
echo "1 - Building Tpack microservice"
echo "--------------------------------"
cp settings.xml $HOME/.m2/
cd ..
mvn clean replacer:replace package

echo "---------------------------"
echo "2 - Starting Open Liberty"
echo "---------------------------"
mvn io.openliberty.tools:liberty-maven-plugin:dev -f "pom.xml"