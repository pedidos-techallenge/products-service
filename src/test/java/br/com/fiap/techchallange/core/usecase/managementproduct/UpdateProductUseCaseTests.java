package br.com.fiap.techchallange.core.usecase.managementproduct;

import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.InputDataProductDTO;
import br.com.fiap.techchallange.adapters.gateways.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UpdateProductUseCaseTests {

    private IProductRepository repositoryMock;
    private UpdateProductUseCase useCase;

    @BeforeEach
    void setup() {
        repositoryMock = mock(IProductRepository.class);
        useCase = new UpdateProductUseCase(repositoryMock);
    }

    @Test
    void testInvoke_UpdatesProduct() {
        InputDataProductDTO inputDataProductDTO = new InputDataProductDTO("SKU001", "Produto Atualizado", "Descrição Atualizada", 150.0f, Category.Meal.getValue());

        useCase.invoke(inputDataProductDTO);

        ArgumentCaptor<String> skuCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(repositoryMock, times(1)).updateProduct(skuCaptor.capture(), productCaptor.capture());

        String capturedSku = skuCaptor.getValue();
        assertEquals("SKU001", capturedSku, "O SKU capturado deve ser igual ao SKU fornecido");

        Product capturedProduct = productCaptor.getValue();
        assertEquals("Produto Atualizado", capturedProduct.getName(), "O nome do produto deve ser o esperado");
        assertEquals("Descrição Atualizada", capturedProduct.getDescription(), "A descrição do produto deve ser a esperada");
        assertEquals(150.0f, capturedProduct.getMonetaryValue(), "O valor monetário do produto deve ser o esperado");
        assertEquals(Category.Meal.getValue(), capturedProduct.getCategory(), "A categoria do produto deve ser a esperada");
    }
}
