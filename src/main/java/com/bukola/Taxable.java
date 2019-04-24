package com.bukola;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for all tax properties.
 * Implemented by the following classes:
 * @see IncomeTax
 * @see NationalInsurance2
 * @see NationalInsurance4
 * @see TotalTaxCalculation
 */
public interface Taxable {

    /**
     * Calculates tax and national insurance due.
     * @param profit this is taxable income after expenses.
     * @see Profits
     */
    void calculate(List<BigDecimal> profit);

    /**
     * list of tax/national insurance deducted to date.
     * @return list of tax and national insurance
     */
    List<BigDecimal> getList();

    /**
     * Sum total of tax/national insurance deducted to date
     * @return running total of tax and national insurance
     */
    BigDecimal getTotalToDate();
}
