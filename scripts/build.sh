#! /bin/bash
clear
echo "----------------------------------"
echo "1 - Clean Docker" 
echo "----------------------------------"
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images -q)
docker volume rm $(docker volume ls -q)
echo "----------------------------------"
echo "2 - Configuring Tpack application"
echo "----------------------------------"
cp settings.xml $HOME/.m2/
cd ..
mvn replacer:replace

echo "----------------------------------"
echo "3 - Building Tpack Web interface"
echo "----------------------------------"
cd ui
npm install
npm run build --fix
cp -a dist/* ../src/main/webapp
cd ..

echo "---------------------------------"
echo "4 - Building Tpack microservice"
echo "---------------------------------"
mvn clean package

echo "-------------------------------"
echo "5 - Starting Tpack application"
echo "------------------------------"
docker-compose up -d