import java.util.*;

/*
Problem: Climbing Stairs
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
You can climb a staircase of n steps by taking either 1 step or 2 steps at a time.
    Return the number of distinct ways to reach the top.

    This is the classic entry point into dynamic programming.

Why this pattern fits:
The number of ways to reach step i depends only on the two previous steps:
    ways(i) = ways(i - 1) + ways(i - 2).

Approach:
Use bottom-up DP with two rolling variables to store the previous two answers.

Worked Example:
Example:
    n = 5
    The answer is 8.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P01_ClimbingStairsDP {
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;

        for (int step = 3; step <= n; step++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(5));
    }
}
