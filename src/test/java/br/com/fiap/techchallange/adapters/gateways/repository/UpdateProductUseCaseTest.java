package br.com.fiap.techchallange.adapters.gateways.repository;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.managementproduct.UpdateProductUseCase;
import br.com.fiap.techchallange.core.usecase.dto.product.InputDataProductDTO;
import br.com.fiap.techchallange.core.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UpdateProductUseCaseTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private UpdateProductUseCase updateProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvoke_UpdateProduct() {
        InputDataProductDTO productDTO = new InputDataProductDTO("sku1", "Updated Product", "Updated Description", 120.0f, Category.Drink.getValue());
        Product updatedProduct = new Product("sku1", "Updated Product", "Updated Description", 120.0f, Category.Drink.getValue());

        updateProductUseCase.invoke(productDTO);

        verify(productRepository, times(1)).updateProduct(eq("sku1"), any(Product.class));
    }

    @Test
    void testInvoke_ProductNotFound() {
        InputDataProductDTO productDTO = new InputDataProductDTO("nonExistentSku", "Non-existent Product", "Description", 100.0f, Category.Drink.getValue());

        updateProductUseCase.invoke(productDTO);

        verify(productRepository, times(1)).updateProduct(eq("nonExistentSku"), any(Product.class));
    }


    @Test
    void testInvoke_UpdateProductWithSameValues() {
        InputDataProductDTO productDTO = new InputDataProductDTO("sku1", "Updated Product", "Updated Description", 120.0f, Category.Drink.getValue());
        Product updatedProduct = new Product("sku1", "Updated Product", "Updated Description", 120.0f, Category.Drink.getValue());

        updateProductUseCase.invoke(productDTO);

        verify(productRepository, times(1)).updateProduct(eq("sku1"), any(Product.class));
    }
}
