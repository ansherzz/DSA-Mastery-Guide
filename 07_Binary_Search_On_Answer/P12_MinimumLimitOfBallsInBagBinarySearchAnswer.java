import java.util.*;

/*
Problem: Minimum Limit of Balls in a Bag
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an array nums where nums[i] is the number of balls in bag i, and an integer maxOperations.
    In one operation you may take any bag and split it into two new bags, each with a positive number of balls.
    Your penalty is the maximum number of balls in any bag.

    Return the minimum possible penalty after performing at most maxOperations operations.

Why this pattern fits:
The goal is to minimize a maximum. If a penalty p is achievable, any larger penalty is also achievable.
    That monotonic feasibility lets you binary search directly on p.

Approach:
Search p between 1 and the largest bag.
    To bring a bag of size b down to bags of size at most p, you need (b - 1) / p splits.
    Sum the splits for all bags; p is feasible when the total is at most maxOperations.

Worked Example:
Example:
    nums = [9], maxOperations = 2
    p = 3: (9 - 1) / 3 = 2 splits, giving bags 3, 3, 3. Feasible.
    p = 2: (9 - 1) / 2 = 4 splits. Not feasible. Answer = 3.

Time Complexity: O(n log(max(nums)))
Space Complexity: O(1)
*/

public class P12_MinimumLimitOfBallsInBagBinarySearchAnswer {
    private static boolean achievable(int[] nums, int maxOperations, int penalty) {
        long operations = 0;
        for (int balls : nums) {
            operations += (balls - 1) / penalty;
        }
        return operations <= maxOperations;
    }

    public static int minimumSize(int[] nums, int maxOperations) {
        int left = 1;
        int right = 0;
        for (int balls : nums) {
            right = Math.max(right, balls);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (achievable(nums, maxOperations, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] nums, int maxOperations) {
        System.out.println("Bags       : " + Arrays.toString(nums));
        System.out.println("Operations : " + maxOperations);
        System.out.println("Penalty    : " + minimumSize(nums, maxOperations));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{9}, 2);
        runDemo(new int[]{2, 4, 8, 2}, 4);
        runDemo(new int[]{7, 17}, 2);
    }
}
