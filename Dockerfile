FROM open-liberty:20.0.0.3-full-java8-openj9

LABEL \
    org.opencontainers.image.authors="Rodrigo Prestes Machado" \
    description="Image for TPACK XS Application"

# Sending jdbc driver
COPY --chown=1001:0 src/main/liberty/config/resources/mysql-connector-java-8.0.19.jar /config/resources

# Sending Liberty configuration 
COPY --chown=1001:0 src/main/liberty/config /config/

# Deploy the application
COPY --chown=1001:0 target/tpack-app.war /config/dropins/ 

# Running configuration 
RUN configure.sh