package br.com.fiap.techchallange.adapters.presenters.checkout;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.outputboundary.presenters.checkout.IProductPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductPresenterTests {

    private IProductPresenter productPresenter;

    @BeforeEach
    public void setUp() {
        productPresenter = new ProductPresenter();
    }

    @Test
    public void testPresent() {
        OutputDataProductDTO product1 = new OutputDataProductDTO("SKU123", "Product A", "Description A", 100.0f, Category.Drink.getValue());
        OutputDataProductDTO product2 = new OutputDataProductDTO("SKU124", "Product B", "Description B", 150.0f, Category.Drink.getValue());
        List<OutputDataProductDTO> productsDTO = Arrays.asList(product1, product2);

        List<IProductPresenter.ProductResponseModel> responseModels = productPresenter.present(productsDTO);

        assertNotNull(responseModels, "The response list should not be null");
        assertEquals(2, responseModels.size(), "The size of the response list should be 2");

        IProductPresenter.ProductResponseModel model1 = responseModels.get(0);
        assertEquals("SKU123", model1.getSku(), "The SKU of the first product should be SKU123");
        assertEquals("Product A", model1.getName(), "The name of the first product should be Product A");
        assertEquals("Description A", model1.getDescription(), "The description of the first product should be Description A");
        assertEquals(100.0f, model1.getValue(), "The value of the first product should be 100.0");

        IProductPresenter.ProductResponseModel model2 = responseModels.get(1);
        assertEquals("SKU124", model2.getSku(), "The SKU of the second product should be SKU124");
        assertEquals("Product B", model2.getName(), "The name of the second product should be Product B");
        assertEquals("Description B", model2.getDescription(), "The description of the second product should be Description B");
        assertEquals(150.0f, model2.getValue(), "The value of the second product should be 150.0");
    }
}