package com.example.namhyun.crunchtime;

/**
 * Created by namhyun on 2/5/16.
 */
public class CalorieCounter {
    public double weight = 150;

    public CalorieCounter() { }

    public CalorieCounter(double weight) {
        this.weight = weight;
    }

    public double genericCaloriesBurned(double amount, Boolean repsUsed) {
        double calories = (float) 0.0;
        // if done in minutes
        if (!repsUsed) {
            calories = this.weight/150 * 9;
        } else {
            calories = (double) amount;
        }
        return calories;
    }

    public double pushUpsCaloriesBurned(double amount, Boolean repsUsed) {
        if (repsUsed) {
            return amount / 3.5 * this.weight/150;
        } else {
            return amount/11 * this.weight/150;
        }
    }

    public double squatsCaloriesBurned(double amount, Boolean repsUsed) {
        return this.genericCaloriesBurned(amount, repsUsed);
    }

    public double situpsCaloriesBurned(double amount, Boolean repsUsed) {
        if (repsUsed) {
            return amount / 2 * this.weight/150;
        } else {
            return amount/11 * this.weight/150;
        }
    }

    public double pullupsCaloriesBurned(double amount, Boolean repsUsed) {
        return this.genericCaloriesBurned(amount, repsUsed);
    }

    public double legLiftsCaloriesBurned(double amount, Boolean repsUsed) {
        return this.genericCaloriesBurned(amount, repsUsed);
    }

    public double jumpingJacks(double amount, Boolean repsUsed) {
        if (!repsUsed) {
            return amount/(10/100.0) * this.weight/150;
        }
        return amount/100 * this.weight/150;
    }

    public double plankCaloriesBurned(double amount) {
        return this.genericCaloriesBurned(amount, false);
    }

    public double cyclingCaloriesBurned(double amount) {
        return this.genericCaloriesBurned(amount, false);
    }

    public double walkingCaloriesBurned(double amount) {
        return this.genericCaloriesBurned(amount, false);
    }

    public double joggingCaloriesBurned(double amount, Boolean minsUsed) {
        if (minsUsed) {
            return amount/(12/100.0) * this.weight/150;
        }
        return amount/5280 * this.weight/150;
    }

    public double swimmingCaloriesBurned(double amount) {
        return this.genericCaloriesBurned(amount, false);
    }

    public double stairclimbingCaloriesBurned(double amount) {
        return this.genericCaloriesBurned(amount, false);
    }

    public int getRepsOfPushUpsToReachCalories(double calories) {
        return (int) ((int) calories * 3.5 * this.weight/150);
    }

    public int getRepsOfSitUpsToReachCalories(double calories) {
        return (int) ((int) calories * 2 * this.weight/150);
    }

    public double getMinutesOfJumpingJacksToReachCalories(double calories) {
        return calories * 0.1 * this.weight/150;
    }

    public double getMinutesOfJoggingToReachCalories(double calories) {
        return calories * 0.12 * this.weight/150;
    }
}
