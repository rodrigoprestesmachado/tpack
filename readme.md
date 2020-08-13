# Tpack XS application

This repository implements an Web application for Tpack XS application

## Run with Maven and Docker Compose

The easer way to install and execute the Tpack XS application is to use docker-composer command. Once [Maven](https://maven.apache.org) and [Docker](https://www.docker.com) Compose installed, you can run the bellow commands:

    mvn clean package

    docker-compose up -d

The Web application will be executed in 9080 door. We put a PhpMyAdmin running in 80 door, the default database root and password is: tapack. For more information, please, take a look in docker-compose.yml file.

## Build a docker image

To create a Docker image for Tpack XS application:

    mvn clean package

    docker build -t tpack-image .

    docker run -d --name tpack-app -p 9080:9080 -p 9443:9443 tpack-image

### Development mode

During development, you can use Open Liberty's development mode (dev mode) to code while observing and testing your changes on the fly.

    mvn clean package
    mvn liberty:dev