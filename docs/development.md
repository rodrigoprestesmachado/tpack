# Development

Once [Maven](https://maven.apache.org) and [Docker](https://www.docker.com) installed, you can run the bellow commands:

    git clone https://github.com/rodrigoprestesmachado/tpack
    cd tpack
    mvn clean package
    docker-compose up -d

The Web application will run at 9080 door. We put a PhpMyAdmin running at 80 door, the default database user and password is: tpack. For more information, please, take a look in docker-compose.yml file in the project’s root directory.

## Build a docker image

To create a Docker image for Tpack XS application:

    git clone https://github.com/rodrigoprestesmachado/tpack
    mvn clean package
    cd tpack
    docker build -t tpack-image .

To execute the container:

    docker run -d --name tpack -p 9080:9080 -p 9443:9443 tpack-image

### Development mode

During development, you can use [Open Liberty's](https://openliberty.io) development mode (dev mode) to code while observing and testing your changes on the fly.

    mvn clean package
    mvn liberty:dev