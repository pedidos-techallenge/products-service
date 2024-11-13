package br.com.fiap.techchallange.core.vo;

import br.com.fiap.techchallange.core.entity.vo.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("order123", "SKU123", 2, 10.0f);
    }

    @Test
    void testValidItemCreation() {
        assertEquals("order123", item.getOrder_id());
        assertEquals("SKU123", item.getSku());
        assertEquals(2, item.getQuantity());
        assertEquals(10.0f, item.getUnitValue());
        assertEquals(20.0f, item.getAmount()); // 2 * 10.0f
    }

    @Test
    void testSetAmountCalculation() {
        Item newItem = new Item("order456", "SKU456", 3, 15.0f);
        assertEquals(45.0f, newItem.getAmount()); // 3 * 15.0f
    }

    @Test
    void testInvalidQuantityThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Item("order789", "SKU789", 0, 20.0f)
        );
        assertEquals("quantity of item cannot be less than or equal to zero", exception.getMessage());
    }

    @Test
    void testNegativeQuantityThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Item("order789", "SKU789", -1, 20.0f)
        );
        assertEquals("quantity of item cannot be less than or equal to zero", exception.getMessage());
    }

    @Test
    void testValidUnitValue() {
        assertEquals(10.0f, item.getUnitValue());
    }

    @Test
    void testAmountUpdatesWithQuantityChange() {
        Item dynamicItem = new Item("orderDynamic", "SKUdynamic", 5, 8.0f);
        assertEquals(40.0f, dynamicItem.getAmount()); // 5 * 8.0f
    }
}
