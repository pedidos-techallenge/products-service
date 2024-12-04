package br.com.fiap.techchallange.core.entity.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangeNotAllowedOrderExceptionTest {

    @Test
    void testChangeNotAllowedOrderExceptionMessage() {
        String expectedMessage = "Alteração não permitida na ordem";

        ChangeNotAllowedOrderException exception = new ChangeNotAllowedOrderException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção não é a esperada.");
    }

    @Test
    void testChangeNotAllowedOrderException() {
        String expectedMessage = "Alteração não permitida na ordem";

        ChangeNotAllowedOrderException exception = new ChangeNotAllowedOrderException(expectedMessage);

        assertNotNull(exception, "A exceção não foi criada.");

        assertTrue(exception instanceof RuntimeException, "A exceção não é uma instância de RuntimeException.");
    }
}
