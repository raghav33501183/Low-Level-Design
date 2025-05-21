package services;

public class InterestCalculatorFactory {
    public static InterestCalculator getInterestCalculator(String calculatorType) {
        return switch (calculatorType) {
            case "simple" -> new SimpleInterestCalculator();
            default -> throw new RuntimeException("Wrong calculator type passed");
        };
    }
}
