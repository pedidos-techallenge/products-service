package br.com.fiap.techchallange.cucumber.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ProductSteps {

    private Response response;

    @Given("existem produtos cadastrados no sistema")
    public void existemProdutosCadastrados() {
        RestAssured.given()
                .contentType("application/json")
                .body("{\"sku\":\"ABC123\",\"name\":\"Produto Teste\",\"description\":\"Descrição\",\"monetaryValue\":10.0,\"category\":\"Categoria\"}")
                .post("/v1/products/create")
                .then()
                .statusCode(201);
    }

    @Given("existe um produto com SKU {string} no sistema")
    public void existeUmProdutoComSku(String sku) {
        RestAssured.given()
                .contentType("application/json")
                .body("{\"sku\":\"" + sku + "\",\"name\":\"Produto Teste\",\"description\":\"Descrição\",\"monetaryValue\":10.0,\"category\":\"Categoria\"}")
                .post("/v1/products/create")
                .then()
                .statusCode(201);
    }

    @When("eu faço uma requisição GET para {string}")
    public void euFacoUmaRequisicaoGetPara(String endpoint) {
        response = RestAssured.get(endpoint);
    }

    @Then("a resposta deve ter o status {int}")
    public void aRespostaDeveTerOStatus(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("o corpo da resposta deve conter uma lista de produtos")
    public void oCorpoDaRespostaDeveConterUmaListaDeProdutos() {
        response.then().body("size()", greaterThan(0));
    }

    @Then("o corpo da resposta deve conter {string} no campo {string}")
    public void oCorpoDaRespostaDeveConterNoCampo(String valorEsperado, String campo) {
        response.then().body(campo, equalTo(valorEsperado));
    }
}
