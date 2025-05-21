package models;

import java.math.BigDecimal;
import java.util.UUID;

public class Loan {
    private final String loanId;
    private final BigDecimal amount;
    private final BigDecimal maxInterest;

    public Loan(BigDecimal amount, BigDecimal maxInterest) {
        this.loanId = UUID.randomUUID().toString();
        this.amount = amount;
        this.maxInterest = maxInterest;
    }

    public String getLoanId() {
        return loanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMaxInterest() {
        return maxInterest;
    }
}