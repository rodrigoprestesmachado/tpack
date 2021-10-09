/**
 * @License
 * Copyright 2020 TPACK XS Application
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

    /**
     * path of API
     */
    private static String API = "/tpack/service/api/";

    /**
     * the host
     */
    private String host;

    /**
     * the port
     */
    private Integer port;

    /**
     * the closeable http client
     */
    private CloseableHttpClient client;

    /**
     * class constructor
     */
    public TpackApiIT() {
        this.client = HttpClients.createDefault();
        host = TpackCompose.tpack.getContainerIpAddress();
        port = TpackCompose.tpack.getFirstMappedPort();
    }

    /**
     * must test getting of sessions
     */
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

}