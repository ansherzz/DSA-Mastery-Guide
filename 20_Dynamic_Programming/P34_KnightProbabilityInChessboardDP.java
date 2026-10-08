import java.util.*;

/*
Problem: Knight Probability in Chessboard
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
A knight starts at (row, column) on an n x n board and makes exactly k moves, each chosen uniformly
from the eight knight moves. If it leaves the board it stays off. Return the probability that it is still on the board.

Why this pattern fits:
Probabilities of independent steps multiply and alternatives add, which is the same sum-over-transitions
structure as counting paths - with each transition weighted by 1/8.

Approach:
dp[r][c] = probability of being at (r, c) after the moves so far. For each move build a new board:
every cell sends dp[r][c] / 8 to each on-board destination. The answer is the sum of the final board.

Worked Example:
n = 3, k = 2, start (0, 0) -> 2 of 8 first moves stay on the board, and from each, 2 of 8 second moves do: 4 / 64 = 0.0625.

Time Complexity: O(k * n^2)
Space Complexity: O(n^2)
*/

public class P34_KnightProbabilityInChessboardDP {
    public static double knightProbability(int n, int k, int row, int column) {
        int[][] moves = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
        double[][] dp = new double[n][n];
        dp[row][column] = 1;
        for (int step = 0; step < k; step++) {
            double[][] next = new double[n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (dp[r][c] == 0) {
                        continue;
                    }
                    for (int[] move : moves) {
                        int nr = r + move[0];
                        int nc = c + move[1];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                            next[nr][nc] += dp[r][c] / 8;
                        }
                    }
                }
            }
            dp = next;
        }
        double total = 0;
        for (double[] line : dp) {
            for (double value : line) {
                total += value;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("n = 3, k = 2 -> " + String.format(Locale.ROOT, "%.5f", knightProbability(3, 2, 0, 0)));
        System.out.println("n = 1, k = 0 -> " + String.format(Locale.ROOT, "%.5f", knightProbability(1, 0, 0, 0)));
    }
}
