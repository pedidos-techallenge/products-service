package br.com.fiap.techchallange.infrastructure.dto;

import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductRequestDTOTests {

    @Test
    public void testConstructorAndGetters() {
        String expectedSku = "123456A";
        String expectedName = "Product A";
        String expectedDescription = "Description of Product A";
        float expectedMonetaryValue = 100.0f;
        String expectedCategory = Category.Meal.getValue();

        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                expectedSku, expectedName, expectedDescription, expectedMonetaryValue, expectedCategory
        );

        assertEquals(expectedSku, productRequestDTO.sku(), "SKU should match");
        assertEquals(expectedName, productRequestDTO.name(), "Name should match");
        assertEquals(expectedDescription, productRequestDTO.description(), "Description should match");
        assertEquals(expectedMonetaryValue, productRequestDTO.monetaryValue(), "Monetary value should match");
        assertEquals(expectedCategory, productRequestDTO.category(), "Category should match");
    }

    @Test
    public void testConstructorWithJsonProperty() {
        String expectedSku = "123456B";
        String expectedName = "Product B";
        String expectedDescription = "Description of Product B";
        float expectedMonetaryValue = 200.0f;
        String expectedCategory = Category.Meal.getValue();

        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                expectedSku, expectedName, expectedDescription, expectedMonetaryValue, expectedCategory
        );

        assertEquals(expectedSku, productRequestDTO.sku(), "SKU should match");
        assertEquals(expectedName, productRequestDTO.name(), "Name should match");
        assertEquals(expectedDescription, productRequestDTO.description(), "Description should match");
        assertEquals(expectedMonetaryValue, productRequestDTO.monetaryValue(), "Monetary value should match");
        assertEquals(expectedCategory, productRequestDTO.category(), "Category should match");
    }

    @Test
    public void testConstructorFromProduct() {
        String sku = "123456C";
        String name = "Product C";
        String description = "Description of Product C";
        float monetaryValue = 300.0f;
        String category = Category.Meal.getValue();

        Product product = new Product(sku, name, description, monetaryValue, category);

        ProductRequestDTO productRequestDTO = new ProductRequestDTO(product);

        assertEquals(sku, productRequestDTO.sku(), "SKU should match");
        assertEquals(name, productRequestDTO.name(), "Name should match");
        assertEquals(description, productRequestDTO.description(), "Description should match");
        assertEquals(monetaryValue, productRequestDTO.monetaryValue(), "Monetary value should match");
        assertEquals(category, productRequestDTO.category(), "Category should match");
    }
}
