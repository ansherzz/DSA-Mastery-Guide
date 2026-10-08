import java.util.*;

/*
Problem: Increment Submatrices by One
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Start with an n x n matrix of zeros. Each query [r1, c1, r2, c2] adds 1 to every cell
of the submatrix with top-left (r1, c1) and bottom-right (r2, c2). Return the final matrix.

Why this pattern fits:
This is the 2D version of range update: a rectangle update becomes four point updates,
and one 2D prefix pass recovers the values.

Approach:
For each query: diff[r1][c1]++, diff[r1][c2 + 1]--, diff[r2 + 1][c1]--, diff[r2 + 1][c2 + 1]++.
Then cell = diff + top + left - topLeft, computed in row-major order.

Worked Example:
n = 3, queries = [[1, 1, 2, 2], [0, 0, 1, 1]] -> [[1, 1, 0], [1, 2, 1], [0, 1, 1]].

Time Complexity: O(n^2 + q)
Space Complexity: O(n^2)
*/

public class P30_IncrementSubmatricesByOnePrefixSum {
    public static int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n + 1][n + 1];
        for (int[] q : queries) {
            diff[q[0]][q[1]]++;
            diff[q[0]][q[3] + 1]--;
            diff[q[2] + 1][q[1]]--;
            diff[q[2] + 1][q[3] + 1]++;
        }
        int[][] result = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int top = r > 0 ? result[r - 1][c] : 0;
                int left = c > 0 ? result[r][c - 1] : 0;
                int topLeft = r > 0 && c > 0 ? result[r - 1][c - 1] : 0;
                result[r][c] = diff[r][c] + top + left - topLeft;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 3 -> " + Arrays.deepToString(rangeAddQueries(3, new int[][]{{1, 1, 2, 2}, {0, 0, 1, 1}})));
    }
}
