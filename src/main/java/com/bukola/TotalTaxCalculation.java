package com.bukola;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TotalTaxCalculation {
    //Income income;
    IncomeTax incomeTax;
    NationalInsurance4 ni4;
    NationalInsurance2 ni2;
    List<BigDecimal> totalWeeklyTaxList;
    List<BigDecimal> totalTaxPayable;

    public TotalTaxCalculation(){
        //income = new Income();
        incomeTax = new IncomeTax();
        ni4 = new NationalInsurance4();
        ni2 = new NationalInsurance2();
    }

    public void setTaxItems(BigDecimal personalAllowance,
                            BigDecimal ni4Threshold, BigDecimal ni2){
        //this.income.setIncome(income);
        incomeTax.setPersonalTaxAllowance(personalAllowance);
        ni4.setNI4threshold(ni4Threshold);
        this.ni2.setWeeklyRate(ni2);
    }

    public List<BigDecimal> getTotalWeeklyTaxList(List<BigDecimal> incomeList){
        //calculate income tax for each pay entry
        List<BigDecimal> incomeTaxPayable = incomeTax.calculate(incomeList);
        //calculate ni4 for each
        List<BigDecimal> ni4Payable = ni4.calculate(incomeList);
        //add ni2 till date
        List<BigDecimal> ni2Payable = ni2.getWeeklyRateList(ni4.getNI4ListSize());

        //add up calculations for each week
        totalWeeklyTaxList = new ArrayList<>();
        BigDecimal total;// = new BigDecimal(0);

        for(int i = 0; i < incomeList.size(); i++){
            total = new BigDecimal(0);
            total = incomeTaxPayable.get(i).add(ni4Payable.get(i).add(ni2Payable.get(i)));
            totalWeeklyTaxList.add(total);
        }
                return totalWeeklyTaxList;
    }

    public static void main(String[] args){
        TotalTaxCalculation tax = new TotalTaxCalculation();
        Income income = new Income();
        income.setIncome(new BigDecimal(100));
        tax.setTaxItems(new BigDecimal(11500), new BigDecimal(8424),
                new BigDecimal(2.85));
        System.out.println("Income list: "+ income.getWeeklyPay());
        System.out.printf("Total tax each week: %s%n", tax.getTotalWeeklyTaxList(income.getWeeklyPay()));
        income.setIncome(new BigDecimal(350));
        System.out.println("Income list: "+ income.getWeeklyPay());
        System.out.printf("Total tax each week: %s%n", tax.getTotalWeeklyTaxList(income.getWeeklyPay()));
        Object[] listArray = tax.getTotalWeeklyTaxList(income.getWeeklyPay()).toArray();
        for(Object list : listArray)
            System.out.printf("%s%n", list);


    }
}
