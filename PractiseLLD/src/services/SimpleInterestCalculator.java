package services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleInterestCalculator implements InterestCalculator {
    @Override
    public BigDecimal calculate(BigDecimal balance, BigDecimal rate) {
        return balance.multiply(rate).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
