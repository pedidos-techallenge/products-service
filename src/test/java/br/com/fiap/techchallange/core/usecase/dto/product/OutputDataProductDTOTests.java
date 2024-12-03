package br.com.fiap.techchallange.core.usecase.dto.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputDataProductDTOTests {

    @Test
    public void testOutputDataProductDTO() {
        String sku = "SKU123";
        String name = "Product Name";
        String description = "Product Description";
        float value = 100.0f;
        String category = "Category";

        OutputDataProductDTO productDTO = new OutputDataProductDTO(sku, name, description, value, category);

        assertEquals(sku, productDTO.getSku(), "SKU should be the same");
        assertEquals(name, productDTO.getName(), "Name should be the same");
        assertEquals(description, productDTO.getDescription(), "Description should be the same");
        assertEquals(value, productDTO.getValue(), "Value should be the same");
        assertEquals(category, productDTO.getCategory(), "Category should be the same");
    }
}
