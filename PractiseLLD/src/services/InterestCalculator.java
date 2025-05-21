package services;

import java.math.BigDecimal;

public interface InterestCalculator {
    BigDecimal calculate(BigDecimal balance, BigDecimal rate);
}
