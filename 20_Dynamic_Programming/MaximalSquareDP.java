import java.util.*;

/*
Problem: Maximal Square
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given an m x n binary matrix filled with '0' and '1' characters,
    find the largest square containing only '1's and return its area.

Why this pattern fits:
Checking every possible square directly is far too slow.
    The size of the largest square ending at a cell is determined by the three squares ending at its
    top, left, and top-left neighbors, so each cell is solved in O(1) from earlier cells.

Approach:
State: dp[r][c] is the side length of the largest all-ones square whose bottom-right corner is cell (r, c).
    Transition: if the cell is '0', dp is 0.
    If it is '1', dp[r][c] = 1 + min(dp[r - 1][c], dp[r][c - 1], dp[r - 1][c - 1]).
    The minimum is the limiting factor: a square of side k needs squares of side k - 1 above, to the left, and diagonally.
    The table has one extra row and column of zeros so the first row and column need no special case.
    The answer is the largest side squared.

Worked Example:
Example:
    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0
    The largest square of 1s has side 2 (rows 1-2, columns 2-3 or 3-4). Area = 4.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class MaximalSquareDP {
    public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int bestSide = 0;

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                if (matrix[r - 1][c - 1] == '1') {
                    dp[r][c] = 1 + Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1]));
                    bestSide = Math.max(bestSide, dp[r][c]);
                }
            }
        }
        return bestSide * bestSide;
    }

    public static void main(String[] args) {
        char[][] first = {
            "10100".toCharArray(),
            "10111".toCharArray(),
            "11111".toCharArray(),
            "10010".toCharArray()};
        System.out.println(maximalSquare(first));

        char[][] second = {"01".toCharArray(), "10".toCharArray()};
        System.out.println(maximalSquare(second));
        System.out.println(maximalSquare(new char[][]{"0".toCharArray()}));
    }
}
