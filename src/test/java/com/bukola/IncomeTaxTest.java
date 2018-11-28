package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IncomeTaxTest {
    IncomeTax tax;
    List<BigDecimal> actual;
    List<BigDecimal> profit;
    List<BigDecimal> expected;

    @BeforeAll
    void setUp() {
        tax = new IncomeTax(new BigDecimal(11500));
        //tax.setPersonalTaxAllowance(new BigDecimal(11500));
        profit = new ArrayList<>();
        expected = new ArrayList<>();
        actual = new ArrayList<>();
    }


    @Test
    @DisplayName("Given personal allowance rate is set at 11,500")
    void setAndGetPersonalTaxAllowance() {
        BigDecimal expected = new BigDecimal(11500);
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                tax.getPersonalTaxAllowance());
    }

    @Test
    @DisplayName("And user enters profit of 100 for a week")
    void calculate() {
        profit.add(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));
        tax.calculate(profit);
        actual = tax.getList();
        System.out.printf("Earnings: %s%n", profit);
    }

    @Test
    @DisplayName("then 20% tax for the week should be -24.23")
    void getTax(){
        expected = new ArrayList<>();
        expected.add(new BigDecimal(-24.23).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, actual);
        System.out.printf("20%% tax:   %s%n", actual);
    }

    @Test
    @DisplayName("and total tax payable till date should be -24.23")
    void getTotal() {
        BigDecimal expected = new BigDecimal(-24.23);
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                tax.getTotalToDate());
        System.out.printf("Total tax: %s%n%n", tax.getTotalToDate());
    }

    @Nested
    class IncomeTaxTest2{
        @Test
        @DisplayName("Given user then enters profit of 350")
        void calculate(){
            profit.add(new BigDecimal(350).setScale(2, RoundingMode.HALF_UP));
            tax.calculate(profit);
            actual = tax.getList();
            System.out.printf("Earnings: %s%n", profit);
        }

        @Test
        @DisplayName("20% tax list should contain -24.33 and 25.77")
        void getTax(){
            expected.add(new BigDecimal(25.77).setScale(2, RoundingMode.HALF_UP));
            assertEquals(expected, actual);
            System.out.printf("20%% tax: %s%n", actual);
        }

        @Test
        @DisplayName("and total tax payable till date should be 1.54")
        void getTotal(){
            BigDecimal expected = new BigDecimal(1.54);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    tax.getTotalToDate());
            System.out.printf("Total: %s%n", tax.getTotalToDate());
        }
    }
}