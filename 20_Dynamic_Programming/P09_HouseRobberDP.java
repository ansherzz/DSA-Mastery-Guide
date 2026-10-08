import java.util.*;

/*
Problem: House Robber
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You are given an array where each value is the amount of money in a house.
    Adjacent houses cannot both be robbed.
    Return the maximum money you can rob.

    This is a classic choose-or-skip DP.

Why this pattern fits:
At each house, you decide between two options:
    rob it and add the best answer from two houses back, or skip it and keep the previous best answer.

Approach:
Maintain two rolling values:
    robPrevious = best answer up to the previous house,
    robBeforePrevious = best answer up to the house before that.

Worked Example:
Example:
    nums = [2,7,9,3,1]
    The maximum loot is 12.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P09_HouseRobberDP {
    public static int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;

        for (int money : nums) {
            int current = Math.max(prev1, prev2 + money);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }
}
