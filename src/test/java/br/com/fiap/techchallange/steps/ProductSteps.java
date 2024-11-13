package br.com.fiap.techchallange.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

public class ProductSteps {

    private List<String> products;
    private List<String> result;

    @Given("I have a list of products")
    public void i_have_a_list_of_products() {
        products = new ArrayList<>();
        products.add("Product A");
        products.add("Product B");
    }

    @When("I request all products")
    public void i_request_all_products() {
        result = products; // Simulando a chamada para buscar produtos
    }

    @Then("I should get a non-empty list")
    public void i_should_get_a_non_empty_list() {
        Assert.assertFalse(result.isEmpty());
    }
}

