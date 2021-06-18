FROM open-liberty:20.0.0.11-full-java8-openj9

# Sending wait-for-it.sh script
COPY scripts/wait-for-it.sh /wait-for-it.sh
USER root
RUN chmod +x /wait-for-it.sh

LABEL \
    org.opencontainers.image.authors="Rodrigo Prestes Machado" \
    description="Image for TPACK XS Application"

# Sending jdbc driver
COPY --chown=1001:0 src/main/liberty/config/resources/mysql-connector-java-8.0.19.jar /config/resources

# Sending Liberty configuration 
COPY --chown=1001:0 src/main/liberty/config /config/

# Deploy the application
COPY --chown=1001:0 target/tpack.war /config/dropins/ 

# Running configuration 
RUN configure.sh