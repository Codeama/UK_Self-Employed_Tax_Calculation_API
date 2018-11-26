package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IncomeTax implements Taxable {
    private BigDecimal personalTaxAllowance;
    private List<BigDecimal> incomeTax;
    private BigDecimal totalIncomeTax;


    public IncomeTax(BigDecimal personalTaxAllowance){
        if(personalTaxAllowance.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    "Personal allowance cannot be zero or negative. " +
                            "Please refer to www.gov.uk for self-employed tax rates.");
        this.personalTaxAllowance = personalTaxAllowance;
    }


    public BigDecimal getPersonalTaxAllowance() {
        return personalTaxAllowance.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPersonalTaxAllowance(BigDecimal personalTaxAllowance) {
        this.personalTaxAllowance = personalTaxAllowance;
    }

    @Override
    public void calculate(List<BigDecimal> weeklyIncomeList) {
        incomeTax = new ArrayList<>();
        //income.setPay(pay);
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

        //return incomeTax;
    }

    @Override
    public List<BigDecimal> getList(){
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
    public static void main(String[] args){
        IncomeTax in = new IncomeTax(new BigDecimal(11500));
        List<BigDecimal> income = new ArrayList<>();
        income.add(new BigDecimal(100));
        in.calculate(income);
        System.out.printf("Income tax for £100: %s%n", in.getList());
    }
*/

}
