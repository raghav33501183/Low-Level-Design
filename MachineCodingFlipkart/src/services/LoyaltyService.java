package services;

import exceptions.UserNotFoundException;
import models.User;
import models.UserLevel;
import util.DiscountCalculator;

import java.util.HashMap;
import java.util.Map;

public class LoyaltyService {
    private final Map<String, User> users = new HashMap<>();

    public void onboardUser(String name) {
        if (users.containsKey(name)) {
            System.out.println("User already exists.");
        } else {
            users.put(name, new User(name));
            System.out.println("User onboarded: " + name);
        }
    }

    public void purchase(String name, double amount, int pointsToRedeem) {
        User user = users.get(name);
        if (user == null) throw new UserNotFoundException("User not found.");

        UserLevel level = user.getLevel();
        int maxRedeem = Math.min(level.getMaxRedeemPoints(), (int) (level.getMaxRedeemPercent() * amount));
        if (pointsToRedeem > maxRedeem) {
            System.out.println("Purchase Failed. Redemption exceeds max limit.");
            return;
        }
        if (pointsToRedeem > user.getPoints()) {
            System.out.println("Purchase Failed. Not enough points to redeem");
            return;
        }

        double remainingAmount = amount - pointsToRedeem;
        double discount = DiscountCalculator.calculateDiscount(user, remainingAmount);
        double finalAmount = remainingAmount - discount;
        finalAmount = Math.max(0, finalAmount);

        double pointsEarned = (finalAmount / 100.0) * level.getPointRatePer100();

        if (pointsToRedeem > 0) user.redeemPoints(pointsToRedeem);

        user.addPoints(pointsEarned);
        user.incrementOrders();
        user.incrementEligibleOrders();
        user.addSpent(finalAmount);
        user.addEligibleSpent(finalAmount);

        if (user.isEligibleForDiscount()) user.markDiscountUsed();

        System.out.print("Purchase successful");
        if (pointsToRedeem > 0) {
            System.out.print(". Points redeemed: " + pointsToRedeem);
        }

        System.out.print(". Points added: " + pointsEarned + " .");
        if (discount > 0) {
            System.out.println("Discount applied: ₹" + discount + ". ");
        }

        System.out.println("Total payable amount: " + finalAmount + " . Current points: " + user.getPoints() + " . Current level: " + user.getLevel());
        System.out.println("Orders count: " + user.getOrders());
    }

    public void printUserStats(String name) {
        User user = users.get(name);
        if (user == null) throw new UserNotFoundException("User not found.");
        System.out.println(name + " has " + user.getPoints() + " points. Current level: " + user.getLevel());
    }
}
