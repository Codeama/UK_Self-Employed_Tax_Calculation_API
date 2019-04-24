package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for computing total profit each week.
 * NOTE: smallest unit of profit is the total of a week's profit.
 * Profit is actual taxable earnings/income
 * Can be used in conjunction with Wages and Expenses classes.
 * All results are rounded up using BigDecimal RoundingMode.HALF_UP.
 * @see Wages
 * @see Expenses
 */
public class Profits {
    private List<BigDecimal> wages;
    List<BigDecimal> expenses;
    private List<BigDecimal> earningsList;
    private BigDecimal totalEarnings;

    /**
     * Class constructor.
     * @param wages: a list of weekly payments/wages entered
     * @see Wages#getWeeklyPayList()
     * @param expenses: a list of weekly expenses
     * @see Expenses#getAllExpenses()
     */
    public Profits(List<BigDecimal> wages, List<BigDecimal> expenses){
        this.wages = wages;
        this.expenses = expenses;
        earningsList = new ArrayList<>();
    }

    /**
     * Works out actual earnings for tax purposes.
     * In addition to working out actual earnings,
     * this method also checks that the number of records in Wages and Expenses
     * are the same. If not, the list that is short is updated accordingly with zero value(s)
     * on the assumption there were no values entered for the corresponding entity.
     * This is to prevent an IndexOutOfBoundsException.
     */
    public void calculateEarnings(){
        if (wages.size() > expenses.size()){
            //check size difference
            int sizeUp = wages.size() - expenses.size();
            for(int i = 0; i < sizeUp; i++){
                //add zero(s) to expenses to match up(this assumes no expenses for the week(s))
                expenses.add(BigDecimal.ZERO);
            }
        }

        if(expenses.size() > wages.size()){
            //check size difference
            int sizeUp = expenses.size() - wages.size();
            for(int i = 0; i < sizeUp; i++){
               //add zero(s) to expenses to match up(this assumes no income for the week(s))
               wages.add(BigDecimal.ZERO);
            }
        }
        for(int i=0; i < wages.size(); i++){
            BigDecimal profits;
            profits = wages.get(i).subtract(expenses.get(i));
            BigDecimal roundUp = profits.setScale(2, RoundingMode.HALF_UP);
            earningsList.add(roundUp);
        }
    }

    /**
     * returns a list of earnings calculated to date based on Wages and Expenses.
     * @return list of earnings/profits recorded to date
     */
    public List<BigDecimal> getEarningsList(){
        return earningsList;
    }

    /**
     * returns a sum total of earnings to date.
     * @return a running total of earnings
     */
    public BigDecimal getTotalEarnings() {
        totalEarnings = new BigDecimal(0);
        for(BigDecimal earnings: earningsList){
            totalEarnings = totalEarnings.add(earnings);
        }
        return totalEarnings.setScale(2, RoundingMode.HALF_UP);
    }

}
