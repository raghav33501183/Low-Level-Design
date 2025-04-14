package models;

public enum UserLevel {
    Bronze(10, 0.05, 200),
    Silver(12.5, 0.10, 500),
    Gold(15, 0.15, 1000);

    private final double pointRatePer100;
    private final double maxRedeemPercent;
    private final int maxRedeemPoints;

    UserLevel(double pointRatePer100, double maxRedeemPercent, int maxRedeemPoints) {
        this.pointRatePer100 = pointRatePer100;
        this.maxRedeemPercent = maxRedeemPercent;
        this.maxRedeemPoints = maxRedeemPoints;
    }

    public double getPointRatePer100() {
        return pointRatePer100;
    }

    public double getMaxRedeemPercent() {
        return maxRedeemPercent;
    }

    public int getMaxRedeemPoints() {
        return maxRedeemPoints;
    }
}
