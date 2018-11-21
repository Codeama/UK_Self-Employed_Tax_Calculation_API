package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Wages {
    private BigDecimal pay;
    private BigDecimal totalPay;
    private List<BigDecimal> payList;

    public Wages(){

        payList = new ArrayList<>();

    }

    public void setIncome(BigDecimal pay){
        BigDecimal roundedUp = pay.setScale(2, RoundingMode.HALF_UP);
        payList.add(roundedUp);
    }

    public List<BigDecimal> getWeeklyPayList(){
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


}
