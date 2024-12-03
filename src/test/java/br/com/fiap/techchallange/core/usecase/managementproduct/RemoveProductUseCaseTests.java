package br.com.fiap.techchallange.core.usecase.managementproduct;

import br.com.fiap.techchallange.adapters.gateways.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RemoveProductUseCaseTests {

    private IProductRepository repositoryMock;
    private RemoveProductUseCase useCase;

    @BeforeEach
    void setup() {
        repositoryMock = mock(IProductRepository.class);
        useCase = new RemoveProductUseCase(repositoryMock);
    }

    @Test
    void testInvoke_RemovesProduct() {
        String sku = "SKU001";

        useCase.invoke(sku);

        ArgumentCaptor<String> skuCaptor = ArgumentCaptor.forClass(String.class);
        verify(repositoryMock, times(1)).deleteProduct(skuCaptor.capture());

        String capturedSku = skuCaptor.getValue();
        assertEquals(sku, capturedSku, "O SKU capturado deve ser igual ao SKU fornecido");
    }
}
