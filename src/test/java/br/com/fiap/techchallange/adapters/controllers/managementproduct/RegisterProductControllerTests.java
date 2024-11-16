package br.com.fiap.techchallange.adapters.controllers.managementproduct;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.InputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.inputboundary.managementproduct.IRegisterProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RegisterProductControllerTests {

    private RegisterProductController registerProductController;

    @Mock
    private IRegisterProductUseCase registerProductUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registerProductController = new RegisterProductController(registerProductUseCase);
    }

    @Test
    public void testInvoke() {
        String sku = "SKU123";
        String name = "Product A";
        String description = "Description A";
        float monetaryValue = 100.0f;
        String category = Category.Meal.getValue();

        registerProductController.invoke(sku, name, description, monetaryValue, category);

        verify(registerProductUseCase, times(1)).invoke(argThat(inputDTO ->
                inputDTO.sku().equals(sku) &&
                        inputDTO.name().equals(name) &&
                        inputDTO.description().equals(description) &&
                        inputDTO.monetaryValue() == monetaryValue &&
                        inputDTO.category().equals(category)
        ));
    }
}
