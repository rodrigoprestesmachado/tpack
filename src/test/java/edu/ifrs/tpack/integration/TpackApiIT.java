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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Integration test of TPACK XS Application
 */
@ExtendWith({ TpackCompose.class })
public class TpackApiIT {

    private static String API = "/tpack/service/api/";

    private String host;
    private Integer port;

    private CloseableHttpClient client;

    public TpackApiIT() {
        this.client = HttpClients.createDefault();
        host = TpackCompose.tpack.getContainerIpAddress();
        port = TpackCompose.tpack.getFirstMappedPort();
    }

    @Test
    public void simpleTest() {
        try {
            // Mounting URL
            String url = "http://" + host + ":" + port + API + "test";

            System.out.println(url);

            // Creating a http get
            HttpGet get = new HttpGet(url);
            // execute and getting the response
            HttpResponse response = this.client.execute(get);

            assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSessions() {
        try {
            // Mounting URL
            String url = "http://" + host + ":" + port + API + "getSessions";

            System.out.println(url);

            // Creating a http get
            HttpGet get = new HttpGet(url);
            // execute and getting the response
            HttpResponse response = this.client.execute(get);

            assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}