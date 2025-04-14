package models;

public class User {
    private final String name;
    private double points;
    private int orders;
    private double totalSpent;
    private boolean eligibleForDiscount;
    private int eligibleOrders;
    private double eligibleSpentAmount;

    public User(String name) {
        this.name = name;
        this.points = 0;
        this.orders = 0;
        this.totalSpent = 0;
        this.eligibleForDiscount = false;
    }

    public String getName() {
        return name;
    }

    public double getPoints() {
        return points;
    }

    public void addPoints(double pts) {
        this.points += pts;
    }

    public void redeemPoints(int pts) {
        this.points -= pts;
    }

    public void incrementOrders() {
        this.orders++;
    }

    public void incrementEligibleOrders() {
        this.eligibleOrders++;
    }

    public int getOrders() {
        return orders;
    }

    public void addSpent(double amount) {
        this.totalSpent += amount;
    }

    public void addEligibleSpent(double amount) {
        this.eligibleSpentAmount += amount;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setEligibleForDiscount(boolean eligibleForDiscount) {
        this.eligibleForDiscount = eligibleForDiscount;
    }

    public int getEligibleOrders() {
        return eligibleOrders;
    }

    public void setEligibleOrders(int eligibleOrders) {
        this.eligibleOrders = eligibleOrders;
    }

    public double getEligibleSpentAmount() {
        return eligibleSpentAmount;
    }

    public void setEligibleSpentAmount(double eligibleSpentAmount) {
        this.eligibleSpentAmount = eligibleSpentAmount;
    }

    public UserLevel getLevel() {
        if (points >= 1000) return UserLevel.Gold;
        if (points >= 500) return UserLevel.Silver;
        return UserLevel.Bronze;
    }

    public boolean isEligibleForDiscount() {
        return (orders > 3 || totalSpent > 10000) && !eligibleForDiscount;
    }

    public void markDiscountUsed() {
        this.eligibleForDiscount = true;
    }

    public void resetDiscountEligibility() {
        this.eligibleForDiscount = false;
    }
}
