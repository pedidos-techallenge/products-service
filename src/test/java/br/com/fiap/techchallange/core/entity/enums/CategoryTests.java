package br.com.fiap.techchallange.core.entity.enums;

import br.com.fiap.techchallange.core.entity.enums.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTests {

    @Test
    void testFromValueValid() {
        assertEquals(Category.Meal, Category.fromValue("Lanche"));
        assertEquals(Category.Sides, Category.fromValue("Acompanhamento"));
        assertEquals(Category.Drink, Category.fromValue("Bebida"));
        assertEquals(Category.Dessert, Category.fromValue("Sobremesa"));
    }

    @Test
    void testFromValueInvalid() {
        String invalidCategory = "InvalidCategory";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Category.fromValue(invalidCategory);
        });

        assertTrue(thrown.getMessage().contains("Valor inválido para campo de Category"));
    }

    @Test
    void testGetValue() {
        assertEquals("Lanche", Category.Meal.getValue());
        assertEquals("Acompanhamento", Category.Sides.getValue());
        assertEquals("Bebida", Category.Drink.getValue());
        assertEquals("Sobremesa", Category.Dessert.getValue());
    }

    @Test
    void testFromValueNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Category.fromValue(null);
        }, "Deveria lançar uma exceção para valor nulo");
    }

    @Test
    void testFromValueCaseInsensitive() {
        assertEquals(Category.Meal, Category.fromValue("lanche"));
        assertEquals(Category.Sides, Category.fromValue("acompanhamento"));
        assertEquals(Category.Drink, Category.fromValue("bebida"));
        assertEquals(Category.Dessert, Category.fromValue("sobremesa"));
    }

    @Test
    void testExceptionMessageContainsAllCategories() {
        String invalidCategory = "InvalidCategory";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Category.fromValue(invalidCategory);
        });

        String expectedMessage = "Esperado: Lanche Acompanhamento Bebida Sobremesa";
        assertTrue(thrown.getMessage().contains(expectedMessage));
    }

    @Test
    void testFromValueCorrectCategory() {
        assertEquals(Category.Meal, Category.fromValue("Lanche"));
        assertEquals(Category.Sides, Category.fromValue("Acompanhamento"));
        assertEquals(Category.Drink, Category.fromValue("Bebida"));
        assertEquals(Category.Dessert, Category.fromValue("Sobremesa"));
    }

    @Test
    void testGetValueAfterChangingCategoryDefinition() {
        Category meal = Category.Meal;
        assertEquals("Lanche", meal.getValue());
        meal = Category.Drink;
        assertEquals("Bebida", meal.getValue());
    }

    @Test
    void testNoAdditionalCategories() {
        Category[] categories = Category.values();
        assertEquals(4, categories.length, "Deve haver apenas 4 categorias definidas");
    }

    @Test
    void testSidesCategory() {
        assertEquals(Category.Sides, Category.fromValue("Acompanhamento"));
    }

    @Test
    void testDrinkCategory() {
        assertEquals(Category.Drink, Category.fromValue("Bebida"));
    }

}
