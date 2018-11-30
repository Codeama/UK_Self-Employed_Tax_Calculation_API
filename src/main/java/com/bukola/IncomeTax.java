package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for Income Tax calculations.
 * All results are rounded up with BigDecimal RoundingMode.HALF_UP.
 */
public class IncomeTax implements Taxable {
    private BigDecimal personalTaxAllowance;
    private List<BigDecimal> incomeTax;
    private BigDecimal totalIncomeTax;

    /**
     * Class constructor.
     * @param personalTaxAllowance this is the personal allowance for every tax payer
     *                             before tax deductions. See www.gov.uk for current rate.
     *                             This value cannot be zero.
     */
    public IncomeTax(BigDecimal personalTaxAllowance){
        if(personalTaxAllowance.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "Personal allowance cannot be zero or negative. " +
                            "Please refer to www.gov.uk for self-employed tax rates.");
        this.personalTaxAllowance = personalTaxAllowance;
    }

    /**
     * Returns tax allowance vale for the year entered during class initialization.
     * @return
     */
    public BigDecimal getPersonalTaxAllowance() {
        return personalTaxAllowance.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates income tax due on each weekly earnings,
     * normally 20% after personal allowance
     * @param profit this is taxable income after expenses.
     * @see Profits
     */
    @Override
    public void calculate(List<BigDecimal> profit) {
        incomeTax = new ArrayList<>();
        for(BigDecimal wage: profit){
            BigDecimal weeklyTax = wage
                    .multiply(new BigDecimal(52))
                    .subtract(personalTaxAllowance)
                    .multiply(new BigDecimal(0.2))
                    .divide(new BigDecimal(52));
            BigDecimal roundUp = weeklyTax.setScale(2, RoundingMode.HALF_UP);
            incomeTax.add(roundUp);
        }
    }

    /**
     * Returns a list view of all tax due for each week's earnings.
     * @return list of type BigDecimal
     */
    @Override
    public List<BigDecimal> getList(){
        return incomeTax;
    }

    /**
     * Returns a running total of tax due to date.
     * @return type BigDecimal.
     */
    @Override
    public BigDecimal getTotalToDate() {
        totalIncomeTax = new BigDecimal(0);
        Iterator<BigDecimal> taxIterator = incomeTax.iterator();
        while(taxIterator.hasNext()){
            totalIncomeTax = totalIncomeTax.add(taxIterator.next());
        }
        return totalIncomeTax.setScale(2, RoundingMode.HALF_UP);
    }

}
