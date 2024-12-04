package br.com.fiap.techchallange.core.usecase.outputboundary.presenters.managementproduct;

import br.com.fiap.techchallange.core.entity.enums.Category;
import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagementPresenterTest {

    @Test
    void testPresentList() {
        OutputDataProductDTO productDTO1 = new OutputDataProductDTO("sku123", "Produto 1", "Descrição do Produto 1", 100.0f, Category.Drink.getValue());
        OutputDataProductDTO productDTO2 = new OutputDataProductDTO("sku456", "Produto 2", "Descrição do Produto 2", 200.0f, Category.Drink.getValue());

        List<OutputDataProductDTO> productDTOList = List.of(productDTO1, productDTO2);

        IProductManagementPresenter presenter = new IProductManagementPresenter() {
            @Override
            public List<IProductManagementPresenter.ProductResponseModel> presentList(List<OutputDataProductDTO> productDTOs) {
                return List.of(
                        new IProductManagementPresenter.ProductResponseModel(productDTO1),
                        new IProductManagementPresenter.ProductResponseModel(productDTO2)
                );
            }

            @Override
            public IProductManagementPresenter.ProductResponseModel present(OutputDataProductDTO productDTO) {
                return new IProductManagementPresenter.ProductResponseModel(productDTO);
            }
        };

        List<IProductManagementPresenter.ProductResponseModel> result = presenter.presentList(productDTOList);

        assertEquals(2, result.size(), "Deve retornar 2 produtos");

        assertEquals("sku123", result.get(0).getSku(), "O SKU do primeiro produto está incorreto");
        assertEquals("Produto 1", result.get(0).getName(), "O nome do primeiro produto está incorreto");
        assertEquals("Descrição do Produto 1", result.get(0).getDescription(), "A descrição do primeiro produto está incorreta");
        assertEquals(100.0f, result.get(0).getValue(), 0.01f, "O valor do primeiro produto está incorreto");

        assertEquals("sku456", result.get(1).getSku(), "O SKU do segundo produto está incorreto");
        assertEquals("Produto 2", result.get(1).getName(), "O nome do segundo produto está incorreto");
        assertEquals("Descrição do Produto 2", result.get(1).getDescription(), "A descrição do segundo produto está incorreta");
        assertEquals(200.0f, result.get(1).getValue(), 0.01f, "O valor do segundo produto está incorreto");
    }

    @Test
    void testPresent() {
        OutputDataProductDTO productDTO = new OutputDataProductDTO("sku123", "Produto Teste", "Descrição do produto", 100.0f, Category.Drink.getValue());

        IProductManagementPresenter presenter = new IProductManagementPresenter() {
            @Override
            public List<IProductManagementPresenter.ProductResponseModel> presentList(List<OutputDataProductDTO> productDTOs) {
                return null;
            }

            @Override
            public IProductManagementPresenter.ProductResponseModel present(OutputDataProductDTO productDTO) {
                return new IProductManagementPresenter.ProductResponseModel(productDTO);
            }
        };

        IProductManagementPresenter.ProductResponseModel result = presenter.present(productDTO);

        assertEquals("sku123", result.getSku(), "O SKU do produto está incorreto");
        assertEquals("Produto Teste", result.getName(), "O nome do produto está incorreto");
        assertEquals("Descrição do produto", result.getDescription(), "A descrição do produto está incorreta");
        assertEquals(100.0f, result.getValue(), 0.01f, "O valor do produto está incorreto");
    }
}
