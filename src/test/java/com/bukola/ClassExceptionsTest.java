package com.bukola;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClassExceptionsTest {
    @Test
    @DisplayName("IncomeTax constructor throws IllegalArgumentException if argument is <= 0")
    void testIncomeTaxException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new IncomeTax(ZERO);
        });
    }

    @Test
    @DisplayName("NationalInsurance2 throws IllegalArgumentException if either argument is <= 0")
    void testNationalInsurance2Exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NationalInsurance2(ZERO, ONE);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new NationalInsurance2(ONE, ZERO);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new NationalInsurance2(ZERO, ZERO);
        });
    }

    @Test
    @DisplayName("NationalInsurance4 throws IllegalArgumentException if either argument is <= 0")
    void testNationalInsurance4Exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NationalInsurance4(ZERO, ONE);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new NationalInsurance4(ONE, ZERO);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new NationalInsurance4(ZERO, ZERO);
        });
    }


}
