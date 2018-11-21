package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NationalInsurance4 implements Taxable {
    BigDecimal lowThreshold;
    BigDecimal highThreshold;
    List<BigDecimal> classNI4List;
    BigDecimal totalNI4ToDate;

    public NationalInsurance4(){
        this(new BigDecimal(0), new BigDecimal(0));
    }

    public NationalInsurance4(BigDecimal lowThreshold, BigDecimal highThreshold){
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }


    public BigDecimal getLowThreshold() {
        return lowThreshold;
    }

    public void setLowThreshold(BigDecimal lowThreshold) {
        this.lowThreshold = lowThreshold;
    }

    public BigDecimal getHighThreshold() {
        return highThreshold;
    }

    public void setHighThreshold(BigDecimal highThreshold) {
        this.highThreshold = highThreshold;
    }

    @Override
    public List<BigDecimal> calculate(List<BigDecimal> weeklyPayList) {
        classNI4List = new ArrayList<>();
        BigDecimal totaIncome = new BigDecimal(0);
        for(BigDecimal wage: weeklyPayList){
            //add up income to check threshold
            totaIncome = totaIncome.add(wage);
        }

        //walk through weekly income and calculate NI4 for each
//        for(BigDecimal wage: weeklyPayList){
            BigDecimal weeklyClass4NI = new BigDecimal(0);
            if(totaIncome.compareTo(lowThreshold)>= 0
                    & totaIncome.compareTo(highThreshold) <= 0){
                return applyNi4LowThreshold(weeklyPayList);
            }

            if(totaIncome.compareTo(highThreshold) > 0){
                return applyNi4HighThreshold(weeklyPayList);
            }

            else {
                for (BigDecimal wage : weeklyPayList) {
                    weeklyClass4NI = new BigDecimal(0);
                    BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
                    classNI4List.add(roundUp);
                }
            }
        return classNI4List;
    }

    public List<BigDecimal> applyNi4HighThreshold(List<BigDecimal> weeklyPayList){
        classNI4List = new ArrayList<>();
        for(BigDecimal wage : weeklyPayList){
            BigDecimal weeklyClass4NI = wage.multiply(new BigDecimal(0.09));
            BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
            classNI4List.add(roundUp);
        }
        return classNI4List;
    }

    public List<BigDecimal> applyNi4LowThreshold(List<BigDecimal> weeklyPayList){
        classNI4List = new ArrayList<>();
        for(BigDecimal wage : weeklyPayList) {
            BigDecimal weeklyClass4NI = wage.multiply(new BigDecimal(0.02));
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

}
