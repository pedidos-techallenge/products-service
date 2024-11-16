package br.com.fiap.techchallange.adapters.controllers.managementproduct;

import br.com.fiap.techchallange.core.usecase.inputboundary.managementproduct.IRemoveProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RemoveProductControllerTests {

    private RemoveProductController removeProductController;

    @Mock
    private IRemoveProductUseCase removeProductUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        removeProductController = new RemoveProductController(removeProductUseCase);
    }

    @Test
    public void testInvoke() {
        String sku = "SKU123";

        removeProductController.invoke(sku);

        verify(removeProductUseCase, times(1)).invoke(sku);
    }
}
