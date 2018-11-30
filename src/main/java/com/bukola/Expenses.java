package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bukola Jimoh
 * Class for recording expenses on a weekly basis as List.
 * All results are rounded up with BigDecimal RoundingMode.HALF_UP.
 */
public class Expenses {
    private BigDecimal totalWeeklyExpenses;
    private List<BigDecimal> expensesList;

    /**
     * Class constructor.
     */
    public Expenses(){
        expensesList = new ArrayList<>();
    }

    /**
     * adds weekly expenses to a list rounded up with BigDecimal RoundingMode.HALF_UP.
     * @param weeklyExpenses expense of type BigDecimal
     */
    public void addWeeklyExpense(BigDecimal weeklyExpenses) {
        BigDecimal expenses = weeklyExpenses.setScale(2, RoundingMode.HALF_UP);
        expensesList.add(expenses);
    }

    /**
     * a list view of expenses entered to date.
     * @return a list of type BigDecimal
     */
    public List<BigDecimal> getExpensesList() {
        return expensesList;
    }

    /**
     * sum of all expenses entered to date rounded up with BigDecimal RoundingMode.HALF_UP.
     * @return a running total of all expenses to date
     */
    public BigDecimal getTotalWeeklyExpenses() {
        totalWeeklyExpenses = new BigDecimal(0);

        for(BigDecimal expenses: expensesList){
            totalWeeklyExpenses = totalWeeklyExpenses.add(expenses);
        }
        return totalWeeklyExpenses.setScale(2, RoundingMode.HALF_UP);
    }

/*
    public static void main(String[] args){
        Expenses ex = new Expenses();
        ex.addWeeklyExpense(new BigDecimal(55));
        System.out.printf("Expense list: %s%n", ex.getExpensesList());
    }
*/
}
