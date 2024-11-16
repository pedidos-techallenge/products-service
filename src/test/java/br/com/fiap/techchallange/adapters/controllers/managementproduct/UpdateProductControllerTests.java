package br.com.fiap.techchallange.adapters.controllers.managementproduct;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.InputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.inputboundary.managementproduct.IUpdateProductUseCase;
import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UpdateProductControllerTests {

    private UpdateProductController updateProductController;

    @Mock
    private IUpdateProductUseCase updateProductUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateProductController = new UpdateProductController(updateProductUseCase);
    }

    @Test
    public void testInvoke() {
        String sku = "SKU123";
        String name = "Product Name";
        String description = "Product Description";
        float monetaryValue = 100.0f;
        String category = Category.Meal.getValue();

        updateProductController.invoke(sku, name, description, monetaryValue, category);

        InputDataProductDTO expectedInputData = new InputDataProductDTO(sku, name, description, monetaryValue, category);
        verify(updateProductUseCase, times(1)).invoke(expectedInputData);
    }
}
