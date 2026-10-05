import java.util.*;

/*
Problem: Minimum Speed to Arrive on Time
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You must take n trains in order to reach the office. dist[i] is the distance of the ith train ride.
    Each train departs only on an integer hour, so after each ride except the last you may have to wait
    until the next whole hour. You have `hour` hours in total (a decimal number).

    Return the minimum positive integer speed that all trains must travel at for you to arrive on time,
    or -1 if it is impossible. The answer will not exceed 10^7.

Why this pattern fits:
Travelling faster never makes the trip longer, so "can arrive on time" is monotonic in the speed.
    The speed itself is the answer being searched.

Approach:
Search the speed between 1 and 10^7.
    For a candidate speed, every ride except the last takes ceil(dist / speed) hours because of the waiting,
    and the last ride takes exactly dist / speed hours.
    If even the fastest allowed speed fails, return -1.

Worked Example:
Example:
    dist = [1, 3, 2], hour = 2.7
    Speed 3: 1 hour (0.33 rounded up) + 1 hour + 0.667 hours = 2.667 <= 2.7. Feasible.
    Speed 2: 1 + 2 + 1 = 4 hours. Not feasible. Answer = 3.

Time Complexity: O(n log(10^7))
Space Complexity: O(1)
*/

public class MinimumSpeedToArriveOnTimeBinarySearchAnswer {
    private static final int MAX_SPEED = 10_000_000;

    private static boolean arrivesOnTime(int[] dist, double hour, int speed) {
        double total = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            total += (dist[i] + speed - 1) / speed;
        }
        total += (double) dist[dist.length - 1] / speed;
        return total <= hour;
    }

    public static int minSpeedOnTime(int[] dist, double hour) {
        if (!arrivesOnTime(dist, hour, MAX_SPEED)) {
            return -1;
        }

        int left = 1;
        int right = MAX_SPEED;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arrivesOnTime(dist, hour, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] dist, double hour) {
        System.out.println("Distances : " + Arrays.toString(dist));
        System.out.println("Hours     : " + hour);
        System.out.println("Speed     : " + minSpeedOnTime(dist, hour));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, 2}, 6);
        runDemo(new int[]{1, 3, 2}, 2.7);
        runDemo(new int[]{1, 3, 2}, 1.9);
    }
}
