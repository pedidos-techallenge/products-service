package br.com.fiap.techchallange.core.entity.vo;

import br.com.fiap.techchallange.core.entity.vo.MonetaryValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class MonetaryValueTests {

    @Test
    public void testConstructorWithAmountAndCurrency() {
        BigDecimal amount = new BigDecimal("100.50");
        String currency = "USD";
        MonetaryValue monetaryValue = new MonetaryValue(amount, currency);
        assertEquals(amount.floatValue(), monetaryValue.getValue());
        assertEquals(currency, monetaryValue.getCurrency());
    }

    @Test
    public void testConstructorWithAmountOnly() {
        BigDecimal amount = new BigDecimal("200.00");

        MonetaryValue monetaryValue = new MonetaryValue(amount);
        assertEquals(amount.floatValue(), monetaryValue.getValue());
        assertEquals("BRL", monetaryValue.getCurrency()); // Default currency
    }

    @Test
    public void testGetValue() {
        BigDecimal amount = new BigDecimal("50.75");
        MonetaryValue monetaryValue = new MonetaryValue(amount);

        float value = monetaryValue.getValue();
        assertEquals(amount.floatValue(), value);
    }

    @Test
    public void testNegativeAmountShouldThrowException() {
        BigDecimal negativeAmount = new BigDecimal("-10.00");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new MonetaryValue(negativeAmount);
        });
        assertEquals("amount of MonetaryValue cannot be less than to zero", exception.getMessage());
    }

    @Test
    public void testCurrencyShouldBeSetProperly() {
        BigDecimal amount = new BigDecimal("300.00");
        MonetaryValue monetaryValue = new MonetaryValue(amount, "EUR");
        assertEquals("EUR", monetaryValue.getCurrency());
    }
}
