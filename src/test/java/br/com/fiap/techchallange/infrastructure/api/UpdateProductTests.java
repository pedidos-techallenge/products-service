package br.com.fiap.techchallange.infrastructure.api;

import br.com.fiap.techchallange.adapters.controllers.managementproduct.IUpdateProductController;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.infrastructure.dto.ProductRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProductTests {

    @Mock
    IUpdateProductController updateProductController;

    @InjectMocks
    ManagementProduct managementProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateProductHTTP_Success() {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO("123", "Produto Teste Atualizado", "Descrição Atualizada", 150.0F, Category.Drink.getValue());

        doNothing().when(updateProductController).invoke(eq("123"), eq("Produto Teste Atualizado"), eq("Descrição Atualizada"), eq(150.0F), eq(Category.Drink.getValue()));

        ResponseEntity<?> response = managementProduct.updateProductHTTP("123", productRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto atualizado com sucesso", response.getBody());
    }
}
