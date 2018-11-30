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
     * Calculates tax/national insurance due.
     * @param profit this is taxable income after expenses.
     * @see Profits
     */
    void calculate(List<BigDecimal> profit);

    /**
     * list view of tax/national insurance deducted to date.
     * @return list of type BigDecimal
     */
    List<BigDecimal> getList();

    /**
     * Sum total of tax/national insurance deducted to date
     * @return type BigDecimal
     */
    BigDecimal getTotalToDate();
}
