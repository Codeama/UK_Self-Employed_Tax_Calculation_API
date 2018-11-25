package com.bukola;

import java.math.BigDecimal;
import java.util.List;

public interface Taxable {

    void calculate(List<BigDecimal> income);
    List<BigDecimal> getList();
    BigDecimal getTotalToDate();
}
