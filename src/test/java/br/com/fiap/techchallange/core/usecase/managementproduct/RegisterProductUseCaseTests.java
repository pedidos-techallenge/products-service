package br.com.fiap.techchallange.core.usecase.managementproduct;

import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.InputDataProductDTO;
import br.com.fiap.techchallange.adapters.gateways.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterProductUseCaseTests {

    private IProductRepository repositoryMock;
    private RegisterProductUseCase useCase;

    @BeforeEach
    void setup() {
        repositoryMock = mock(IProductRepository.class);
        useCase = new RegisterProductUseCase(repositoryMock);
    }

    @Test
    void testInvoke_CreatesProduct() {
        InputDataProductDTO inputDataProductDTO = new InputDataProductDTO("SKU001", "Produto 1", "Descrição 1", 100.0f, Category.Meal.getValue());

        useCase.invoke(inputDataProductDTO);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(repositoryMock, times(1)).createProduct(productCaptor.capture());

        Product capturedProduct = productCaptor.getValue();
        assertEquals("SKU001", capturedProduct.getSku());
        assertEquals("Produto 1", capturedProduct.getName());
        assertEquals("Descrição 1", capturedProduct.getDescription());
        assertEquals(100.0f, capturedProduct.getMonetaryValue());
        assertEquals(Category.Meal.getValue(), capturedProduct.getCategory());
    }
}
