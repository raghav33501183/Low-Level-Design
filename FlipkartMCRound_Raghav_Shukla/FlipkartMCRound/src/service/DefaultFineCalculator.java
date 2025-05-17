package service;

public class DefaultFineCalculator implements FineCalculationStrategy {
    private static final long ALLOWED_DAYS = 14;
    private static final int FINE_PER_DAY = 20;

    @Override
    public int calculateFine(long borrowedAtMillis) {
        long now = System.currentTimeMillis();
        // can be simulated using seconds instead of days
//        long daysBorrowed = (now - borrowedAtMillis) / (1000 * 60 * 60 * 24);
        long daysBorrowed = (now - borrowedAtMillis) / (1000);
        return daysBorrowed > ALLOWED_DAYS ? (int) (daysBorrowed - ALLOWED_DAYS) * FINE_PER_DAY : 0;
    }
}
