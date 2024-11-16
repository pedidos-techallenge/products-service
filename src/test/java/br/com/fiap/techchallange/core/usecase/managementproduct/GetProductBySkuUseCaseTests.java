package br.com.fiap.techchallange.core.usecase.managementproduct;

import br.com.fiap.techchallange.adapters.gateways.repository.IProductRepository;
import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetProductBySkuUseCaseTests {

    private IProductRepository repositoryMock;
    private GetProductBySkuUseCase useCase;

    @BeforeEach
    void setup() {
        repositoryMock = Mockito.mock(IProductRepository.class);
        useCase = new GetProductBySkuUseCase(repositoryMock);
    }

    @Test
    void testGetProductBySku() {
        String sku = "ABC123";
        Product mockProduct = new Product("ABC123", "Produto Teste", "Descrição Teste", 100.50f, Category.Drink.getValue());
        when(repositoryMock.getProductBySku(sku)).thenReturn(mockProduct);

        OutputDataProductDTO result = useCase.get(sku);

        assertEquals(mockProduct.getSku(), result.getSku());
        assertEquals(mockProduct.getName(), result.getName());
        assertEquals(mockProduct.getDescription(), result.getDescription());
        assertEquals(mockProduct.getMonetaryValue(), result.getValue());
        assertEquals(mockProduct.getCategory(), result.getCategory());

        verify(repositoryMock, times(1)).getProductBySku(sku);
    }
}
