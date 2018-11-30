package com.bukola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Bukola Jimoh
 * A class for entering payments recieved/wages on a weekly basis.
 * This is processed as a List.
 * All results are rounded up with BigDecimal RoundingMode.HALF_UP.
 * */
public class Wages {
    private BigDecimal totalPay;
    private List<BigDecimal> payList;

    /**
     * Class constructor.
     */
    public Wages(){
        payList = new ArrayList<>();
    }

    /**
     * method for entering each payment/wage.
     * @param pay payments to add of type BigDecimal
     */
    public void addPay(BigDecimal pay){
        BigDecimal roundedUp = pay.setScale(2, RoundingMode.HALF_UP);
        payList.add(roundedUp);
    }

    /**
     * returns a list view of weekly wages/payments entered to date.
     * @return a list of type BigDecimal
     */
    public List<BigDecimal> getWeeklyPayList(){
        return payList;
    }

    /**
     * sums up all wages entered to date.
     * @return running total of wages entered to date
     */
    public BigDecimal getTotalPayToDate(){
        totalPay = new BigDecimal(0);
        Iterator<BigDecimal> iterator = payList.iterator();
        while(iterator.hasNext()){
            totalPay = totalPay.add(iterator.next());
        }
        return totalPay.setScale(2, RoundingMode.HALF_UP);
    }


}
