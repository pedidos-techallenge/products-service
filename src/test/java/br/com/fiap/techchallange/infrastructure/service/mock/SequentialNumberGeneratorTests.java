package br.com.fiap.techchallange.infrastructure.service.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SequentialNumberGeneratorTests {

    private SequentialNumberGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = SequentialNumberGenerator.getInstance();
        generator.reset();
    }

    @Test
    public void testGenerateNextNumber() {
        Integer nextNumber = generator.generateNextNumber();
        assertEquals(1, nextNumber, "O próximo número deve ser 1");

        nextNumber = generator.generateNextNumber();
        assertEquals(2, nextNumber, "O próximo número deve ser 2");
    }

    @Test
    public void testGetFormattedCurrentNumber() {
        generator.generateNextNumber();
        generator.generateNextNumber();

        String formattedNumber = generator.getFormattedCurrentNumber();
        assertEquals("00002", formattedNumber, "O número formatado deve ser 00002");
    }

    @Test
    public void testReset() {
        generator.generateNextNumber();
        generator.reset();

        Integer nextNumber = generator.generateNextNumber();
        assertEquals(1, nextNumber, "Após o reset, o próximo número deve ser 1");
    }

    @Test
    public void testGenerateNextNumberMaxLimit() {
        for (int i = 0; i < 10000; i++) {
            generator.generateNextNumber();
        }

        assertThrows(IllegalStateException.class, generator::generateNextNumber,
                "Deveria lançar uma exceção quando o limite de 10000 for atingido");
    }

    @Test
    public void testSingletonInstance() {
        SequentialNumberGenerator generator1 = SequentialNumberGenerator.getInstance();
        SequentialNumberGenerator generator2 = SequentialNumberGenerator.getInstance();

        assertSame(generator1, generator2, "As instâncias devem ser as mesmas (Singleton)");
    }
}
