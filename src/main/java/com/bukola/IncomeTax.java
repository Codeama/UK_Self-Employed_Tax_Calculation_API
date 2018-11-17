package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IncomeTax implements Taxable {
    BigDecimal personalTaxAllowance;
    List<BigDecimal> incomeTax;
    Income income = new Income();
    BigDecimal totalIncomeTax;


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
        //weeklyIncomeList = income.getWeeklyPay();
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

/*
    public static void main (String[] args){
        Taxable incomeTax = new IncomeTax(new BigDecimal(11500));
        List<BigDecimal> tax = incomeTax.calculate(new BigDecimal(100));
        System.out.printf("Income tax for £100: %s%n", tax);
        List<BigDecimal> tax2 = incomeTax.calculate(new BigDecimal(350));
        System.out.printf("Income tax for £100 and £350: %s%n", tax2);

    }
*/
}
