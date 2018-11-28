package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NationalInsurance2Test {
    NationalInsurance2 ni2;
    List<BigDecimal> income;
    List<BigDecimal> actual;

    @BeforeAll
    void setUp() {
        BigDecimal threshold = new BigDecimal(6205);
        BigDecimal weeklyRate = new BigDecimal(2.95);
        ni2 = new NationalInsurance2(threshold, weeklyRate);
        income = new ArrayList<>();
        actual = new ArrayList<>();
    }

    @Test
    @DisplayName("Given low threshold is set at 6,205 and weekly rate is set at 2.95")
    void getNi2ThresholdAndRate() {
        assertEquals(new BigDecimal(6205), ni2.getNi2Threshold(), "threshold should be 6205");
        assertEquals(new BigDecimal(2.95).setScale(2, RoundingMode.HALF_UP),
                ni2.getWeeklyRate(), "weekly rate should be 2.95");
    }


    @Test
    @DisplayName("And user enters profit of 350")
    void calculate1() {
        income.add(new BigDecimal(350));
        System.out.printf("Income: %s%n", income);
        ni2.calculate(income);
        actual = ni2.getList();
        System.out.printf("NI2 list: %s%n", actual);
    }

    @Test
    @DisplayName("NI2 list payable should contain 0.00")
    void getNi2List1() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Then total NI2 payable till date should be 0.00")
    void getTotalNI2() {
        BigDecimal expected =new BigDecimal(0);
        System.out.printf("Total NI2 till date: %s%n%n", ni2.getTotalToDate());
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP), ni2.getTotalToDate());
    }

    @Nested
    class NationalInsurance2Test2{

        @Test
        @DisplayName("Given user then enters profit of 6000")
        void calculate(){
            income.add(new BigDecimal(6000));
            ni2.calculate(income);
            System.out.printf("profit: %s%n", income);
            actual = ni2.getList();
            System.out.printf("NI2 list: %s%n", actual);
        }

        @Test
        @DisplayName("NI2 list payable till date should contain 2.95 and 2.95")
        void getNi2List1(){
            List<BigDecimal> expected = new ArrayList<>();
            BigDecimal rate = new BigDecimal(2.95).setScale(2, RoundingMode.HALF_UP);
            expected.add(rate);
            expected.add(rate);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Then total NI2 payable till date should be 5.90")
        void getTotalNI2(){
            BigDecimal expected =new BigDecimal(5.90);
            System.out.printf("Total NI2 till date: %s%n", ni2.getTotalToDate());
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP), ni2.getTotalToDate());
        }
    }
}