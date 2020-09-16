# Tpack XS application installation

This repository implements a Web application for Tpack XS application

## Run with Maven and Docker Compose

The easiest way to install and execute the Tpack XS application is to use docker-composer command. Once [Maven](https://maven.apache.org) and [Docker](https://www.docker.com) Compose installed, you can run the bellow commands:

```shell
git clone https://github.com/rodrigoprestesmachado/tpack
cd tpack
mvn clean package
docker-compose up -d
```

The Web application will run at 9080 door. We put a PhpMyAdmin running at 80 door, the default database user and password is: tpack. For more information, please, take a look in docker-compose.yml file in the project’s root directory.

## Build a docker image

To create a Docker image for Tpack XS application:

```shell
git clone https://github.com/rodrigoprestesmachado/tpack
mvn clean package
cd tpack
docker build -t tpack-image .
```

To execute the container:

```shell
docker run -d --name tpack -p 9080:9080 -p 9443:9443 tpack-image
```

### Development mode

During development, you can use [Open Liberty's](https://openliberty.io) development mode (dev mode) to code while observing and testing your changes on the fly.

    mvn clean package
    mvn liberty:dev