package com.bukola;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TotalTaxCalculation {
    IncomeTax incomeTax;
    NationalInsurance4 ni4;
    NationalInsurance2 ni2;
    List<BigDecimal> totalWeeklyTaxList;
    List<BigDecimal> totalTaxPayable;

    public TotalTaxCalculation(){
        this(new BigDecimal(0), new BigDecimal(0),
                new BigDecimal(0), new BigDecimal(0),
                new BigDecimal(0));
    }

    public TotalTaxCalculation(BigDecimal personalAllowance, BigDecimal ni4LowThreshold,
                               BigDecimal ni4HighThreshold, BigDecimal ni2Threshold,
                               BigDecimal ni2Rate){
        incomeTax = new IncomeTax(personalAllowance);
        ni4 = new NationalInsurance4(ni4LowThreshold, ni4HighThreshold);
        ni2 = new NationalInsurance2(ni2Threshold, ni2Rate);
            }

    public void setTaxItems(BigDecimal personalAllowance,
                            BigDecimal ni4LowThreshold,
                            BigDecimal ni4HighThreshold, BigDecimal ni2Threshold,
                            BigDecimal ni2Rate){
        incomeTax = new IncomeTax(personalAllowance);
        ni4 = new NationalInsurance4(ni4LowThreshold, ni4HighThreshold);
        ni2 = new NationalInsurance2(ni2Threshold, ni2Rate);
    }

    public List<BigDecimal> getTotalWeeklyTaxList(List<BigDecimal> incomeList){
        //calculate income tax for each pay entry
        List<BigDecimal> incomeTaxPayable = incomeTax.calculate(incomeList);
        //calculate ni4 for each
        List<BigDecimal> ni4Payable = ni4.calculate(incomeList);
        //add ni2 till date
        List<BigDecimal> ni2Payable = ni2.calculate(incomeList);

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
}
