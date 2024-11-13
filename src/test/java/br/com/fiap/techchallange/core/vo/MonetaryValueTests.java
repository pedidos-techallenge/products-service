package br.com.fiap.techchallange.core.vo;

import br.com.fiap.techchallange.core.entity.vo.MonetaryValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class MonetaryValueTests {

    @Test
    public void testConstructorWithAmountAndCurrency() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.50");
        String currency = "USD";

        // Act
        MonetaryValue monetaryValue = new MonetaryValue(amount, currency);

        // Assert
        assertEquals(amount.floatValue(), monetaryValue.getValue());
        assertEquals(currency, monetaryValue.getCurrency());
    }

    @Test
    public void testConstructorWithAmountOnly() {
        // Arrange
        BigDecimal amount = new BigDecimal("200.00");

        // Act
        MonetaryValue monetaryValue = new MonetaryValue(amount);

        // Assert
        assertEquals(amount.floatValue(), monetaryValue.getValue());
        assertEquals("BRL", monetaryValue.getCurrency()); // Default currency
    }

    @Test
    public void testGetValue() {
        // Arrange
        BigDecimal amount = new BigDecimal("50.75");
        MonetaryValue monetaryValue = new MonetaryValue(amount);

        // Act
        float value = monetaryValue.getValue();

        // Assert
        assertEquals(amount.floatValue(), value);
    }

    @Test
    public void testNegativeAmountShouldThrowException() {
        // Arrange
        BigDecimal negativeAmount = new BigDecimal("-10.00");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new MonetaryValue(negativeAmount);
        });
        assertEquals("amount of MonetaryValue cannot be less than to zero", exception.getMessage());
    }

    @Test
    public void testCurrencyShouldBeSetProperly() {
        // Arrange
        BigDecimal amount = new BigDecimal("300.00");

        // Act
        MonetaryValue monetaryValue = new MonetaryValue(amount, "EUR");

        // Assert
        assertEquals("EUR", monetaryValue.getCurrency());
    }
}
