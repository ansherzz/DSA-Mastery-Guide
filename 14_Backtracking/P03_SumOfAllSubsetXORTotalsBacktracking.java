import java.util.*;

/*
Problem: Sum of All Subset XOR Totals
Pattern: 14. Backtracking
Difficulty: Easy

Interview Prompt:
The XOR total of an array is the XOR of all its elements (0 if empty).
Return the sum of the XOR totals of every subset of nums.

Why this pattern fits:
Every subset corresponds to one path of include/exclude decisions.
Passing the running XOR as a parameter means there is nothing to undo when backtracking.

Approach:
dfs(index, xor): at the end return xor. Otherwise return dfs(index + 1, xor ^ nums[index]) + dfs(index + 1, xor).

Worked Example:
nums = [1, 3] -> subsets {}, {1}, {3}, {1, 3} have XOR totals 0, 1, 3, 2. Answer = 6.

Time Complexity: O(2^n)
Space Complexity: O(n)
*/

public class P03_SumOfAllSubsetXORTotalsBacktracking {
    private static int dfs(int[] nums, int index, int xor) {
        if (index == nums.length) {
            return xor;
        }
        return dfs(nums, index + 1, xor ^ nums[index]) + dfs(nums, index + 1, xor);
    }

    public static int subsetXORSum(int[] nums) {
        return dfs(nums, 0, 0);
    }

    public static void main(String[] args) {
        System.out.println("[1, 3] -> " + subsetXORSum(new int[]{1, 3}));
        System.out.println("[5, 1, 6] -> " + subsetXORSum(new int[]{5, 1, 6}));
        System.out.println("[3, 4, 5, 6, 7, 8] -> " + subsetXORSum(new int[]{3, 4, 5, 6, 7, 8}));
    }
}
