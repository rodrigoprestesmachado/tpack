#! /bin/bash
clear
echo "----------------------------------"
echo "1 - Configuring Tpack application"
echo "----------------------------------"
cp settings.xml $HOME/.m2/
cd ..
mvn replacer:replace

echo "----------------------------------"
echo "2 - Building Tpack Web interface"
echo "----------------------------------"
cd ui
npm install
npm run build --fix
cp -a dist/* ../src/main/webapp
cd ..

echo "---------------------------------"
echo "3 - Building Tpack microservice"
echo "---------------------------------"
mvn clean package

echo "-------------------------------"
echo "4 - Starting Tpack application"
echo "------------------------------"
docker-compose up -d