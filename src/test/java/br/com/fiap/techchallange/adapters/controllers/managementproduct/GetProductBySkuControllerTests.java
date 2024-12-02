package br.com.fiap.techchallange.adapters.controllers.managementproduct;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.inputboundary.managementproduct.IGetProductBySkuUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetProductBySkuControllerTests {

    private GetProductBySkuController getProductBySkuController;

    @Mock
    private IGetProductBySkuUseCase getProductBySkuUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getProductBySkuController = new GetProductBySkuController(getProductBySkuUseCase);
    }

    @Test
    public void testInvoke() {
        String sku = "SKU123";
        OutputDataProductDTO expectedProduct = new OutputDataProductDTO(sku, "Product A", "Description A", 100.0f, Category.Drink.getValue());

        when(getProductBySkuUseCase.get(sku)).thenReturn(expectedProduct);

        OutputDataProductDTO result = getProductBySkuController.invoke(sku);

        assertNotNull(result, "O produto não deve ser nulo");
        assertEquals(sku, result.getSku(), "O SKU do produto retornado deveria ser SKU123");
        assertEquals("Product A", result.getName(), "O nome do produto retornado deveria ser Product A");
        assertEquals("Description A", result.getDescription(), "A descrição do produto retornado deveria ser Description A");
        assertEquals(100.0f, result.getValue(), "O valor do produto retornado deveria ser 100.0");

        verify(getProductBySkuUseCase, times(1)).get(sku);
    }

    @Test
    public void testInvokeWhenProductNotFound() {
        String sku = "SKU999";

        when(getProductBySkuUseCase.get(sku)).thenReturn(null);

        OutputDataProductDTO result = getProductBySkuController.invoke(sku);

        assertNull(result, "O produto retornado deveria ser nulo quando o SKU não for encontrado");

        verify(getProductBySkuUseCase, times(1)).get(sku);
    }
}
