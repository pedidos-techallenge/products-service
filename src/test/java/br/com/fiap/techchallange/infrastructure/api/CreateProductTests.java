package br.com.fiap.techchallange.infrastructure.api;

import br.com.fiap.techchallange.adapters.controllers.managementproduct.IRegisterProductController;
import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.outputboundary.presenters.managementproduct.IProductManagementPresenter;
import br.com.fiap.techchallange.infrastructure.dto.ProductRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreateProductTests {

    private MockMvc mockMvc;

    @Mock
    private IRegisterProductController registerProductController;

    @Mock
    private IProductManagementPresenter productPresenter;

    @InjectMocks
    private ManagementProduct managementProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(managementProduct).build();
    }

    @Test
    void createProductHTTP_ShouldReturnCreated_WhenValidRequest() throws Exception {
        ProductRequestDTO validProduct = new ProductRequestDTO("sku123", "Produto Teste", "Descrição do produto", 100.0F, Category.Drink.getValue());

        doNothing().when(registerProductController).invoke(
                validProduct.sku(), validProduct.name(), validProduct.description(), validProduct.monetaryValue(), validProduct.category()
        );

        mockMvc.perform(post("/v1/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"sku\": \"sku123\", \"name\": \"Produto Teste\", \"description\": \"Descrição do produto\", \"monetaryValue\": 100.0, \"category\": \"Bebida\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sku").value("sku123"))
                .andExpect(jsonPath("$.name").value("Produto Teste"))
                .andExpect(jsonPath("$.description").value("Descrição do produto"))
                .andExpect(jsonPath("$.monetaryValue").value(100.0))
                .andExpect(jsonPath("$.category").value("Bebida"));

        verify(registerProductController, times(1)).invoke(
                validProduct.sku(), validProduct.name(), validProduct.description(), validProduct.monetaryValue(), validProduct.category()
        );
    }
}
