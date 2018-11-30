package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for class 4 National Insurance.
 * All results are rounded up with BigDecimal RoundingMode.HALF_UP.
 */
public class NationalInsurance4 implements Taxable {
    private BigDecimal lowThreshold;
    private BigDecimal highThreshold;
    private List<BigDecimal> classNI4List;
    private BigDecimal totalNI4ToDate;
    private final BigDecimal TWO_PERCENT = new BigDecimal(0.02);
    private final BigDecimal NINE_PERCENT = new BigDecimal(0.09);


    /**
     * Class constructor.
     * @param lowThreshold this is the minimum profit set before
     *                    self-employed people start to pay 9% of earnings
     * @param highThreshold this is the maximum profit limit set before
     *                      they start to pay 9% of earnings, or 2% if earnings
     *                      are higher than this value.
     */
    public NationalInsurance4(BigDecimal lowThreshold, BigDecimal highThreshold){
        if(lowThreshold.compareTo(BigDecimal.ZERO) <= 0
                | highThreshold.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Thresholds cannot be zero or a negative value.");

        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }

    /**
     * Returns the lower limit threshold set at initialization.
     * @return type BigDecimal.
     */
    public BigDecimal getLowThreshold() {
        return lowThreshold;
    }

    /**
     * Returns higher/maximum threshold set at initialization.
     * @return type BigDecimal.
     */
    public BigDecimal getHighThreshold() {
        return highThreshold;
    }

    /**
     * Calculates class 4 National Insurance due on each weekly profit
     * using the thresholds entered during class initialization.
     * Nine percent is applied to total earnings that are equal to the lower threshold
     * and less than or equal to the higher threshold.
     * Two percent is applied to total earning that are higher than the high threshold.
     * Computations are applied to weekly earnings after getting a total of earnings till date.
     * So as soon as total earnings meet one of the thresholds, earnings become deductible.
     * But while earnings are below the minimum, no National Insurance is deducted.
     * @param profit this is taxable income after expenses.
     * @see Profits
     */
    @Override
    public void calculate(List<BigDecimal> profit) {
        classNI4List = new ArrayList<>();
        BigDecimal totalIncome = new BigDecimal(0);
        //add up income till date
        for(BigDecimal wage : profit)
            totalIncome = totalIncome.add(wage);
        BigDecimal weeklyClass4NI = new BigDecimal(0);

        if(minimumThreshold(totalIncome)){
            applyNI4(profit, NINE_PERCENT);
        }

        if(maximumThreshold(totalIncome)){
            applyNI4(profit, TWO_PERCENT);
        }

        else if(totalIncome.compareTo(lowThreshold) < 0){
             for(int i = 0; i< profit.size(); i++){
                BigDecimal roundUp = weeklyClass4NI.setScale(2, RoundingMode.HALF_UP);
                classNI4List.add(roundUp);
           }
        }
    }

    /**
     * This applies National Insurance class 4 regardless of whether
     * or not the earnings reach the thresholds. It can be used in situations where the
     * total annual earnings is already known and the appropriate NI4 percentage can be entered.
     * @param weeklyPayList this is the list of earnings entered to date.
     * @see Profits
     * @param percentage this is the appropriate NI4 percentage to be applied. Visit www.gov.uk
     *                   for actual percentages. But this is usually 9% of total earnings if
     *                   equal to or above the minimum threshold, and 2% of total earnings if
     *                   above the maximum threshold.
     */
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

    /**
     * Returns a list view of all NI4 deductions due for each week's earnings.
     * @return list of type BigDecimal.
     */
    @Override
    public List<BigDecimal> getList(){
        return classNI4List;
    }

    /**
     * Returns a running total of all weekly Ni4 deductions to date.
     * This can be used to compute the total annual NI4 after 52 weeks,
     * using the tax calendar.
     * @return type BigDecimal
     */
    @Override
    public BigDecimal getTotalToDate(){
        totalNI4ToDate = new BigDecimal(0);
        Iterator<BigDecimal> ni4Iterator = classNI4List.iterator();
        while(ni4Iterator.hasNext()){
            totalNI4ToDate = totalNI4ToDate.add(ni4Iterator.next());
        }
        return  totalNI4ToDate.setScale(2, RoundingMode.HALF_UP);
    }

}
