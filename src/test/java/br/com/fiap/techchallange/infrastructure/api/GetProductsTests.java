package br.com.fiap.techchallange.infrastructure.api;

import br.com.fiap.techchallange.adapters.controllers.managementproduct.IGetProductListController;
import br.com.fiap.techchallange.adapters.presenters.viewmodel.ErrorViewModel;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.outputboundary.presenters.managementproduct.IProductManagementPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetProductsTests {

    @Mock
    IGetProductListController getProductListController;

    @Mock
    IProductManagementPresenter productPresenter;

    @InjectMocks
    ManagementProduct managementProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts_Success() throws EmptyResultDataAccessException {
        OutputDataProductDTO productDTO = new OutputDataProductDTO("123", "Produto Teste", "Descrição do Produto", 100.0f, Category.Meal.getValue());

        IProductManagementPresenter.ProductResponseModel productResponseModel = new IProductManagementPresenter.ProductResponseModel(productDTO);

        List<IProductManagementPresenter.ProductResponseModel> productList = List.of(productResponseModel);

        when(getProductListController.getProducts()).thenReturn(List.of(productDTO));
        when(productPresenter.presentList(List.of(productDTO))).thenReturn(productList);

        ResponseEntity<?> response = managementProduct.getProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    void testGetProducts_NotFound() throws EmptyResultDataAccessException {
        when(getProductListController.getProducts()).thenThrow(EmptyResultDataAccessException.class);

        ResponseEntity<?> response = managementProduct.getProducts();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
    }
}
