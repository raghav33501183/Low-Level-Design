package util;

import models.User;

public class DiscountCalculator {
    public static double calculateDiscount(User user, double amount) {
        var ordersGreaterThan3 = user.getEligibleOrders() > 3;
        var spentGreaterThan10k = user.getEligibleSpentAmount() > 10000;
        if (!ordersGreaterThan3 && !spentGreaterThan10k) return 0;

        user.setEligibleOrders(0);
        user.setEligibleSpentAmount(0);

        double discountRate = 0;
        if (ordersGreaterThan3 && spentGreaterThan10k) discountRate = 0.12;
        else if (spentGreaterThan10k) discountRate = 0.10;
        else discountRate = 0.05;

        return Math.min(amount * discountRate, 5000);
    }
}
