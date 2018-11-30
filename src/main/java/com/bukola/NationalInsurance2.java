package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for Class 2 National Insurance.
 * All results are rounded up with BigDecimal RoundingMode.HALF_UP.
 */
public class NationalInsurance2 implements Taxable{
    private BigDecimal ni2Threshold;
    private BigDecimal weeklyRate;
    private BigDecimal totalNI2;
    private List<BigDecimal> ni2List;


    /**
     * Class constructor.
     * @param ni2Threshold this is the specified annual threshold for
     * self-employed Class 2 National Insurance for the year concerned.
     * @param weeklyRate this is the weekly rate due for the year concerned.
     */
    public NationalInsurance2(BigDecimal ni2Threshold, BigDecimal weeklyRate) {
        if(ni2Threshold.compareTo(BigDecimal.ZERO) <= 0 |
                weeklyRate.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "NI2 threshold or weekly rate cannot be zero or a negative number.");
        this.ni2Threshold = ni2Threshold;
        this.weeklyRate = weeklyRate;
    }

    /**
     * returns the annual threshold entered in the constructor.
     * @return type BigDecimal
     */
    public BigDecimal getNi2Threshold(){
        return ni2Threshold;
    }


    /**
     * returns the weekly rate entered during class initialization.
     * @return weeklyRate
     */
    public BigDecimal getWeeklyRate() {
        return weeklyRate.setScale(2, RoundingMode.HALF_UP);
    }


    /**
     * Calculates class 2 National Insurance due to date for each self-employment work week.
     * @param profit list of earnings entered to date.
     * @see Profits
     */
    @Override
    public void calculate(List<BigDecimal> profit) {
        ni2List = new ArrayList<>();
        BigDecimal totalPay = new BigDecimal(0);
        Iterator<BigDecimal> iterator = profit.iterator();
        while(iterator.hasNext()){
            totalPay = totalPay.add(iterator.next());
        }

        if(totalPay.compareTo(ni2Threshold) >= 0) {
            for (int i = 0; i < profit.size(); i++) {
                ni2List.add(getWeeklyRate());
            }
        }
        else{
            for (int i = 0; i < profit.size(); i++) {
                ni2List.add(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP));
            }

        }
    }

    /**
     * returns a list view of class 2 National Insurance due to date.
     * @return list of type BigDecimal
     */
    @Override
    public List<BigDecimal> getList(){
        return ni2List;
    }

    /**
     * returns a sum total of class 2 National Insurance due to date.
     * This can be used to compute the total annual NI2 after 52 weeks,
     * using the tax calendar.
     * @return type BigDecimal
     */
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
