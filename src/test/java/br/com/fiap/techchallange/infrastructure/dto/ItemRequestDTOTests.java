package br.com.fiap.techchallange.infrastructure.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemRequestDTOTests {

    @Test
    public void testItemRequestDTOConstructorAndGetters() {
        String expectedSku = "123456A";
        float expectedAmount = 35.00f;
        Integer expectedQuantity = 1;

        ItemRequestDTO itemRequestDTO = new ItemRequestDTO(expectedSku, expectedAmount, expectedQuantity);

        assertEquals(expectedSku, itemRequestDTO.sku(), "SKU should match");
        assertEquals(expectedAmount, itemRequestDTO.amount(), "Amount should match");
        assertEquals(expectedQuantity, itemRequestDTO.quantity(), "Quantity should match");
    }

    @Test
    public void testConstructorWithNullValues() {
        String sku = null;
        float amount = 0f;
        Integer quantity = null;

        ItemRequestDTO itemRequestDTO = new ItemRequestDTO(sku, amount, quantity);
        assertNull(itemRequestDTO.sku(), "SKU should be null");
        assertEquals(0f, itemRequestDTO.amount(), "Amount should be 0");
        assertNull(itemRequestDTO.quantity(), "Quantity should be null");
    }
}
