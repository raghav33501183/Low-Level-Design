package models;

import java.math.BigDecimal;

public class Installment {
    private final int number;
    private final BigDecimal interestRate;
    private final BigDecimal remainingBalance;
    private final BigDecimal principalInstallment;
    private final BigDecimal interest;
    private final BigDecimal totalPayment;

    public Installment(int number, BigDecimal interestRate, BigDecimal remainingBalance,
                       BigDecimal principalInstallment, BigDecimal interest, BigDecimal totalPayment) {
        this.number = number;
        this.interestRate = interestRate;
        this.remainingBalance = remainingBalance;
        this.principalInstallment = principalInstallment;
        this.interest = interest;
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return String.format("Installment %d: Interest Rate = %.2f%%, Remaining Balance = %.2f, Principal Installment = %.2f, Interest = %.2f, Total Payment = %.2f",
                number, interestRate, remainingBalance, principalInstallment, interest, totalPayment);
    }

    public BigDecimal getInterest() {
        return interest;
    }
}