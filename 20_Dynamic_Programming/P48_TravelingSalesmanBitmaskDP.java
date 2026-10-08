import java.util.*;

/*
Problem: Traveling Salesman Problem (Bitmask DP)
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
Given the distances between n cities (n <= 16), return the length of the shortest route that starts at city 0,
visits every city exactly once, and returns to city 0.

Why this pattern fits:
The order of previously visited cities does not matter, only which ones were visited and where we are now.
A bitmask encodes the visited set compactly, shrinking n! orderings to 2^n * n states.

Approach:
dp[mask][last] = shortest path that starts at 0, visits exactly the cities in mask, and ends at `last`.
For each state and each city `next` not in mask: relax dp[mask | 1 << next][next].
The answer is min over last of dp[full][last] + dist[last][0].

Worked Example:
For the 4-city example the best tour is 0 -> 1 -> 3 -> 2 -> 0 with length 10 + 25 + 30 + 15 = 80.

Time Complexity: O(2^n * n^2)
Space Complexity: O(2^n * n)
*/

public class P48_TravelingSalesmanBitmaskDP {
    public static int shortestTour(int[][] dist) {
        int n = dist.length;
        int full = (1 << n) - 1;
        int infinity = Integer.MAX_VALUE / 2;
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, infinity);
        }
        dp[1][0] = 0;
        for (int mask = 1; mask <= full; mask++) {
            for (int last = 0; last < n; last++) {
                if (dp[mask][last] == infinity) {
                    continue;
                }
                for (int next = 0; next < n; next++) {
                    if ((mask & (1 << next)) == 0) {
                        int nextMask = mask | (1 << next);
                        dp[nextMask][next] = Math.min(dp[nextMask][next], dp[mask][last] + dist[last][next]);
                    }
                }
            }
        }
        int best = infinity;
        for (int last = 0; last < n; last++) {
            best = Math.min(best, dp[full][last] + dist[last][0]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("4 cities -> " + shortestTour(
                new int[][]{{0, 10, 15, 20}, {10, 0, 35, 25}, {15, 35, 0, 30}, {20, 25, 30, 0}}));
        System.out.println("3 cities -> " + shortestTour(new int[][]{{0, 1, 2}, {1, 0, 3}, {2, 3, 0}}));
    }
}
