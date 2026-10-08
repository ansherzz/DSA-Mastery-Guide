import java.util.*;

/*
Problem: Partition Equal Subset Sum
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given an array of positive integers, return true if the array can be partitioned into two subsets
    whose sums are equal.

Why this pattern fits:
Two equal halves means one subset must sum to exactly half of the total.
    "Can some subset reach a target sum, using each item at most once?" is the 0/1 knapsack decision problem,
    one of the foundational DP patterns.

Approach:
If the total is odd, the answer is false. Otherwise let target = total / 2.
    State: reachable[s] is true if some subset of the numbers processed so far sums to s. reachable[0] is true.
    For each number, update the sums from target down to the number:
    reachable[s] becomes true if reachable[s - num] was true.
    Iterating downward ensures each number is used at most once.

Worked Example:
Example:
    nums = [1, 5, 11, 5]
    Total = 22, target = 11. The subset [11] reaches it (and so does [1, 5, 5]). Answer = true.

    nums = [1, 2, 3, 5] has total 11, which is odd. Answer = false.

Time Complexity: O(n * target)
Space Complexity: O(target)
*/

public class P16_PartitionEqualSubsetSumDP {
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 != 0) {
            return false;
        }

        int target = total / 2;
        boolean[] reachable = new boolean[target + 1];
        reachable[0] = true;

        for (int num : nums) {
            for (int sum = target; sum >= num; sum--) {
                if (reachable[sum - num]) {
                    reachable[sum] = true;
                }
            }
            if (reachable[target]) {
                return true;
            }
        }
        return reachable[target];
    }

    private static void runDemo(int[] nums) {
        System.out.println(Arrays.toString(nums) + " -> " + canPartition(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 5, 11, 5});
        runDemo(new int[]{1, 2, 3, 5});
        runDemo(new int[]{2, 2, 3, 5});
    }
}
