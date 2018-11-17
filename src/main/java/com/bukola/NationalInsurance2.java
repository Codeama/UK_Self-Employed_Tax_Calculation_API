package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NationalInsurance2 implements Taxable{
    BigDecimal ni2Threshold;
    BigDecimal weeklyRate;
    BigDecimal totalNI2;
    List<BigDecimal> ni2List;


    public void setNI2Threshold(BigDecimal ni2Threshold){
        this.ni2Threshold = ni2Threshold;
    }

    public BigDecimal getNi2Threshold(){
        return ni2Threshold;
    }

    public void setWeeklyRate(BigDecimal weeklyRate) {
        this.weeklyRate = weeklyRate;
//        BigDecimal roundUp = weeklyRate.setScale(2, RoundingMode.HALF_UP);
//        ni2List.add(roundUp);
    }

    public BigDecimal getWeeklyRate() {
        return weeklyRate.setScale(2, RoundingMode.HALF_UP);
    }

    public List<BigDecimal> getWeeklyRateList(int ni4ListSize){
        for(int i = 0; i < ni4ListSize; i++){
            ni2List.add(getWeeklyRate());
        }
        return ni2List;
    }

    public BigDecimal getTotalNI2(){
        totalNI2 = new BigDecimal(0);
        Iterator<BigDecimal> ni2Iterator = ni2List.iterator();
        while(ni2Iterator.hasNext()){
            totalNI2 = totalNI2.add(ni2Iterator.next());
        }

        return totalNI2.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List<BigDecimal> calculate(List<BigDecimal> incomeList) {
        ni2List = new ArrayList<>();
        BigDecimal totalPay = new BigDecimal(0);
        Iterator<BigDecimal> iterator = incomeList.iterator();
        while(iterator.hasNext()){
            totalPay = totalPay.add(iterator.next());
            totalPay.setScale(2, RoundingMode.HALF_UP);
        }

        if(totalPay.compareTo(ni2Threshold) >= 0) {
            for (int i = 0; i < incomeList.size(); i++) {
                ni2List.add(getWeeklyRate());
            }
        }
        else{
            for (int i = 0; i < incomeList.size(); i++) {
                ni2List.add(new BigDecimal(0));
            }

        }
        return ni2List;
    }

    @Override
    public BigDecimal getTotalToDate() {
        totalNI2 = new BigDecimal(0);
        Iterator<BigDecimal> ni2Iterator = ni2List.iterator();
        while(ni2Iterator.hasNext()){
            totalNI2 = totalNI2.add(ni2Iterator.next());
        }

        return totalNI2.setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args){
        NationalInsurance2 ni2 = new NationalInsurance2();
        Income income = new Income();
        income.setIncome(new BigDecimal(100));
        ni2.setWeeklyRate(new BigDecimal(2.85));
        ni2.setNI2Threshold(new BigDecimal(6205));
        System.out.printf("Week1 Ni2: %s%n", ni2.calculate(income.getWeeklyPay()));
        //ni2.setWeeklyRate(new BigDecimal(2.85));
        income.setIncome(new BigDecimal(7000));
        System.out.printf("Week1 & 2 Ni2: %s%n", ni2.calculate(income.getWeeklyPay()));
        System.out.printf("Total Ni2 to date: %s%n", ni2.getTotalNI2());
    }
}
