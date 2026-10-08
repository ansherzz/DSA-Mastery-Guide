import java.util.*;

/*
Problem: Min Cost Climbing Stairs
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
You are given an array cost where cost[i] is the cost of stepping on the ith stair.
    After paying the cost of a stair, you may climb one or two stairs.
    You may start from stair 0 or stair 1, and the goal is to reach the top, which is just past the last stair.

    Return the minimum total cost to reach the top.

Why this pattern fits:
The cheapest way to stand on a stair depends only on the cheapest ways to stand on the two stairs below it.
    That overlapping, optimal substructure is what dynamic programming exploits.

Approach:
State: dp[i] is the minimum cost paid to arrive at position i (before paying for stair i itself).
    Base: dp[0] = dp[1] = 0, since you can start on either stair for free.
    Transition: dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]).
    The answer is dp[n], the position past the last stair. Two variables are enough.

Worked Example:
Example:
    cost = [10, 15, 20]
    Start on stair 1, pay 15, and jump two stairs to the top. Answer = 15.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P03_MinCostClimbingStairsDP {
    public static int minCostClimbingStairs(int[] cost) {
        int twoBelow = 0;
        int oneBelow = 0;

        for (int i = 2; i <= cost.length; i++) {
            int current = Math.min(oneBelow + cost[i - 1], twoBelow + cost[i - 2]);
            twoBelow = oneBelow;
            oneBelow = current;
        }
        return oneBelow;
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
