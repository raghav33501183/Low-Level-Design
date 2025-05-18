package services;

public class DefaultFineStrategy implements FineStrategy  {
    private static final double DAILY_FINE = 1.0; // currency per day

    public double calculateFine(long overdueDays) {
        return overdueDays * DAILY_FINE;
    }
}
