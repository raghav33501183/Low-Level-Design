package service;

public interface FineCalculationStrategy {
    int calculateFine(long borrowedAtMillis);
}
