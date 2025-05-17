package service;

public class FineStrategyFactory {

    public static FineCalculationStrategy getFineStrategy() {
        // switch (type) { case 1: return obj1}
        return new DefaultFineCalculator();
    }
}
