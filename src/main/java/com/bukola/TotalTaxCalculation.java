package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * Class for computing weekly total tax.
 * (Class 2 & 4 National Insurance and 20% income tax)
 */
public class TotalTaxCalculation implements Taxable {
    private IncomeTax incomeTax;
    private NationalInsurance4 ni4;
    private NationalInsurance2 ni2;
    private List<BigDecimal> totalWeeklyTaxList;
    private BigDecimal totalTaxPayable;

    /**
     * Class constructor.
     * @param personalAllowance this is the personal allowance entered for IncomeTax calculations
     * @see IncomeTax#getPersonalTaxAllowance()
     * @param ni4LowThreshold this is the minimum value for class 4 NI deductions
     * @see NationalInsurance4#getLowThreshold()
     * @param ni4HighThreshold this is the maximum value entered for class 4 NI deductions
     * @see NationalInsurance4#getHighThreshold()
     * @param ni2Threshold this is the minimum value before class 2 NI becomes payable
     * @see NationalInsurance2#getNi2Threshold()
     * @param ni2Rate this is the annual weekly rate for National Insurance2
     * @see NationalInsurance2#getWeeklyRate()
     */
    public TotalTaxCalculation(BigDecimal personalAllowance, BigDecimal ni4LowThreshold,
                               BigDecimal ni4HighThreshold, BigDecimal ni2Threshold,
                               BigDecimal ni2Rate){
        if(personalAllowance.compareTo(ZERO) <= 0
                | ni4LowThreshold.compareTo(ZERO) <= 0
                | ni4HighThreshold.compareTo(ZERO) <= 0
                | ni2Threshold.compareTo(ZERO) <= 0
                | ni2Rate.compareTo(ZERO) <= 0)
            throw new IllegalArgumentException(
                    "Values cannot be zero or negative. " +
                            "Please refer to www.gov.uk for self-employed tax rates.");

        incomeTax = new IncomeTax(personalAllowance);
        ni4 = new NationalInsurance4(ni4LowThreshold, ni4HighThreshold);
        ni2 = new NationalInsurance2(ni2Threshold, ni2Rate);
        totalWeeklyTaxList = new ArrayList<>();
        totalTaxPayable = new BigDecimal(0);
    }

    /**
     * calculates a weekly sum total of all tax items-
     * NI2, NI4, Income Tax.
     * @param profit this is taxable income after expenses.
     */
    @Override
    public void calculate(List<BigDecimal> profit) {
        incomeTax.calculate(profit);
        ni4.calculate(profit);
        ni2.calculate(profit);

        //calculate income tax for each pay entry
        List<BigDecimal> incomeTaxPayable = incomeTax.getList();
        //calculate ni4 for each
        List<BigDecimal> ni4Payable = ni4.getList();
        //add ni2 till date
        List<BigDecimal> ni2Payable = ni2.getList();

        //add up calculations for each week
        for (int i = 0; i < profit.size(); i++) {
            BigDecimal total = incomeTaxPayable.get(i).add(ni4Payable.get(i).add(ni2Payable.get(i)));
            totalWeeklyTaxList.add(total);
        }
    }

    /**
     * Returns a list view of a weekly running total of payabletax.
     * @return list of type BigDecimal
     */
    @Override
    public List<BigDecimal> getList(){
        return totalWeeklyTaxList;
    }

    /**
     * Returns a running total of annual tax payable till date.
     * This can be used to compute the total annual tax after 52 weeks,
     * using the tax calendar.
     * @return type BigDecimal
     */
    @Override
    public BigDecimal getTotalToDate() {
        Iterator<BigDecimal> totalIterator = totalWeeklyTaxList.iterator();
        while(totalIterator.hasNext())
            totalTaxPayable = totalTaxPayable.add(totalIterator.next());
        return totalTaxPayable.setScale(2, RoundingMode.HALF_UP);
    }

}
