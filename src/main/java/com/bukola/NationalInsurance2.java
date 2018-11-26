package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NationalInsurance2 implements Taxable{
    private BigDecimal ni2Threshold;
    private BigDecimal weeklyRate;
    private BigDecimal totalNI2;
    private List<BigDecimal> ni2List;


    public NationalInsurance2(BigDecimal ni2Threshold, BigDecimal weeklyRate) {
        if(ni2Threshold.compareTo(BigDecimal.ZERO) <= 0 |
                weeklyRate.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "NI2 threshold or weekly rate cannot be zero or a negative number.");
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
    }

    public BigDecimal getWeeklyRate() {
        return weeklyRate.setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public void calculate(List<BigDecimal> incomeList) {
        ni2List = new ArrayList<>();
        BigDecimal totalPay = new BigDecimal(0);
        Iterator<BigDecimal> iterator = incomeList.iterator();
        while(iterator.hasNext()){
            totalPay = totalPay.add(iterator.next());
        }

        if(totalPay.compareTo(ni2Threshold) >= 0) {
            for (int i = 0; i < incomeList.size(); i++) {
                ni2List.add(getWeeklyRate());
            }
        }
        else{
            for (int i = 0; i < incomeList.size(); i++) {
                ni2List.add(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP));
            }

        }
    }

    @Override
    public List<BigDecimal> getList(){
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
