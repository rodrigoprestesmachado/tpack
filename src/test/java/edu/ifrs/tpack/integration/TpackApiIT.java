/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
    *
    *
    * comment for pmd
    */
    private static String API = "/tpack/service/api/";

    /**
    *
    *
    * comment for pmd
    */
    private String host;
    /**
    *
    *
    * comment for pmd
    */
    private Integer port;

    /**
    *
    *
    * comment for pmd
    */
    private CloseableHttpClient client;

    /**
    *
    *
    * comment for pmd
    */
    public TpackApiIT() {
        this.client = HttpClients.createDefault();
        host = TpackCompose.tpack.getContainerIpAddress();
        port = TpackCompose.tpack.getFirstMappedPort();
    }

    /**
    *
    *
    * comment for pmd
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