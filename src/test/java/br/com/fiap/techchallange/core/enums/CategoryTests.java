package br.com.fiap.techchallange.core.enums;

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
}
