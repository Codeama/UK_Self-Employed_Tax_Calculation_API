package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NationalInsurance2 {
    BigDecimal weeklyRate;
    BigDecimal totalNI2;
    List<BigDecimal> NI2list;

    public NationalInsurance2(){
    //    this.weeklyRate = weeklyRate;
        NI2list = new ArrayList<>();
    }

    public void setWeeklyRate(BigDecimal weeklyRate) {
        this.weeklyRate = weeklyRate;
//        BigDecimal roundUp = weeklyRate.setScale(2, RoundingMode.HALF_UP);
//        NI2list.add(roundUp);
    }

    public BigDecimal getWeeklyRate() {
        return weeklyRate.setScale(2, RoundingMode.HALF_UP);
    }

    public List<BigDecimal> getWeeklyRateList(int ni4ListSize){
        for(int i = 0; i < ni4ListSize; i++){
            NI2list.add(getWeeklyRate());
        }
        return NI2list;
    }

    public BigDecimal getTotalNI2(){
        totalNI2 = new BigDecimal(0);
        Iterator<BigDecimal> ni2Iterator = NI2list.iterator();
        while(ni2Iterator.hasNext()){
            totalNI2 = totalNI2.add(ni2Iterator.next());
        }

        return totalNI2.setScale(2, RoundingMode.HALF_UP);
    }

/*
    public static void main(String[] args){
        NationalInsurance2 ni2 = new NationalInsurance2();
        ni2.setWeeklyRate(new BigDecimal(2.85));
        System.out.printf("Week1 Ni2: %s%n", ni2.getWeeklyRateList());
        ni2.setWeeklyRate(new BigDecimal(2.85));
        System.out.printf("Week1 & 2 Ni2: %s%n", ni2.getWeeklyRateList());
        System.out.printf("Total Ni2 to date: %s%n", ni2.getTotalNI2());
    }
*/
}
