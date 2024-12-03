package br.com.fiap.techchallange.adapters.controllers.managementproduct;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.inputboundary.managementproduct.IGetProductListUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetProductListControllerTests {

    private GetProductListController getProductListController;

    @Mock
    private IGetProductListUseCase getProductListUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getProductListController = new GetProductListController(getProductListUseCase);
    }

    @Test
    public void testGetProducts() {
        OutputDataProductDTO product1 = new OutputDataProductDTO("SKU123", "Product A", "Description A", 100.0f, Category.Drink.getValue());
        OutputDataProductDTO product2 = new OutputDataProductDTO("SKU124", "Product B", "Description B", 150.0f, Category.Drink.getValue());
        List<OutputDataProductDTO> productDTOList = Arrays.asList(product1, product2);

        when(getProductListUseCase.getProducts()).thenReturn(productDTOList);

        List<OutputDataProductDTO> result = getProductListController.getProducts();

        assertNotNull(result, "A lista de produtos não deve ser nula");
        assertEquals(2, result.size(), "O tamanho da lista de produtos deveria ser 2");

        assertEquals("SKU123", result.get(0).getSku(), "O SKU do primeiro produto deveria ser SKU123");
        assertEquals("Product A", result.get(0).getName(), "O nome do primeiro produto deveria ser Product A");
        assertEquals("Description A", result.get(0).getDescription(), "A descrição do primeiro produto deveria ser Description A");
        assertEquals(100.0f, result.get(0).getValue(), "O valor do primeiro produto deveria ser 100.0");

        assertEquals("SKU124", result.get(1).getSku(), "O SKU do segundo produto deveria ser SKU124");
        assertEquals("Product B", result.get(1).getName(), "O nome do segundo produto deveria ser Product B");
        assertEquals("Description B", result.get(1).getDescription(), "A descrição do segundo produto deveria ser Description B");
        assertEquals(150.0f, result.get(1).getValue(), "O valor do segundo produto deveria ser 150.0");

        verify(getProductListUseCase, times(1)).getProducts();
    }
}
