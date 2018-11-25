package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TotalTaxCalculation implements Taxable {
    private IncomeTax incomeTax;
    private NationalInsurance4 ni4;
    private NationalInsurance2 ni2;
    private List<BigDecimal> totalWeeklyTaxList;
    private BigDecimal totalTaxPayable;

    BigDecimal personalAllowance;
    BigDecimal ni4LowThreshold;
    BigDecimal ni4HighThreshold;
    BigDecimal ni2Threshold;
    BigDecimal ni2Rate;

    public TotalTaxCalculation(){
        this(new BigDecimal(0), new BigDecimal(0),
                new BigDecimal(0), new BigDecimal(0),
                new BigDecimal(0));
        totalWeeklyTaxList = new ArrayList<>();
        totalTaxPayable = new BigDecimal(0);
    }

    public TotalTaxCalculation(BigDecimal personalAllowance, BigDecimal ni4LowThreshold,
                               BigDecimal ni4HighThreshold, BigDecimal ni2Threshold,
                               BigDecimal ni2Rate){
        incomeTax = new IncomeTax(personalAllowance);
        ni4 = new NationalInsurance4(ni4LowThreshold, ni4HighThreshold);
        ni2 = new NationalInsurance2(ni2Threshold, ni2Rate);
    }

    public BigDecimal getPersonalAllowance() {
        return personalAllowance;
    }

    public void setPersonalAllowance(BigDecimal personalAllowance) {
        this.personalAllowance = personalAllowance;
    }

    public BigDecimal getNi4LowThreshold() {
        return ni4LowThreshold;
    }

    public void setNi4LowThreshold(BigDecimal ni4LowThreshold) {
        this.ni4LowThreshold = ni4LowThreshold;
    }

    public BigDecimal getNi4HighThreshold() {
        return ni4HighThreshold;
    }

    public void setNi4HighThreshold(BigDecimal ni4HighThreshold) {
        this.ni4HighThreshold = ni4HighThreshold;
    }

    public BigDecimal getNi2Threshold() {
        return ni2Threshold;
    }

    public void setNi2Threshold(BigDecimal ni2Threshold) {
        this.ni2Threshold = ni2Threshold;
    }

    public BigDecimal getNi2Rate() {
        return ni2Rate;
    }

    public void setNi2Rate(BigDecimal ni2Rate) {
        this.ni2Rate = ni2Rate;
    }

    public void setTaxItems(BigDecimal personalAllowance,
                            BigDecimal ni4LowThreshold,
                            BigDecimal ni4HighThreshold, BigDecimal ni2Threshold,
                            BigDecimal ni2Rate){
        this.personalAllowance = personalAllowance;
        this.ni4LowThreshold = ni4LowThreshold;
        this.ni4HighThreshold = ni4HighThreshold;
        this.ni2Threshold = ni2Threshold;
        this.ni2Rate = ni2Rate;
        incomeTax = new IncomeTax(this.personalAllowance);
        ni4 = new NationalInsurance4(this.ni4LowThreshold, this.ni4HighThreshold);
        ni2 = new NationalInsurance2(this.ni2Threshold, this.ni2Rate);
    }

    @Override
    public void calculate(List<BigDecimal> incomeList) {
        incomeTax.calculate(incomeList);
        ni4.calculate(incomeList);
        ni2.calculate(incomeList);

        //calculate income tax for each pay entry
        List<BigDecimal> incomeTaxPayable = incomeTax.getList();
        //calculate ni4 for each
        List<BigDecimal> ni4Payable = ni4.getList();
        //add ni2 till date
        List<BigDecimal> ni2Payable = ni2.getList();

        //add up calculations for each week
        //BigDecimal total = new BigDecimal(0);

        for (int i = 0; i < incomeList.size(); i++) {
            //total = new BigDecimal(0);
            BigDecimal total = incomeTaxPayable.get(i).add(ni4Payable.get(i).add(ni2Payable.get(i)));
            totalWeeklyTaxList.add(total);
        }
    }

    @Override
    public List<BigDecimal> getList(){
        return totalWeeklyTaxList;
    }

    //@Override
    public BigDecimal getTotalToDate() {
        Iterator<BigDecimal> totalIterator = totalWeeklyTaxList.iterator();
        while(totalIterator.hasNext())
            totalTaxPayable = totalTaxPayable.add(totalIterator.next());
        return totalTaxPayable.setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args){
        TotalTaxCalculation tax = new TotalTaxCalculation();
        tax.setTaxItems(new BigDecimal(11500), new BigDecimal(6424),
                new BigDecimal(46350), new BigDecimal(6205), new BigDecimal(2.95));
        List<BigDecimal> income = new ArrayList<>();
        income.add(new BigDecimal(100));
        income.add(new BigDecimal(350));
        income.add(new BigDecimal(6000));
        tax.calculate(income);
        System.out.printf("Totals: %s%n", tax.getList());
    }
}
