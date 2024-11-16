package br.com.fiap.techchallange.core.usecase.managementproduct;

import br.com.fiap.techchallange.adapters.gateways.repository.IProductRepository;
import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetProductListUseCaseTests {

    private IProductRepository repositoryMock;
    private GetProductListUseCase useCase;

    @BeforeEach
    void setup() {
        repositoryMock = Mockito.mock(IProductRepository.class);
        useCase = new GetProductListUseCase(repositoryMock);
    }

    @Test
    void testGetProducts() {
        List<Product> mockProducts = Arrays.asList(
                new Product("SKU001", "Produto 1", "Descrição 1", 100.0f, Category.Meal.getValue()),
                new Product("SKU002", "Produto 2", "Descrição 2", 200.0f, Category.Drink.getValue())
        );
        when(repositoryMock.getProducts()).thenReturn(mockProducts);

        List<OutputDataProductDTO> result = useCase.getProducts();

        assertEquals(mockProducts.size(), result.size());
        for (int i = 0; i < mockProducts.size(); i++) {
            Product mockProduct = mockProducts.get(i);
            OutputDataProductDTO dto = result.get(i);

            assertEquals(mockProduct.getSku(), dto.getSku());
            assertEquals(mockProduct.getName(), dto.getName());
            assertEquals(mockProduct.getDescription(), dto.getDescription());
            assertEquals(mockProduct.getMonetaryValue(), dto.getValue());
            assertEquals(mockProduct.getCategory(), dto.getCategory());
        }

        verify(repositoryMock, times(1)).getProducts();
    }
}
