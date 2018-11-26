package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TotalTaxCalculationTest {

    TotalTaxCalculation tax;
    BigDecimal personalAllowance;
    BigDecimal ni4LowThreshold1;
    BigDecimal ni4HighThreshold1;
    BigDecimal ni2Threshold1;
    BigDecimal ni2Rate1;
    List<BigDecimal> income;
    List<BigDecimal> totals;

    @BeforeAll
    @Test
    @DisplayName("Given personal allowance(11500), NI4 thresholds(6424, 46350)" +
            "NI2 threshold(6205) and NI2 weekly rate(2.95) are set")
    void setUp() {
        personalAllowance = new BigDecimal(11500);
        ni4LowThreshold1 = new BigDecimal(6424);
        ni4HighThreshold1 = new BigDecimal(46350);
        ni2Threshold1 = new BigDecimal(6205);
        ni2Rate1 = new BigDecimal(2.95);
        tax = new TotalTaxCalculation(personalAllowance, ni4LowThreshold1,
                ni4HighThreshold1, ni2Threshold1, ni2Rate1);
        income = new ArrayList<>();
        totals = new ArrayList<>();
    }

/*
    @Disabled
    @Test
    @DisplayName("Given personal allowance(11500), NI4 thresholds(6424, 46350)" +
            "NI2 threshold(6205) and NI2 weekly rate(2.95) are set")
    void setTaxItems() {
        assertEquals(new BigDecimal(11500), tax.getPersonalAllowance(),
                "personal allowance should be 11500");
        assertEquals(new BigDecimal(6424), tax.getNi4LowThreshold(),
                "low NI4 should be 6424");
        assertEquals(new BigDecimal(46350), tax.getNi4HighThreshold(),
                "high NI4 should be 46350");
        assertEquals(new BigDecimal(6205), tax.getNi2Threshold(),
                "NI2threshold should be 6205");
        assertEquals(new BigDecimal(2.95), tax.getNi2Rate(), "NI2 rate should be 2.95");
    }
*/

    @Nested
    @DisplayName("if user enters earnings for 3 weeks")
    class TotalTaxCalculationTest1 {
        @Test
        @DisplayName("[100, 350, 6000]")
        void calculate() {
            income.add(new BigDecimal(100));
            income.add(new BigDecimal(350));
            income.add(new BigDecimal(6000));
            System.out.printf("Earnings:  %s%n", income);
            tax.calculate(income);
            totals = tax.getList();
            System.out.printf("Total tax: %s%n", totals);
        }

        @Test
        @DisplayName("Income tax for each week should be [-12.28, 60.22, 1698.72]")
        void getList1() {
            List<BigDecimal> expected = new ArrayList<>();
            expected.add(new BigDecimal(-12.28).setScale(2, RoundingMode.HALF_UP));
            expected.add(new BigDecimal(60.22).setScale(2, RoundingMode.HALF_UP));
            expected.add(new BigDecimal(1698.72).setScale(2, RoundingMode.HALF_UP));
            assertEquals(expected, totals);
        }

        @Test
        @DisplayName("Running total of tax payable should be 1746.66")
        void getTotalToDate1() {
            BigDecimal expected = new BigDecimal(1746.66);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    tax.getTotalToDate());
        }

    }


}