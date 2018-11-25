package com.bukola;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExpensesTest {
    Expenses expenses;
    List<BigDecimal> actual;

    @BeforeAll
    void setUp() {
        expenses = new Expenses();
        actual = expenses.getExpensesList();
    }

    @Test
    @DisplayName("Given user enters 90")
    void setWeeklyExpenses() {
        expenses.setWeeklyExpenses(new BigDecimal(90));
    }

    @Test
    @DisplayName("expenses list should contain 90.00")
    void getExpensesListTest() {
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(new BigDecimal(90).setScale(2, RoundingMode.HALF_UP));
        assertEquals(expected, expenses.getExpensesList());
        System.out.printf("Expenses: %s%n", expenses.getExpensesList());
    }

    @Test
    @DisplayName("then total expenses to date should be 90.00")
    void getTotalExpensesTest() {
        BigDecimal expected = new BigDecimal(90);
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                expenses.getTotalWeeklyExpenses());
    }

    @Nested
    class ExpensesTest2{
        @Test
        @DisplayName("Given user then enters 0")
        void setWeeklyExpenses(){
            expenses.setWeeklyExpenses(new BigDecimal(0.50));
        }

        @Test
        @DisplayName("expenses list should contain 90.00 and 0.50")
        void getWeeklyExpensesList(){
            List<BigDecimal> expected = new ArrayList<>();
            expected.add(new BigDecimal(90).setScale(2, RoundingMode.HALF_UP));
            expected.add(new BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP));
            assertEquals(expected, expenses.getExpensesList());
            System.out.printf("Expenses: %s%n", expenses.getExpensesList());
        }

        @Test
        @DisplayName("Then total expenses to date should be 90.50")
        void getTotalExpenses(){
            BigDecimal expected = new BigDecimal(90.50);
            assertEquals(expected.setScale(2, RoundingMode.HALF_UP),
                    expenses.getTotalWeeklyExpenses());
            System.out.printf("Total expenses: %s%n", expenses.getTotalWeeklyExpenses());
        }

    }

}