/**
 * @License
 * Copyright 2020 TPACK XS Applicatio
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
//@ExtendWith({ TpackCompose.class })
public class TpackApiIT {

    

    @Test
    public void getSessions() {
        try {
            // mounts the URL
            String url = "http://" + host + ":" + port + API + "getSessions";
            // creates a http get
            HttpGet get = new HttpGet(url);
            // executes and getting the response
            HttpResponse response = this.client.execute(get);

            assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */

}