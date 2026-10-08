import java.util.*;

/*
Problem: Count Number of Maximum Bitwise-OR Subsets
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Find the maximum possible bitwise OR of a subset of nums and return the number of different
non-empty subsets that achieve it. Subsets are different if their chosen indices differ.

Why this pattern fits:
OR only gains bits, so the maximum is the OR of all elements.
Counting subsets that reach a specific value is a direct include/exclude enumeration.

Approach:
Compute the target OR. dfs(index, or): at the end return 1 if or == target.
Otherwise sum the results of including and excluding nums[index].

Worked Example:
nums = [3, 1] -> target 3 is reached by {3} and {3, 1}. Answer = 2.

Time Complexity: O(2^n)
Space Complexity: O(n)
*/

public class P05_CountNumberOfMaximumBitwiseORSubsetsBacktracking {
    private static int dfs(int[] nums, int index, int or, int target) {
        if (index == nums.length) {
            return or == target ? 1 : 0;
        }
        return dfs(nums, index + 1, or | nums[index], target) + dfs(nums, index + 1, or, target);
    }

    public static int countMaxOrSubsets(int[] nums) {
        int target = 0;
        for (int value : nums) {
            target |= value;
        }
        return dfs(nums, 0, 0, target);
    }

    public static void main(String[] args) {
        System.out.println("[3, 1] -> " + countMaxOrSubsets(new int[]{3, 1}));
        System.out.println("[2, 2, 2] -> " + countMaxOrSubsets(new int[]{2, 2, 2}));
        System.out.println("[3, 2, 1, 5] -> " + countMaxOrSubsets(new int[]{3, 2, 1, 5}));
    }
}
