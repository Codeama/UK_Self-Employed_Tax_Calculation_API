package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NationalInsurance4Test {
    NationalInsurance4 ni4;
    List<BigDecimal> income;
    List<BigDecimal> actual;

    @BeforeAll
    void setUp() {
        ni4 = new NationalInsurance4();
        income = new ArrayList<>();
        actual = new ArrayList<>();
    }

    @Test
    @DisplayName("Given low threshold is 8,424 and high threshold is 46,350")
    void setLowThreshold1() {
        ni4.setLowThreshold(new BigDecimal(8424));
        ni4.setHighThreshold(new BigDecimal(46350));
        assertEquals(new BigDecimal(8424), ni4.getLowThreshold(), "low threshold should be 8424");
        assertEquals(new BigDecimal(46350), ni4.getHighThreshold(), "high threshold should be 46350");
    }

    @Test
    @DisplayName("and user enters earnings of 350")
    void calculateTest() {
        income.add(new BigDecimal(350));
        ni4.calculate(income);
        actual = ni4.getList();
        System.out.printf("Earnings: %s%n", income);
    }

    @Test
    @DisplayName("then NI4 list should contain 0.00")
    void getClassNI4List1() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, actual);
        System.out.printf("NI4:  %s%n%n", actual);
    }

    @Test
    @DisplayName("and total NI4 payable till date should be 0.00")
    void getTotalToDate1() {
        BigDecimal expected = new BigDecimal(0);
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                ni4.getTotalToDate());
    }

    @Nested
    class NationalInsurance4Test2{
        @Test
        @DisplayName("Given user then enters income of 9450")
        void calculate(){
            income.add(new BigDecimal(9450));
            ni4.calculate(income);
            actual = ni4.getList();
            System.out.printf("Earnings: %s%n", income);
        }

        @Test
        @DisplayName("Ni4 list should now contain 31.50 and 850.50")
        void getNI4ClassList1(){
            List<BigDecimal> expected = new ArrayList<>();
            expected.add(new BigDecimal(31.50).setScale(2, RoundingMode.HALF_UP));
            expected.add(new BigDecimal(850.50).setScale(2, RoundingMode.HALF_UP));
            assertEquals(expected, actual);
            System.out.printf("NI4: %s%n", actual);
        }

        @Test
        @DisplayName("and total NI4 payable till date should be 882.00")
        void getTotalToDate1(){
            BigDecimal expected = new BigDecimal(882);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    ni4.getTotalToDate());
            System.out.printf("Total: %s%n", ni4.getTotalToDate());
        }
    }

}