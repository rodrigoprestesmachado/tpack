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
package edu.ifrs.tpack.integration;

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
public class TpackCompose implements BeforeAllCallback, AfterAllCallback {

    /**
     * 
     * @return
     */
    // Configure the a tpack application container from Dockerfile
    @Container
    public static ApplicationContainer tpack = new ApplicationContainer().withAppContextRoot("/tpack")
            .waitingFor(Wait.forHttp("/"));

    /**
     * 
     * @return
     */
    public static MySQLContainer<?> mysql;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

        // Create a private network for the containers
        Network network = Network.newNetwork();

        // Create a MySQL container to work with tpack service
        mysql = new MySQLContainer<>("mysql:latest");
        mysql.withDatabaseName("tpack");
        mysql.withUsername("tpack");
        mysql.withPassword("tpack");
        mysql.withNetworkAliases("db");
        mysql.withExposedPorts(3306);
        mysql.waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(60)));
        mysql.withNetwork(network);

        // Set network for tpack service
        tpack.withExposedPorts(9080);
        tpack.withNetworkAliases("tpack");
        tpack.withNetwork(network);

        // Start the containers
        mysql.start();
        tpack.start();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        mysql.stop();
        tpack.stop();
    }
}