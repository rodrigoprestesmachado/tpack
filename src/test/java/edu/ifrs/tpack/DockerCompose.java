/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.ifrs.tpack;

import java.time.Duration;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

/**
 * This class creates the containers to run integration tests for TPACK XS
 * Application
 */
public class DockerCompose implements BeforeAllCallback, AfterAllCallback {

    // Configure the a tpack application container from project Dockerfile

    /**
     * Container tpack
     */
    @Container
    public static ApplicationContainer tpack = new ApplicationContainer().withAppContextRoot("/tpack-app")
            .waitingFor(Wait.forHttp("/")).withNetworkAliases("tpack").withExposedPorts(9080);

    /**
     * mysql
     */
    public static MySQLContainer<?> mysql;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        // Create a private network for the containers
        Network network = Network.newNetwork();

        // Set network for tpack service
        tpack.withNetwork(network);

        // Create a MySQL container to work with tpack service
        mysql = new MySQLContainer<>("mysql:latest");
        mysql.withDatabaseName("tpack");
        mysql.withUsername("tpack");
        mysql.withPassword("tpack");
        mysql.withNetworkAliases("db");
        mysql.withExposedPorts(3306);
        mysql.waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));
        mysql.withNetwork(network);

        // Start the containers
        tpack.start();
        mysql.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        tpack.stop();
        mysql.stop();
    }
}