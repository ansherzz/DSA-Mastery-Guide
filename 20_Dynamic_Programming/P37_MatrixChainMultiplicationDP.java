import java.util.*;

/*
Problem: Matrix Chain Multiplication
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Matrix i has dimensions dims[i - 1] x dims[i]. Multiplying a p x q matrix by a q x r matrix costs p * q * r.
Return the minimum total cost of multiplying the whole chain, over all ways to parenthesize it.

Why this pattern fits:
The final multiplication splits the chain into a left block and a right block that are solved independently.
The sub-problems are contiguous intervals, so the table is indexed by (start, end) and filled by increasing length.

Approach:
dp[i][j] = min over k in [i, j) of dp[i][k] + dp[k + 1][j] + dims[i - 1] * dims[k] * dims[j].
Single matrices cost 0.

Worked Example:
dims = [40, 20, 30, 10, 30] -> the best order is (A (B C)) D with cost 26000.

Time Complexity: O(n^3)
Space Complexity: O(n^2)
*/

public class P37_MatrixChainMultiplicationDP {
    public static int matrixChainOrder(int[] dims) {
        int n = dims.length - 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int length = 2; length <= n; length++) {
            for (int i = 1; i + length - 1 <= n; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + dims[i - 1] * dims[k] * dims[j]);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println("[40, 20, 30, 10, 30] -> " + matrixChainOrder(new int[]{40, 20, 30, 10, 30}));
        System.out.println("[10, 20, 30, 40, 30] -> " + matrixChainOrder(new int[]{10, 20, 30, 40, 30}));
        System.out.println("[10, 20, 30] -> " + matrixChainOrder(new int[]{10, 20, 30}));
    }
}
