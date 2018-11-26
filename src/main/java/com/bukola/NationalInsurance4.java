package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NationalInsurance4 implements Taxable {
    private BigDecimal lowThreshold;
    private BigDecimal highThreshold;
    private List<BigDecimal> classNI4List;
    private BigDecimal totalNI4ToDate;
    private final BigDecimal TWO_PERCENT = new BigDecimal(0.02);
    private final BigDecimal NINE_PERCENT = new BigDecimal(0.09);


    public NationalInsurance4(BigDecimal lowThreshold, BigDecimal highThreshold){
        if(lowThreshold.compareTo(BigDecimal.ZERO) <= 0
                | highThreshold.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Thresholds cannot be zero or a negative value.");

        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }


    public BigDecimal getLowThreshold() {
        return lowThreshold;
    }

    public void setLowThreshold(BigDecimal lowThreshold) {
        if(lowThreshold.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Thresholds cannot be zero or a negative value.");

        this.lowThreshold = lowThreshold;
    }

    public BigDecimal getHighThreshold() {
        return highThreshold;
    }

    public void setHighThreshold(BigDecimal highThreshold) {
        if(highThreshold.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Thresholds cannot be zero or a negative value.");

        this.highThreshold = highThreshold;
    }

    @Override
    public void calculate(List<BigDecimal> weeklyPayList) {
        classNI4List = new ArrayList<>();
        BigDecimal totalIncome = new BigDecimal(0);
        //add up income till date
        for(BigDecimal wage : weeklyPayList)
            totalIncome = totalIncome.add(wage);
        BigDecimal weeklyClass4NI = new BigDecimal(0);

        if(minimumThreshold(totalIncome)){
             //applyNi4LowThreshold(weeklyPayList);
            applyNI4(weeklyPayList, NINE_PERCENT);
        }

        if(maximumThreshold(totalIncome)){
            //applyNi4HighThreshold(weeklyPayList);
            applyNI4(weeklyPayList, TWO_PERCENT);
        }

        else if(totalIncome.compareTo(lowThreshold) < 0){
           //for (BigDecimal wage : weeklyPayList) {
               // weeklyClass4NI = new BigDecimal(0);
             for(int i=0; i<weeklyPayList.size(); i++){
                BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
                classNI4List.add(roundUp);
           }
        }
        //return classNI4List;
    }

    public void applyNI4(List<BigDecimal> weeklyPayList, BigDecimal percentage){
        classNI4List = new ArrayList<>();
        for(BigDecimal wage : weeklyPayList) {
            BigDecimal weeklyClass4NI = wage.multiply(percentage);
            BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
            classNI4List.add(roundUp);
        }
    }

    private boolean minimumThreshold(BigDecimal totalWages){
        return (totalWages.compareTo(lowThreshold) >= 0)
                & (totalWages.compareTo(highThreshold) <= 0);
    }

    private boolean maximumThreshold(BigDecimal totalWages){
        return totalWages.compareTo(highThreshold) > 0;
    }


 /*   public void applyNi4HighThreshold(List<BigDecimal> weeklyPayList){
        classNI4List = new ArrayList<>();
        for(BigDecimal wage : weeklyPayList){
            BigDecimal weeklyClass4NI = wage.multiply(new BigDecimal(0.02));
            BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
            classNI4List.add(roundUp);
        }
        //return classNI4List;
    }

    public void applyNi4LowThreshold(List<BigDecimal> weeklyPayList){
        classNI4List = new ArrayList<>();
        for(BigDecimal wage : weeklyPayList) {
            BigDecimal weeklyClass4NI = wage.multiply(new BigDecimal(0.09));
            BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
            classNI4List.add(roundUp);
        }
        //return classNI4List;
    }
*/
    @Override
    public List<BigDecimal> getList(){
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

/*
    public static void main(String[] args){
        NationalInsurance4 ni = new NationalInsurance4();
        ni.setLowThreshold(new BigDecimal(8424));
        ni.setHighThreshold(new BigDecimal(46350));
        Wages wages = new Wages();
        wages.setPay(new BigDecimal(100));
        wages.setPay(new BigDecimal(350));
        //wages.setPay(new BigDecimal(9450));
        //wages.setPay(new BigDecimal(400));
        System.out.printf("pay list: %s%n", wages.getWeeklyPayList());
        ni.calculate(wages.getWeeklyPayList());
        System.out.printf("Ni4 to date: %s%n", ni.getList());
    }
*/

/*
    public int getNI4ListSize(){
        return classNI4List.size();
    }
*/

}
