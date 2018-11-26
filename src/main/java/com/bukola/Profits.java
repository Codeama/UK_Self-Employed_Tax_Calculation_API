package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Profits {
    private List<BigDecimal> earningsList;
    private BigDecimal totalEarnings;

    public Profits(){
        earningsList = new ArrayList<>();
    }


    public void calculateEarnings(List<BigDecimal> wages, List<BigDecimal> expenses){
        for(int i=0; i < wages.size(); i++){
            BigDecimal profits;
            profits = wages.get(i).subtract(expenses.get(i));
            BigDecimal roundUp = profits.setScale(2, RoundingMode.HALF_UP);
            earningsList.add(roundUp);
        }
    }

    public List<BigDecimal> getEarningsList(){
        return earningsList;
    }

    public BigDecimal getTotalEarnings() {
        totalEarnings = new BigDecimal(0);
        for(BigDecimal earnings: earningsList){
            totalEarnings = totalEarnings.add(earnings);
        }
        return totalEarnings.setScale(2, RoundingMode.HALF_UP);
    }

}
