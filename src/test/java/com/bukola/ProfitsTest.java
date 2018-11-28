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
        wage.add(new BigDecimal(15));
        wage.add(new BigDecimal(300));
        expenses.add(new BigDecimal(44));

    }

    @Test
    @DisplayName("Given wages for 3 weeks [500, 15, 300] and only week 1 expense [44] entered")
    void getWages(){
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(500));
        expected.add(new BigDecimal(15));
        expected.add(new BigDecimal(300));
        assertEquals(expected, wage);
        System.out.printf("Wages: %s%n", wage);
        System.out.printf("Expenses:%s%n", expenses);
    }

    @Test
    @DisplayName("Expense list should contain [44.00, 0.00, 0.00]")
    void getExpenses1(){
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(44));
        expected.add(new BigDecimal(0));
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
    @DisplayName("Corresponding earnings should be 456 and 15 and 300")
    void getEarningsList() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(456).setScale(2, RoundingMode.HALF_UP));
        expected.add(new BigDecimal(15).setScale(2, RoundingMode.HALF_UP));
        expected.add(new BigDecimal(300).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, profit.getEarningsList());
        System.out.printf("Earnings: %s%n", profit.getEarningsList());
    }

    @Nested
    @DisplayName("Running total")
    class ProfitsTest1 {

        @Test
        @DisplayName("Total earnings till date should be 771.00")
        void getTotalEarnings() {
            BigDecimal expected = new BigDecimal(771);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    profit.getTotalEarnings(), "total should be 771.00");
            System.out.printf("Running total: %s%n", profit.getTotalEarnings());
        }
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfitsTest2{
    Profits profit;
    List<BigDecimal> wage;
    List<BigDecimal> expenses;

    @BeforeAll
    void setUp() {
        profit = new Profits();
        wage = new ArrayList<>();
        expenses  = new ArrayList<>();
        wage.add(new BigDecimal(0));
        wage.add(new BigDecimal(15));
        expenses.add(new BigDecimal(25));
        expenses.add(new BigDecimal(44));
        expenses.add(new BigDecimal(250));

    }

    @Test
    @DisplayName("Given only wages for 2 weeks [0, 15] and expenses for 3 weeks [25, 44, 250] are entered")
    void getExpenses1(){
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(25));
        expected.add(new BigDecimal(44));
        expected.add(new BigDecimal(250));
        assertEquals(expected, expenses);
        System.out.printf("Expenses: %s%n", expenses);
    }

    @Test
    @DisplayName("Wages list should contain [0, 15, 0]")
    void getWages(){
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(0));
        expected.add(new BigDecimal(15));
        expected.add(new BigDecimal(0));
        assertEquals(expected, wage);
        System.out.printf("Wages: %s%n", wage);
    }

    @Test
    @DisplayName("Calculate earnings")
    void calculateEarnings1() {
        profit.calculateEarnings(wage, expenses);
    }

    @Test
    @DisplayName("Corresponding earnings should be -25 and -29 and -250")
    void getEarningsList() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(-25 ).setScale(2, RoundingMode.HALF_UP));
        expected.add(new BigDecimal(-29).setScale(2, RoundingMode.HALF_UP));
        expected.add(new BigDecimal(-250).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, profit.getEarningsList());
        System.out.printf("Earnings: %s%n", profit.getEarningsList());
    }

    @Nested
    @DisplayName("Running total")
    class ProfitsTest3 {

        @Test
        @DisplayName("Total earnings till date should be -304.00")
        void getTotalEarnings() {
            BigDecimal expected = new BigDecimal(-304);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    profit.getTotalEarnings(), "total should be 246.00");
            System.out.printf("Running total: %s%n", profit.getTotalEarnings());
        }
    }

}