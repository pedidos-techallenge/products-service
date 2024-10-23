package br.com.fiap.techchallange.adapters.controllers.managementproduct;

import br.com.fiap.techchallange.core.usecase.dto.product.OutputDataProductDTO;

public interface IGetProductBySkuController {
    OutputDataProductDTO invoke(String sku);
}
