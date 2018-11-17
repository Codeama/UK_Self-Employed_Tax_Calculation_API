package com.bukola;

import java.math.BigDecimal;
import java.util.List;

public interface Taxable {

    List<BigDecimal> calculate(List<BigDecimal> income);

    BigDecimal getTotalToDate();
}
