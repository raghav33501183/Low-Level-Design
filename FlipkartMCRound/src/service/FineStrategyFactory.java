package service;

public class FineStrategyFactory {

    public static FineCalculationStrategy getFineStrategy() {
        return new DefaultFineCalculator();
    }
}
