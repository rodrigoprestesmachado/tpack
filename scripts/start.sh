#! /bin/bash
clear
echo "----------------------------------------"
echo "1 - Building Vue user interface with NPM"
echo "----------------------------------------"
cd ../ui
npm install
vue-cli-service build --mode production --dest ../src/main/webapp --target app --modern --dashboard

echo "----------------------------------------"
echo "2 - Building microservice with Maven"
echo "----------------------------------------"
cd ..
mvn clean package

echo "----------------------------------------"
echo "3 - Starting Docker Compose"
echo "----------------------------------------"
docker-compose up -d