import java.util.*;

/*
Problem: Capacity To Ship Packages Within D Days
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given package weights in order. Each day, ship a prefix of the remaining packages whose total weight
    does not exceed the ship capacity. Return the minimum capacity needed to ship everything within a given number of days.

    The candidate answer is a capacity, not an index.

Why this pattern fits:
If a capacity works, any larger capacity also works.
    That monotonic feasibility lets us binary search the answer.

Approach:
Search capacity from max(weights) to sum(weights).
    A helper simulates how many days a given capacity would require.

Worked Example:
Example:
    weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], days = 5
    The minimum feasible capacity is 15.

Time Complexity: O(n log S), where S is the sum of weights
Space Complexity: O(1)
*/

public class ShipPackagesWithinDDaysBinarySearchAnswer {
    private static boolean canShip(int[] weights, int days, int capacity) {
        int usedDays = 1;
        int current = 0;

        for (int weight : weights) {
            if (current + weight > capacity) {
                usedDays++;
                current = 0;
            }
            current += weight;
        }

        return usedDays <= days;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static void runDemo(int[] weights, int days) {
        System.out.println("Weights  : " + Arrays.toString(weights));
        System.out.println("Days     : " + days);
        System.out.println("Capacity : " + shipWithinDays(weights, days));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
        runDemo(new int[]{3, 2, 2, 4, 1, 4}, 3);
    }
}
