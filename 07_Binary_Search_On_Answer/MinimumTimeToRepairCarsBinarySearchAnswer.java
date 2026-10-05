import java.util.*;

/*
Problem: Minimum Time to Repair Cars
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an array ranks where ranks[i] is the rank of the ith mechanic.
    A mechanic with rank r can repair n cars in r * n^2 minutes.
    All mechanics work at the same time. Given the total number of cars waiting,
    return the minimum time needed to repair all of them.

Why this pattern fits:
Deciding how to split cars among mechanics directly is hard.
    But given a time limit t, each mechanic's capacity is fixed: floor(sqrt(t / r)) cars.
    Total capacity grows with t, so the smallest sufficient t can be binary searched.

Approach:
Search t between 1 and (smallest rank * cars^2), the time for the best mechanic to do everything alone.
    For a candidate t, add up floor(sqrt(t / rank)) over all mechanics.
    If the total is at least the number of cars, try a smaller t.

Worked Example:
Example:
    ranks = [4, 2, 3, 1], cars = 10
    At t = 16: capacities are sqrt(4) = 2, sqrt(8) = 2, sqrt(5.33) = 2, sqrt(16) = 4, total 10. Feasible.
    At t = 15: capacities are 1, 2, 2, 3, total 8. Not feasible. Answer = 16.

Time Complexity: O(n log(minRank * cars^2))
Space Complexity: O(1)
*/

public class MinimumTimeToRepairCarsBinarySearchAnswer {
    private static boolean canRepairAll(int[] ranks, int cars, long limit) {
        long repaired = 0;
        for (int rank : ranks) {
            repaired += (long) Math.sqrt((double) (limit / rank));
            if (repaired >= cars) {
                return true;
            }
        }
        return false;
    }

    public static long repairCars(int[] ranks, int cars) {
        int bestRank = Integer.MAX_VALUE;
        for (int rank : ranks) {
            bestRank = Math.min(bestRank, rank);
        }

        long left = 1;
        long right = (long) bestRank * cars * cars;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (canRepairAll(ranks, cars, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] ranks, int cars) {
        System.out.println("Ranks : " + Arrays.toString(ranks));
        System.out.println("Cars  : " + cars);
        System.out.println("Time  : " + repairCars(ranks, cars));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{4, 2, 3, 1}, 10);
        runDemo(new int[]{5, 1, 8}, 6);
    }
}
