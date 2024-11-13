package br.com.fiap.techchallange.core.entity;

import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.entity.vo.MonetaryValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

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
}
