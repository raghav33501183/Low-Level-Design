import exception.LoanValidationException;
import models.Loan;
import models.Installment;
import services.InterestCalculator;
import services.InterestCalculatorFactory;
import services.RepaymentPlanBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            BigDecimal[] interestRates = Stream.of(
                            3.0, 1.5, 1.5, 2.0,
                            0.5, 0.5, 0.5, 2.0,
                            2.0, 2.0, 1.5, 1.0
                    ).map(BigDecimal::valueOf)
                    .toArray(BigDecimal[]::new);

            Loan loan = new Loan(BigDecimal.valueOf(1000), BigDecimal.valueOf(45));

            if (loan.getAmount() == null || loan.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new LoanValidationException("Loan amount must be positive.");
            }
            if (loan.getMaxInterest() == null || loan.getMaxInterest().compareTo(BigDecimal.ZERO) < 0) {
                throw new LoanValidationException("Maximum interest cannot be negative.");
            }

            InterestCalculator calculator = InterestCalculatorFactory.getInterestCalculator("simple");

            RepaymentPlanBuilder builder = new RepaymentPlanBuilder(calculator);
            List<Installment> plan = builder.buildPlan(loan, interestRates);

            if (plan != null) {
                plan.forEach(System.out::println);
                BigDecimal totalCost = plan.stream()
                        .map(Installment::getInterest)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                System.out.printf("Total Cost of the plan: %.2f\n", totalCost);
            } else {
                System.out.println("No valid repayment plan found under the given constraints.");
            }
        } catch (LoanValidationException ex) {
            System.err.println("Validation Error: " + ex.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error due to: " + e.getMessage());
        }
    }
}