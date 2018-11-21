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

    public NationalInsurance2(){
        this(new BigDecimal(0), new BigDecimal(0));
    }

    public NationalInsurance2(BigDecimal ni2Threshold, BigDecimal weeklyRate) {
        this.ni2Threshold = ni2Threshold;
        this.weeklyRate = weeklyRate;
    }

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

}
