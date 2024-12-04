package br.com.fiap.techchallange.infrastructure.api;

import br.com.fiap.techchallange.adapters.presenters.viewmodel.ErrorViewModel;
import br.com.fiap.techchallange.core.entity.Product;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import br.com.fiap.techchallange.core.usecase.outputboundary.presenters.managementproduct.IProductManagementPresenter;
import br.com.fiap.techchallange.infrastructure.dto.ProductRequestDTO;
import br.com.fiap.techchallange.adapters.controllers.managementproduct.*;
import br.com.fiap.techchallange.infrastructure.api.ManagementProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ManagementProductTest {

    private ProductRequestDTO productRequestDTO;

    private ManagementProduct managementProduct;
    private IGetProductListController getProductListController;
    private IGetProductBySkuController getProductBySkuController;
    private IRegisterProductController registerProductController;
    private IUpdateProductController updateProductController;
    private IRemoveProductController removeProductController;
    private IProductManagementPresenter productPresenter;
    private String sku;
    private OutputDataProductDTO productDTO;
    private IProductManagementPresenter.ProductResponseModel responseModel;

    @BeforeEach
    public void setUp() {
        sku = "sku123";
        getProductListController = mock(IGetProductListController.class);
        getProductBySkuController = mock(IGetProductBySkuController.class);
        registerProductController = mock(IRegisterProductController.class);
        updateProductController = mock(IUpdateProductController.class);
        removeProductController = mock(IRemoveProductController.class);
        productPresenter = mock(IProductManagementPresenter.class);
        productRequestDTO = new ProductRequestDTO("sku123", "Product Name", "Product Description", 100.0f, Category.Drink.getValue());
        responseModel = mock(IProductManagementPresenter.ProductResponseModel.class);

        productDTO = new OutputDataProductDTO(sku, "Product Name", "Product Description", 99.99f, "Category");
        responseModel = mock(IProductManagementPresenter.ProductResponseModel.class);

        managementProduct = new ManagementProduct(
                getProductListController,
                getProductBySkuController,
                registerProductController,
                updateProductController,
                removeProductController,
                productPresenter
        );
    }

    @Test
    public void testGetProducts_ExceptionHandling_EmptyResult() {
        when(getProductListController.getProducts()).thenThrow(new EmptyResultDataAccessException(1));

        ResponseEntity<?> response = managementProduct.getProducts();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
        ErrorViewModel error = (ErrorViewModel) response.getBody();
    }

    @Test
    public void testGetProductBySku_ExceptionHandling_EmptyResult() {
        when(getProductBySkuController.invoke(any())).thenThrow(new EmptyResultDataAccessException(1));

        ResponseEntity<?> response = managementProduct.getProductBySkuHTTP("sku123");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
        ErrorViewModel error = (ErrorViewModel) response.getBody();
    }



    @Test
    void testCreateProduct_WhenDataAccessExceptionThrown_ShouldReturnErrorResponse() {
        doThrow(new DataAccessException("Error during product registration") {}).when(registerProductController).invoke(
                anyString(), anyString(), anyString(), anyFloat(), anyString());

        ResponseEntity<?> response = managementProduct.createProductHTTP(productRequestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
        ErrorViewModel error = (ErrorViewModel) response.getBody();
        assertEquals("Erro ao realizar cadastro de produto", error.getMessage());
    }

    @Test
    void testCreateProduct_WhenIllegalArgumentExceptionThrown_ShouldReturnErrorResponse() {
        // Arrange: Simula a exceção IllegalArgumentException
        doThrow(new IllegalArgumentException("Invalid product data")).when(registerProductController).invoke(
                anyString(), anyString(), anyString(), anyFloat(), anyString());

        // Act: Chama o método
        ResponseEntity<?> response = managementProduct.createProductHTTP(productRequestDTO);

        // Assert: Verifica se a resposta é a esperada
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
        ErrorViewModel error = (ErrorViewModel) response.getBody();
        assertEquals(99, error.getCode());
        assertEquals("Invalid product data", error.getMessage());
    }

    @Test
    void testCreateProduct_WhenNoException_ShouldReturnCreatedResponse() {
        doNothing().when(registerProductController).invoke(
                anyString(), anyString(), anyString(), anyFloat(), anyString());

        ResponseEntity<?> response = managementProduct.createProductHTTP(productRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Product);
        Product createdProduct = (Product) response.getBody();
        assertEquals("sku123", createdProduct.getSku());
        assertEquals("Product Name", createdProduct.getName());
        assertEquals("Product Description", createdProduct.getDescription());
        assertEquals(100.0f, createdProduct.getMonetaryValue());
        assertEquals(Category.Drink.getValue(), createdProduct.getCategory());
    }

    @Test
    void testUpdateProduct_WhenDataAccessExceptionThrown_ShouldReturnErrorResponse() {
        doThrow(new DataAccessException("Product not found") {}).when(updateProductController).invoke(
                anyString(), anyString(), anyString(), anyFloat(), anyString());

        ResponseEntity<?> response = managementProduct.updateProductHTTP("sku123", productRequestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
        ErrorViewModel error = (ErrorViewModel) response.getBody();
        assertEquals(5, error.getCode());
        assertEquals("Produto não encontrado", error.getMessage());
    }

    @Test
    void testUpdateProduct_WhenIllegalArgumentExceptionThrown_ShouldReturnErrorResponse() {
        doThrow(new IllegalArgumentException("Invalid product data")).when(updateProductController).invoke(
                anyString(), anyString(), anyString(), anyFloat(), anyString());

        ResponseEntity<?> response = managementProduct.updateProductHTTP("sku123", productRequestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorViewModel);
        ErrorViewModel error = (ErrorViewModel) response.getBody();
        assertEquals(99, error.getCode());
        assertEquals("Invalid product data", error.getMessage());
    }

    @Test
    void testUpdateProduct_WhenNoException_ShouldReturnSuccessResponse() {
        doNothing().when(updateProductController).invoke(
                anyString(), anyString(), anyString(), anyFloat(), anyString());

        ResponseEntity<?> response = managementProduct.updateProductHTTP("sku123", productRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto atualizado com sucesso", response.getBody());
    }

    @Test
    void testGetProductBySkuHTTP_Success() {
        when(getProductBySkuController.invoke(sku)).thenReturn(productDTO);
        when(productPresenter.present(productDTO)).thenReturn(responseModel);

        ResponseEntity<?> response = managementProduct.getProductBySkuHTTP(sku);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseModel, response.getBody());

        verify(productPresenter, times(1)).present(productDTO);
    }
}
