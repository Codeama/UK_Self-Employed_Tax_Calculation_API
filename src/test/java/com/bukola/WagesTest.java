package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WagesTest {
    Wages wage;
    List<BigDecimal> payList;

    @BeforeAll
    void setUp() {
        wage = new Wages();
        payList = wage.getWeeklyPayList();
    }

    @DisplayName("Given user enters 100.97")
    @Test
    void setPay() {
        wage.setPay(new BigDecimal(100.97));
    }


    @Test
    @DisplayName("list should contain 100.97")
    void getWeeklyPayList() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(100.97).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, payList);//wage.getWeeklyPayList());
    }

    @Test
    @DisplayName("then total pay to date should be 100.97")
    void getTotalPayToDate() {
        BigDecimal expected = new BigDecimal(100.97);
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP), wage.getTotalPayToDate());
    }


    @Nested
    class WagesTest2{
        @DisplayName("Given user then enters 100 and 350")
        @Test
        void setPay(){
            wage.setPay(new BigDecimal(100));
            wage.setPay(new BigDecimal(350));
        }

        @Test
        @DisplayName("list should contain 100.97, 100 and 350")
        void getWeeklyPayList() {
            List<BigDecimal> expected = new ArrayList<>();
            expected.add(new BigDecimal(100.97).setScale(2, RoundingMode.HALF_UP));
            expected.add(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));
            expected.add(new BigDecimal(350).setScale(2, RoundingMode.HALF_UP));
            assertEquals(expected, payList);//wage.getWeeklyPayList());
        }

        @Test
        @DisplayName("then total pay to date should be 500.97")
        void getTotalPayToDate() {
            BigDecimal expected = new BigDecimal(550.97);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP), wage.getTotalPayToDate());
        }

    }


}