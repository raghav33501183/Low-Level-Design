package services;

import exception.LoanValidationException;
import models.Loan;
import models.Installment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RepaymentPlanBuilder {
    private static final int MIN_TERM = 3;
    private static final int MAX_TERM = 12;
    private final InterestCalculator interestCalculator;

    public RepaymentPlanBuilder(InterestCalculator interestCalculator) {
        this.interestCalculator = interestCalculator;
    }

    private void validateInputs(Loan loan, BigDecimal[] interestRates) {
        if (interestRates == null || interestRates.length < MIN_TERM) {
            throw new LoanValidationException("Monthly interest rates provided are less than required duration.");
        }
    }

    public List<Installment> buildPlan(Loan loan, BigDecimal[] interestRates) {
        validateInputs(loan, interestRates);

        int low = MIN_TERM;
        int high = Math.min(MAX_TERM, interestRates.length);
        List<Installment> bestPlan = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            List<Installment> plan = generatePlan(loan, interestRates, mid);

            if (plan != null) {
                bestPlan = plan;     // Valid plan found, try to find a longer one
                low = mid + 1;
            } else {
                high = mid - 1;      // Invalid, try shorter durations
            }
        }

        return bestPlan;
    }

    private List<Installment> generatePlan(Loan loan, BigDecimal[] interestRates, int months) {
        if (interestRates.length < months) return null;

        List<Installment> plan = new ArrayList<>();
        BigDecimal balance = loan.getAmount();
        BigDecimal monthlyPrincipal = loan.getAmount()
                .divide(BigDecimal.valueOf(months), 6, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalInterest = BigDecimal.ZERO;

        for (int i = 0; i < months; i++) {
            BigDecimal rate = interestRates[i];
            BigDecimal interest = interestCalculator.calculate(balance, rate);

            BigDecimal principalInstallment = (i == months - 1)
                    ? balance.setScale(2, RoundingMode.HALF_UP)
                    : monthlyPrincipal;

            BigDecimal totalPayment = principalInstallment.add(interest);
            Installment installment = new Installment(i + 1, rate, balance.setScale(2, RoundingMode.HALF_UP),
                    principalInstallment, interest, totalPayment);

            plan.add(installment);
            totalInterest = totalInterest.add(interest);
            balance = balance.subtract(principalInstallment);
        }

        return totalInterest.compareTo(loan.getMaxInterest()) <= 0 ? plan : null;
    }
}