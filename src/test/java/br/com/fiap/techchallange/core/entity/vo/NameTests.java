package br.com.fiap.techchallange.core.entity.vo;

import br.com.fiap.techchallange.core.entity.vo.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTests {

    @Test
    void testValidName() {
        Name name = new Name("João Silva");
        assertEquals("João Silva", name.getNameValue());
    }

    @Test
    void testValidNameWithSpaces() {
        // Testa um nome com espaços
        Name name = new Name("Maria da Silva");
        assertEquals("Maria da Silva", name.getNameValue());
    }

    @Test
    void testNameWithDigits() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name("João123");
        });
        assertEquals("Nome é inválido!", exception.getMessage());
    }

    @Test
    void testNameWithSpecialCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name("João! Silva");
        });
        assertEquals("Nome é inválido!", exception.getMessage());
    }

    @Test
    void testEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name("");
        });
        assertEquals("Nome é inválido!", exception.getMessage());
    }

    @Test
    void testNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(null);
        });
        assertEquals("Nome é inválido!", exception.getMessage());
    }
}
