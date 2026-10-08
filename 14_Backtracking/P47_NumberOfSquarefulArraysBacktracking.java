import java.util.*;

/*
Problem: Number of Squareful Arrays
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
An array is squareful if the sum of every pair of adjacent elements is a perfect square.
Return the number of distinct permutations of nums that are squareful.

Why this pattern fits:
It is "Permutations II" with a pruning rule: a number may be appended only if it forms a square
with the previous one. Sorting plus the usual duplicate-skipping rule keeps permutations distinct.

Approach:
Sort. dfs(previous, placed): for each unused index i, skip it if it equals the previous value and that
previous index is unused (duplicate rule) or if previous + nums[i] is not a perfect square. Otherwise use it and recurse.

Worked Example:
nums = [1, 17, 8] -> [1, 8, 17] and [17, 8, 1] work (9 and 25 are squares). Answer = 2.

Time Complexity: O(n!) worst case
Space Complexity: O(n)
*/

public class P47_NumberOfSquarefulArraysBacktracking {
    private static boolean isSquare(int value) {
        int root = (int) Math.round(Math.sqrt(value));
        return root * root == value;
    }

    private static int dfs(int[] nums, boolean[] used, int previous, int placed) {
        if (placed == nums.length) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            if (placed > 0 && !isSquare(previous + nums[i])) {
                continue;
            }
            used[i] = true;
            count += dfs(nums, used, nums[i], placed + 1);
            used[i] = false;
        }
        return count;
    }

    public static int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);
        return dfs(nums, new boolean[nums.length], 0, 0);
    }

    public static void main(String[] args) {
        System.out.println("[1, 17, 8] -> " + numSquarefulPerms(new int[]{1, 17, 8}));
        System.out.println("[2, 2, 2] -> " + numSquarefulPerms(new int[]{2, 2, 2}));
    }
}
