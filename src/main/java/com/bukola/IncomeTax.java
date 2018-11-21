package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IncomeTax implements Taxable {
    BigDecimal personalTaxAllowance;
    List<BigDecimal> incomeTax;
    BigDecimal totalIncomeTax;

    public IncomeTax(){
        this(new BigDecimal(0));
    }

    public IncomeTax(BigDecimal personalTaxAllowance){
        this.personalTaxAllowance = personalTaxAllowance;
    }


    public BigDecimal getPersonalTaxAllowance() {
        return personalTaxAllowance.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPersonalTaxAllowance(BigDecimal personalTaxAllowance) {
        this.personalTaxAllowance = personalTaxAllowance;
    }

    @Override
    public List<BigDecimal> calculate(List<BigDecimal> weeklyIncomeList) {
        incomeTax = new ArrayList<>();
        //income.setIncome(pay);
        //weeklyIncomeList = income.getWeeklyPayList();
        for(BigDecimal wage: weeklyIncomeList){
            BigDecimal weeklyTax = wage
                    .multiply(new BigDecimal(52))
                    .subtract(personalTaxAllowance)
                    .multiply(new BigDecimal(0.2))
                    .divide(new BigDecimal(52));
            BigDecimal roundUp = weeklyTax.setScale(2, RoundingMode.HALF_UP);
            incomeTax.add(roundUp);
        }

        return incomeTax;
    }

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
