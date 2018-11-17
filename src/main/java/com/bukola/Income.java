package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Income {
    private BigDecimal pay;
    private BigDecimal totalPay;
    private List<BigDecimal> payList;

    public Income(){

        payList = new ArrayList<>();

    }

    public void setIncome(BigDecimal pay){
        BigDecimal roundedUp = pay.setScale(2, RoundingMode.HALF_UP);
        payList.add(roundedUp);
    }

    public List<BigDecimal> getWeeklyPay(){
        return payList;
    }


    public BigDecimal getTotalPayToDate(){
        totalPay = new BigDecimal(0);
        Iterator<BigDecimal> iterator = payList.iterator();
        while(iterator.hasNext()){
            totalPay = totalPay.add(iterator.next());
        }
        return totalPay.setScale(2, RoundingMode.HALF_UP);
    }


/*
    public static void main(String[] args){
        Income moneyIn =new Income(); //initialize
        moneyIn.setIncome(new BigDecimal(20)); //
        System.out.printf("income1 = %s%n", moneyIn.getWeeklyPay());
        System.out.printf("Total pay: %s%n", moneyIn.getTotalPayToDate());
        moneyIn.setIncome(new BigDecimal(150));
        System.out.printf("income2 = %s%n", moneyIn.getWeeklyPay());
        System.out.println(moneyIn.getTotalPayToDate());

    }
*/
}
