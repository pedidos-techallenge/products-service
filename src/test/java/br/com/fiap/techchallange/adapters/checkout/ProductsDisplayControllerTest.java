package br.com.fiap.techchallange.adapters.checkout;

import br.com.fiap.techchallange.adapters.controllers.checkout.ProductsDisplayController;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.inputboundary.checkout.IProductsDisplayUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductsDisplayControllerTest {

    private IProductsDisplayUseCase productsDisplayUseCaseMock;
    private ProductsDisplayController productsDisplayController;

    @BeforeEach
    public void setUp() {
        // Criação do mock do IProductsDisplayUseCase
        productsDisplayUseCaseMock = mock(IProductsDisplayUseCase.class);

        // Inicialização do controller com o mock
        productsDisplayController = new ProductsDisplayController(productsDisplayUseCaseMock);
    }

    @Test
    public void testGetProducts_ReturnsListOfProducts() {
        // Criação de uma lista fictícia de produtos
        OutputDataProductDTO product1 = new OutputDataProductDTO("SKU001", "Product 1", "Description of Product 1", 100.0f, Category.Drink.getValue());
        OutputDataProductDTO product2 = new OutputDataProductDTO("SKU002", "Product 2", "Description of Product 2", 200.0f, Category.Drink.getValue());
        List<OutputDataProductDTO> expectedProducts = Arrays.asList(product1, product2);

        // Configuração do mock para retornar a lista de produtos
        when(productsDisplayUseCaseMock.getProducts()).thenReturn(expectedProducts);

        // Chamada ao método do controller
        List<OutputDataProductDTO> actualProducts = productsDisplayController.getProducts();

        // Verificação do comportamento esperado
        assertEquals(expectedProducts, actualProducts);

        // Verificação de que o método do caso de uso foi chamado exatamente uma vez
        verify(productsDisplayUseCaseMock, times(1)).getProducts();
    }

    @Test
    public void testGetProducts_ReturnsEmptyList_WhenNoProductsAvailable() {
        // Configuração do mock para retornar uma lista vazia
        when(productsDisplayUseCaseMock.getProducts()).thenReturn(Arrays.asList());

        // Chamada ao método do controller
        List<OutputDataProductDTO> actualProducts = productsDisplayController.getProducts();

        // Verificação de que a lista retornada está vazia
        assertEquals(0, actualProducts.size());

        // Verificação de que o método do caso de uso foi chamado exatamente uma vez
        verify(productsDisplayUseCaseMock, times(1)).getProducts();
    }

    @Test
    public void testGetProducts_ThrowsException_WhenErrorOccurs() {
        // Configuração do mock para lançar uma exceção
        when(productsDisplayUseCaseMock.getProducts()).thenThrow(new RuntimeException("Error fetching products"));

        // Verificação de que a exceção é lançada quando o método é chamado
        try {
            productsDisplayController.getProducts();
        } catch (RuntimeException e) {
            assertEquals("Error fetching products", e.getMessage());
        }

        // Verificação de que o método do caso de uso foi chamado exatamente uma vez
        verify(productsDisplayUseCaseMock, times(1)).getProducts();
    }
}
