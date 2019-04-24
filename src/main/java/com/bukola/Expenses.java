package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bukola Jimoh
 * Class for recording expenses on a weekly basis.
 * All results are rounded up using BigDecimal RoundingMode.HALF_UP.
 */
public class Expenses {
    private BigDecimal totalExpenses;
    private List<BigDecimal> expensesList;

    /**
     * This is the class constructor.
     */
    public Expenses(){
        expensesList = new ArrayList<>();
    }

    /**
     * adds up each expense.
     * @param expense a single unit of expense
     */
    public void addExpense(BigDecimal expense) {
        BigDecimal expenses = expense.setScale(2, RoundingMode.HALF_UP);
        expensesList.add(expenses);
    }

    /**
     * a list of expenses recorded to date.
     * @return a list of type BigDecimal
     */
    public List<BigDecimal> getAllExpenses() {
        return expensesList;
    }

    /**
     * this method returns a sum of all expenses recorded to date.
     * @return a running total of all expenses to date
     */
    public BigDecimal getTotalExpenses() {
        totalExpenses = new BigDecimal(0);

        for(BigDecimal expenses: expensesList){
            totalExpenses = totalExpenses.add(expenses);
        }
        return totalExpenses.setScale(2, RoundingMode.HALF_UP);
    }

/*
    public static void main(String[] args){
        Expenses ex = new Expenses();
        ex.addExpense(new BigDecimal(55));
        System.out.printf("Expense list: %s%n", ex.getAllExpenses());
    }
*/
}
