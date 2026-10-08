import java.util.*;

/*
Problem: Minimum Absolute Difference Between Elements With Constraint
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Return the minimum absolute difference between two elements of nums that are at least x indices apart.

Why this pattern fits:
For index i the allowed partners are indices <= i - x. As i advances that pool only grows,
and the closest value to nums[i] inside a sorted pool is its floor or ceiling.

Approach:
For i from x to n - 1: add nums[i - x] to a TreeSet, then compare nums[i] with the set's floor and ceiling.

Worked Example:
nums = [5, 3, 2, 10, 15], x = 1 -> 3 and 2 are adjacent and differ by 1. Answer = 1.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P36_MinimumAbsoluteDifferenceBetweenElementsWithConstraintAdvanced {
    public static int minAbsoluteDifference(int[] nums, int x) {
        TreeSet<Integer> pool = new TreeSet<>();
        int best = Integer.MAX_VALUE;
        for (int i = x; i < nums.length; i++) {
            pool.add(nums[i - x]);
            Integer floor = pool.floor(nums[i]);
            Integer ceiling = pool.ceiling(nums[i]);
            if (floor != null) {
                best = Math.min(best, nums[i] - floor);
            }
            if (ceiling != null) {
                best = Math.min(best, ceiling - nums[i]);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[4, 3, 2, 4], x = 2 -> " + minAbsoluteDifference(new int[]{4, 3, 2, 4}, 2));
        System.out.println("[5, 3, 2, 10, 15], x = 1 -> " + minAbsoluteDifference(new int[]{5, 3, 2, 10, 15}, 1));
        System.out.println("[1, 2, 3, 4], x = 3 -> " + minAbsoluteDifference(new int[]{1, 2, 3, 4}, 3));
    }
}
