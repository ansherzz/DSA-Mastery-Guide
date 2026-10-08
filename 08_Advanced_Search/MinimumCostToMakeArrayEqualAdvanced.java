import java.util.*;

/*
Problem: Minimum Cost to Make Array Equal
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
You are given two arrays nums and cost of n positive integers.
    In one operation you can increase or decrease any element nums[i] by 1, and doing so costs cost[i].
    Return the minimum total cost such that all the elements of nums become equal.

Why this pattern fits:
The total cost of making everything equal to x is the sum of cost[i] * |nums[i] - x|.
    That is a sum of convex functions, so it is convex in x: it goes down, reaches a minimum, then goes up.
    A convex function has no sorted values to compare against, but its slope is monotonic, and that can be searched.

Approach:
Search x between the minimum and maximum of nums.
    Compare f(mid) with f(mid + 1): if f(mid) <= f(mid + 1), the function is already rising,
    so the minimum is at mid or to its left. Otherwise it is still falling, so the minimum is to the right.
    The search converges on the minimizing x, and f at that point is the answer.
    A convex function can only be flat at its minimum, so the comparison is always safe.

Worked Example:
Example:
    nums = [1, 3, 5, 2], cost = [2, 3, 1, 14]
    Making every element 2 costs 1 * 2 + 1 * 3 + 3 * 1 + 0 = 8, which is the minimum.

Time Complexity: O(n log(max - min))
Space Complexity: O(1)
*/

public class MinimumCostToMakeArrayEqualAdvanced {
    public static long minCost(int[] nums, int[] cost) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int num : nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (totalCost(nums, cost, mid) <= totalCost(nums, cost, mid + 1)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return totalCost(nums, cost, low);
    }

    private static long totalCost(int[] nums, int[] cost, int target) {
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += (long) cost[i] * Math.abs(nums[i] - target);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(minCost(new int[]{1, 3, 5, 2}, new int[]{2, 3, 1, 14}));
        System.out.println(minCost(new int[]{2, 2, 2, 2, 2}, new int[]{4, 2, 8, 1, 3}));
        System.out.println(minCost(new int[]{1, 100}, new int[]{1, 1}));
    }
}
