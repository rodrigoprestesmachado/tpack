#! /bin/bash
clear
echo "----------------------------------"
echo "1 - Building Tpack user interface"
echo "----------------------------------"
mvn replacer:replace
cd ../ui
npm install
npm run build --fix
cp -a dist/* ../src/main/webapp

echo "---------------------------------------------------"
echo "2 - Copying settings.xml do ./m2 (Maven) directory"
echo "---------------------------------------------------"
cd ../scripts
cp settings.xml $HOME/.m2/

echo "---------------------------------"
echo "3 - Building Tpack Microservice"
echo "---------------------------------"
cd ..
mvn clean package

echo "-----------------------------"
echo "4 - Starting docker-compose"
echo "-----------------------------"
docker-compose up -d