package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Expenses {
    BigDecimal totalWeeklyExpenses;
    List<BigDecimal> expensesList;

    public Expenses(){
        expensesList = new ArrayList<>();
    }

    public void setWeeklyExpenses(BigDecimal weeklyExpenses) {
        BigDecimal expenses = weeklyExpenses.setScale(2, RoundingMode.HALF_UP);
        expensesList.add(expenses);
    }

    public List<BigDecimal> getExpensesList() {
        return expensesList;
    }

    public BigDecimal getTotalWeeklyExpenses() {
        totalWeeklyExpenses = new BigDecimal(0);
        for(BigDecimal expenses: expensesList){
            totalWeeklyExpenses = totalWeeklyExpenses.add(expenses);
        }
        return totalWeeklyExpenses.setScale(2, RoundingMode.HALF_UP);
    }
}
