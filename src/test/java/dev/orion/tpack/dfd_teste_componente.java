package dev.orion.tpack;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class dfd_teste_componente {

// TESTE DO BACKEND FirstPage
    @Test
    public void getFirstPageTest() {
        given()
            .when().get("/tpack/getFirstPage")
            .then()
            .statusCode(200);   
    }   // RESULTADO OK

    @Test
    public void getFirstPageValueInvalid() {
        given()
            .when().get("/tpack/getFirstPage/PaginaInexistente")
            .then()
            .statusCode(404);
    }   // RESULTADO ERRO

// TESTE DO BACKEND getPages
    @Test
    public void getPagesTest() {    
        given()
            .when().get("/tpack/getPages")
            .then()
            .statusCode(200);
    }   // RESULTADO OK

    @Test
    public void getPagesValueInvalid() {
        given()
            .when().get("/tpack/getPages/PaginaInexistente")
            .then()
            .statusCode(404);
    }  // RESULTADO ERRO
  

}
