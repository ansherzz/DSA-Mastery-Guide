import java.util.*;

/*
Problem: Target Sum
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You are given an integer array nums of non-negative integers and an integer target.
    You want to build an expression by adding one of the symbols '+' or '-' before each integer in nums
    and then concatenating all the integers.
    Return the number of different expressions that you can build which evaluate to target.

Why this pattern fits:
Trying every sign assignment is 2^n. A change of viewpoint turns it into a counting knapsack:
    let P be the sum of the numbers given '+', and N the sum of those given '-'.
    Then P - N = target and P + N = total, so P = (total + target) / 2.
    The question becomes: how many subsets of nums have sum P?

Approach:
If |target| > total or (total + target) is odd, there is no solution.
    State: ways[s] is the number of subsets of the numbers processed so far whose sum is s. ways[0] = 1.
    For each number, sweep s from high to low and do ways[s] += ways[s - num].
    The backward sweep ensures each number is used at most once (0/1 knapsack).
    Zeros are handled naturally: a zero doubles every count, matching its two sign choices.

Worked Example:
Example:
    nums = [1, 1, 1, 1, 1], target = 3
    total = 5, so P = (5 + 3) / 2 = 4. There are 5 ways to choose four of the five 1s. Answer = 5.

Time Complexity: O(n * P)
Space Complexity: O(P)
*/

public class TargetSumDP {
    public static int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (Math.abs(target) > total || (total + target) % 2 != 0) {
            return 0;
        }

        int positiveSum = (total + target) / 2;
        int[] ways = new int[positiveSum + 1];
        ways[0] = 1;

        for (int num : nums) {
            for (int sum = positiveSum; sum >= num; sum--) {
                ways[sum] += ways[sum - num];
            }
        }
        return ways[positiveSum];
    }

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays(new int[]{1}, 1));
        System.out.println(findTargetSumWays(new int[]{0, 0, 1}, 1));
        System.out.println(findTargetSumWays(new int[]{1, 2}, 4));
    }
}
