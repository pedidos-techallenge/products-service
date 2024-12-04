package br.com.fiap.techchallange.core.entity;

import br.com.fiap.techchallange.core.entity.enums.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTests {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("12345", "Lanche X", "Um delicioso lanche", 19.99f, "Lanche");
    }

    @Test
    void testProductCreationValidCategory() {
        assertEquals("12345", product.getSku());
        assertEquals("Lanche X", product.getName());
        assertEquals("Um delicioso lanche", product.getDescription());
        assertEquals(19.99f, product.getMonetaryValue(), 0.01);
        assertEquals(Category.Meal.getValue(), product.getCategory());
    }

    @Test
    void testInvalidCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("12346", "Bebida Y", "Uma bebida", 5.99f, "CategoriaInvalida");
        });
    }

    @Test
    void testMonetaryValue() {
        assertEquals(19.99f, product.getMonetaryValue(), 0.01);
    }

    @Test
    void testCategoryConversion() {
        Product productMeal = new Product("12347", "Lanche", "Lanche saboroso", 15.00f, "Lanche");
        Product productSides = new Product("12348", "Batata Frita", "Acompanhamento delicioso", 8.50f, "Acompanhamento");

        assertEquals(Category.Meal.getValue(), productMeal.getCategory());
        assertEquals(Category.Sides.getValue(), productSides.getCategory());
    }

    @Test
    void testEmptySkuThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("", "Produto X", "Descrição", 10.0f, "Lanche");
        });
    }

    @Test
    void testEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("12345", "", "Descrição", 10.0f, "Lanche");
        });
    }

    @Test
    void testEmptyDescriptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("12345", "Produto X", "", 10.0f, "Lanche");
        });
    }

    @Test
    void testConstructor_ValidInputs() {
        // Arrange
        String sku = "sku1";
        String name = "Product 1";
        String description = "A great product";
        float monetaryValue = 99.99f;
        String category = Category.Drink.getValue();

        // Act
        Product product = new Product(sku, name, description, monetaryValue, category);

        // Assert
        assertNotNull(product);
        assertEquals(sku, product.getSku());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(monetaryValue, product.getMonetaryValue(), 0.01f);
        assertEquals(category, product.getCategory());
    }

    @Test
    void testConstructor_InvalidSku() {
        // Arrange
        String sku = "";
        String name = "Product 1";
        String description = "A great product";
        float monetaryValue = 99.99f;
        String category = Category.Drink.getValue();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(sku, name, description, monetaryValue, category);
        });
        assertEquals("sku cannot be empty", exception.getMessage());
    }

    @Test
    void testSetMonetaryValue() {
        // Arrange
        String sku = "sku1";
        String name = "Product 1";
        String description = "A great product";
        float monetaryValue = 199.99f;
        String category = Category.Drink.getValue();
        Product product = new Product(sku, name, description, monetaryValue, category);

        // Actgg
        float result = product.getMonetaryValue();

        // Assert
        assertEquals(monetaryValue, result, 0.01f);
    }

    @Test
    void testCheckValue_SkuEmpty() {
        // Arrange
        String sku = "";
        String name = "Product 1";
        String description = "A great product";
        float monetaryValue = 99.99f;
        String category = Category.Drink.getValue();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(sku, name, description, monetaryValue, category);
        });
        assertEquals("sku cannot be empty", exception.getMessage());
    }

    @Test
    void testCheckValue_DescriptionEmpty() {
        // Arrange
        String sku = "sku1";
        String name = "Product 1";
        String description = "";
        float monetaryValue = 99.99f;
        String category = Category.Drink.getValue();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(sku, name, description, monetaryValue, category);
        });
        assertEquals("description cannot be empty", exception.getMessage());
    }

    @Test
    void testToString() {
        // Arrange
        String sku = "sku1";
        String name = "Product 1";
        String description = "A great product";
        float monetaryValue = 99.99f;
        String category = Category.Drink.getValue();
        Product product = new Product(sku, name, description, monetaryValue, category);

        // Act
        String result = product.toString();

        // Assert
        assertTrue(result.contains("sku='sku1'"));
        assertTrue(result.contains("name='Product 1'"));
        assertTrue(result.contains("description='A great product'"));
        assertTrue(result.contains("monetaryValue=99.99"));
        assertTrue(result.contains("category='Drink'"));
    }


}
