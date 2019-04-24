package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a class for Income Tax calculations.
 * All results are rounded up using BigDecimal RoundingMode.HALF_UP.
 */
public class IncomeTax implements Taxable {
    private BigDecimal personalTaxAllowance;
    private List<BigDecimal> incomeTax;
    private BigDecimal totalIncomeTax;

    /**
     * Class constructor.
     * @param personalTaxAllowance this is the personal allowance for every UK tax payer
     *                             before tax deductions. See www.gov.uk for current rates.
     *                             This value cannot be zero - throws an Exception.
     */
    public IncomeTax(BigDecimal personalTaxAllowance){
        if(personalTaxAllowance.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "Personal allowance cannot be zero or negative. " +
                            "Please refer to www.gov.uk for self-employed tax rates.");
        this.personalTaxAllowance = personalTaxAllowance;
    }

    /**
     * Getter method that returns tax allowance value that has been set during class initialization for the year concerned.
     * @return tax allowance value
     */
    public BigDecimal getPersonalTaxAllowance() {
        return personalTaxAllowance.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates income tax due for each week's earnings,
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
     * Returns a list of all taxes due for each week's earnings.
     * @return list of type BigDecimal
     */
    @Override
    public List<BigDecimal> getList(){
        return incomeTax;
    }

    /**
     * Returns a running total of tax due to date.
     * This can be used to compute the total annual tax over a 52 week period,
     * using the tax calendar.
     * @return a running total of tax due to date
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
