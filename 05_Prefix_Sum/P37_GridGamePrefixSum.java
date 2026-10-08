import java.util.*;

/*
Problem: Grid Game
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
On a 2 x n grid two robots move from (0, 0) to (1, n - 1) going only right or down.
The first robot collects (zeroes) the cells on its path and wants to minimize what the second robot can collect;
the second robot then maximizes its own total. Return the points collected by the second robot.

Why this pattern fits:
The first robot's path is determined by the single column where it moves down.
What remains is the top row to the right of that column and the bottom row to the left of it: a suffix and a prefix.

Approach:
Start with topRemaining = sum of row 0 and bottomCollected = 0.
For each column c: remove grid[0][c] from topRemaining, the second robot takes max(topRemaining, bottomCollected),
then add grid[1][c] to bottomCollected. Return the minimum over all columns.

Worked Example:
grid = [[2, 5, 4], [1, 5, 1]] -> dropping at column 1 leaves 4 on top and 1 below. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P37_GridGamePrefixSum {
    public static long gridGame(int[][] grid) {
        long topRemaining = 0;
        for (int value : grid[0]) {
            topRemaining += value;
        }
        long bottomCollected = 0;
        long best = Long.MAX_VALUE;
        for (int c = 0; c < grid[0].length; c++) {
            topRemaining -= grid[0][c];
            best = Math.min(best, Math.max(topRemaining, bottomCollected));
            bottomCollected += grid[1][c];
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[[2, 5, 4], [1, 5, 1]] -> " + gridGame(new int[][]{{2, 5, 4}, {1, 5, 1}}));
        System.out.println("[[3, 3, 1], [8, 5, 2]] -> " + gridGame(new int[][]{{3, 3, 1}, {8, 5, 2}}));
        System.out.println("[[1, 3, 1, 15], [1, 3, 3, 1]] -> " + gridGame(new int[][]{{1, 3, 1, 15}, {1, 3, 3, 1}}));
    }
}
