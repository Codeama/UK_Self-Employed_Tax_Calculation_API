package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfitsTest {
    Profits profit;
    List<BigDecimal> wage;
    List<BigDecimal> expenses;

    @BeforeAll
    void setUp() {
        profit = new Profits();
        wage = new ArrayList<>();
        expenses  = new ArrayList<>();
        wage.add(new BigDecimal(500));
        expenses.add(new BigDecimal(44));
        wage.add(new BigDecimal(15));
        expenses.add(new BigDecimal(0));

    }

    @Test
    @DisplayName("Given wage for 2 weeks is 500 and 15")
    void getWages(){
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(500));
        expected.add(new BigDecimal(15));
        assertEquals(expected, wage);
        System.out.printf("Wages: %s%n", wage);
    }

    @Test
    @DisplayName("given expenses are 44 and 0")
    void getExpenses(){
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(44));
        expected.add(new BigDecimal(0));
        assertEquals(expected, expenses);
        System.out.printf("Expenses: %s%n", expenses);
    }

    @Test
    @DisplayName("Calculate earnings")
    void calculateEarnings1() {
        profit.calculateEarnings(wage, expenses);
    }

    @Test
    @DisplayName("Corresponding earnings should be 456 and 15")
    void getEarningsList() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(456).setScale(2, RoundingMode.HALF_UP));
        expected.add(new BigDecimal(15).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, profit.getEarningsList());
        System.out.printf("Earnings: %s%n", profit.getEarningsList());
    }

    @Nested
    @DisplayName("Running total")
    class ProfitsTest1 {

        @Test
        @DisplayName("Total earnings till date should be 471.00")
        void getTotalEarnings() {
            BigDecimal expected = new BigDecimal(471);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    profit.getTotalEarnings(), "total should be 471.00");
            System.out.printf("Running total: %s%n", profit.getTotalEarnings());
        }
    }
}