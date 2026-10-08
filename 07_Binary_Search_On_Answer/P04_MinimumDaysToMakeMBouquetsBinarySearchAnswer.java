import java.util.*;

/*
Problem: Minimum Days to Make m Bouquets
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an array bloomDay where bloomDay[i] is the day the ith flower blooms.
    To make one bouquet, you need k adjacent flowers that have all bloomed by the chosen day.
    Return the minimum day needed to make exactly m bouquets, or -1 if impossible.

    The answer is a day, and feasibility is monotonic over time.

Why this pattern fits:
If bouquets can be made by day d, they can also be made by any later day.
    That makes the day value searchable with binary search.

Approach:
Search days between the minimum and maximum bloom day.
    A helper scans the array and counts how many adjacent groups of size k are available by that day.

Worked Example:
Example:
    bloomDay = [1, 10, 3, 10, 2], m = 3, k = 1
    The minimum day is 3.

Time Complexity: O(n log D), where D is the day range
Space Complexity: O(1)
*/

public class P04_MinimumDaysToMakeMBouquetsBinarySearchAnswer {
    private static boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int consecutive = 0;

        for (int bloom : bloomDay) {
            if (bloom <= day) {
                consecutive++;
                if (consecutive == k) {
                    bouquets++;
                    consecutive = 0;
                }
            } else {
                consecutive = 0;
            }
        }

        return bouquets >= m;
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        long needed = (long) m * k;
        if (needed > bloomDay.length) {
            return -1;
        }

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canMake(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static void runDemo(int[] bloomDay, int m, int k) {
        System.out.println("Bloom days: " + Arrays.toString(bloomDay));
        System.out.println("m = " + m + ", k = " + k);
        System.out.println("Answer    : " + minDays(bloomDay, m, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 10, 3, 10, 2}, 3, 1);
        runDemo(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3);
    }
}
