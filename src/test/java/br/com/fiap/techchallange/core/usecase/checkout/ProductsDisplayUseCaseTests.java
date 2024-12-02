package br.com.fiap.techchallange.core.usecase.checkout;

import br.com.fiap.techchallange.adapters.gateways.repository.IProductRepository;
import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsDisplayUseCaseTests {

    @Test
    void shouldReturnProductsAsDTOs() {
        IProductRepository mockProductRepository = mock(IProductRepository.class);

        List<Product> mockProducts = Arrays.asList(
                new Product(
                        "SKU123",
                        "Product A",
                        "Description A",
                        10.00F,
                        Category.Meal.getValue()
                ),
                new Product(
                        "SKU456",
                        "Product B",
                        "Description B",
                        20.00f,
                        Category.Drink.getValue()
                )
        );

        when(mockProductRepository.getProducts()).thenReturn(mockProducts);

        ProductsDisplayUseCase useCase = new ProductsDisplayUseCase(mockProductRepository);

        List<OutputDataProductDTO> result = useCase.getProducts();

        assertEquals(2, result.size());

        OutputDataProductDTO firstProduct = result.get(0);
        assertEquals("SKU123", firstProduct.getSku());
        assertEquals("Product A", firstProduct.getName());
        assertEquals("Description A", firstProduct.getDescription());
        assertEquals(10.00F, firstProduct.getValue());
        assertEquals("Lanche", firstProduct.getCategory());

        OutputDataProductDTO secondProduct = result.get(1);
        assertEquals("SKU456", secondProduct.getSku());
        assertEquals("Product B", secondProduct.getName());
        assertEquals("Description B", secondProduct.getDescription());
        assertEquals(20.00F, secondProduct.getValue());
        assertEquals("Bebida", secondProduct.getCategory());

        Mockito.verify(mockProductRepository, Mockito.times(1)).getProducts();
    }
}
