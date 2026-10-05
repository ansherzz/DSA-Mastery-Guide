import java.util.*;

/*
Problem: House Robber II
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Houses are arranged in a circle, so the first house and the last house are neighbors.
    Each house holds some money, and robbing two adjacent houses on the same night triggers the alarm.
    Return the maximum amount of money you can rob without triggering the alarm.

Why this pattern fits:
The straight-line version (House Robber) is a standard DP. The circle adds only one new constraint:
    the first and last houses cannot both be robbed. Splitting on that constraint turns the circular
    problem into two straight-line problems, each solved by the known recurrence.

Approach:
If there is one house, rob it.
    Otherwise solve the linear problem twice: once on houses 0 .. n - 2 (the last house is excluded)
    and once on houses 1 .. n - 1 (the first house is excluded). Return the larger result.
    The linear recurrence is best(i) = max(best(i - 1), best(i - 2) + nums[i]).

Worked Example:
Example:
    nums = [2, 3, 2]
    Houses 0 and 2 are adjacent in the circle, so they cannot both be robbed. The best is house 1 alone: 3.

    nums = [1, 2, 3, 1]
    Rob houses 0 and 2 for a total of 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class HouseRobberIIDP {
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    private static int robRange(int[] nums, int start, int end) {
        int twoBack = 0;
        int oneBack = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(oneBack, twoBack + nums[i]);
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 3, 2}));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{5}));
    }
}
