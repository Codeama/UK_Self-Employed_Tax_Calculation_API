package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NationalInsurance4 implements Taxable {
    Income income = new Income();
    BigDecimal NI4threshold;
    List<BigDecimal> classNI4List;
    BigDecimal totalNI4ToDate;


    public BigDecimal getNI4threshold() {
        return NI4threshold;
    }

    public void setNI4threshold(BigDecimal NI4threshold) {
        this.NI4threshold = NI4threshold;
    }

    @Override
    public List<BigDecimal> calculate(List<BigDecimal> weeklyPayList) {
        classNI4List = new ArrayList<>();
        //set income and tax property(NI4 threshold)
        //income.setIncome(pay);
        //get a list of income entered
        //weeklyPayList = income.getWeeklyPay();
        //walk through weekly income and calculate NI4 for each
        for(BigDecimal wage: weeklyPayList){
            BigDecimal weeklyClass4NI = wage.multiply(new BigDecimal(52))
                    .subtract(NI4threshold)
                    .divide(new BigDecimal(52))
                    .multiply(new BigDecimal(0.09));
            BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
            classNI4List.add(roundUp);
        }
        return classNI4List;
    }

    @Override
    public BigDecimal getTotalToDate(){
        totalNI4ToDate = new BigDecimal(0);
        Iterator<BigDecimal> ni4Iterator = classNI4List.iterator();
        while(ni4Iterator.hasNext()){
            totalNI4ToDate = totalNI4ToDate.add(ni4Iterator.next());
        }
        return  totalNI4ToDate.setScale(2, RoundingMode.HALF_UP);
    }

    public int getNI4ListSize(){
        return classNI4List.size();
    }

/*
    public static void main(String[] args){
        NationalInsurance4 ni4 = new NationalInsurance4(new BigDecimal(8424));
        List<BigDecimal> ni = ni4.calculate(new BigDecimal(100));
        System.out.println("NI4 for £100 is: "+ ni);
        List<BigDecimal> niWeek2 = ni4.calculate(new BigDecimal(350));
        System.out.println("NI4 for £100 and £350 is: "+ niWeek2);
        System.out.printf("Total Ni4 to date: %s%n", ni4.getTotalToDate());
    }
*/
}
