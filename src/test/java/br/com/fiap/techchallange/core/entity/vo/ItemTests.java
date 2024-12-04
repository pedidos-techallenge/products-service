package br.com.fiap.techchallange.core.entity.vo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTests {

    @Test
    public void testItemCreationWithValidData() {
        String orderId = "order123";
        String sku = "sku123";
        Integer quantity = 5;
        float unitValue = 10.0f;

        Item item = new Item(orderId, sku, quantity, unitValue);

        assertNotNull(item, "Item should be created successfully");
        assertEquals(orderId, item.getOrder_id(), "Order ID should match");
        assertEquals(sku, item.getSku(), "SKU should match");
        assertEquals(quantity, item.getQuantity(), "Quantity should match");
        assertEquals(unitValue, item.getUnitValue(), "Unit value should match");
        assertEquals(unitValue * quantity, item.getAmount(), "Amount should be calculated correctly");
    }

    @Test
    public void testItemCreationWithInvalidQuantity() {
        String orderId = "order123";
        String sku = "sku123";
        Integer quantity = 0;
        float unitValue = 10.0f;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Item(orderId, sku, quantity, unitValue);
        });

        assertEquals("quantity of item cannot be less than or equal to zero", exception.getMessage());
    }

    @Test
    public void testItemCreationWithNegativeQuantity() {
        String orderId = "order123";
        String sku = "sku123";
        Integer quantity = -5;
        float unitValue = 10.0f;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Item(orderId, sku, quantity, unitValue);
        });

        assertEquals("quantity of item cannot be less than or equal to zero", exception.getMessage());
    }

    @Test
    public void testAmountCalculation() {
        String orderId = "order123";
        String sku = "sku123";
        Integer quantity = 5;
        float unitValue = 10.0f;

        Item item = new Item(orderId, sku, quantity, unitValue);

        float expectedAmount = unitValue * quantity;
        assertEquals(expectedAmount, item.getAmount(), "Amount should be the product of unitValue and quantity");
    }

    @Test
    public void testValidQuantity() {
        String orderId = "order123";
        String sku = "sku123";
        Integer quantity = 1;
        float unitValue = 10.0f;

        Item item = new Item(orderId, sku, quantity, unitValue);
        assertNotNull(item, "Item should be created successfully with valid quantity");
    }

    @Test
    public void testAmountWithSingleItem() {
        String orderId = "order123";
        String sku = "sku123";
        Integer quantity = 1;
        float unitValue = 10.0f;

        Item item = new Item(orderId, sku, quantity, unitValue);

        float expectedAmount = unitValue * quantity;
        assertEquals(expectedAmount, item.getAmount(), "Amount should be unitValue since quantity is 1");
    }
}
