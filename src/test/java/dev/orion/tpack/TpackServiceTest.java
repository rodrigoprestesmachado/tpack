/**
 * @License
 * Copyright 2022 TPACK XS Application
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
package dev.orion.tpack;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class TpackServiceTest {

    @Test
    void getPagesTest() {
        given()
            .when().get("/tpack/getPages")
            .then()
            .statusCode(200);
    }

    @Test
    void getPageOk() {
        given()
            .when().get("/tpack/getPage/6")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getPage1Ok() {
        given()
            .when().get("/tpack/getPage/1")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getPage12Ok() {
        given()
            .when().get("/tpack/getPage/12")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getPage0Invalid() {
        given()
            .when().get("/tpack/getPage/0")
            .then()
            .statusCode(204); // NO CONTENT
    }

    @Test
    void getPage13Invalid() {
        given()
            .when().get("/tpack/getPage/13")
            .then()
            .statusCode(204); // NO CONTENT
    }

    @Test
    void getPageValueInvalid() {
        given()
            .when().get("/tpack/getPage/invalid")
            .then()
            .statusCode(404); // NOT FOUND
    }


}
