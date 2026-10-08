import java.util.*;

/*
Problem: Longest Increasing Path in a Matrix
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
Given an m x n integer matrix, return the length of the longest strictly increasing path.
    From each cell you can move in four directions: left, right, up, or down.
    Diagonal moves and wrapping around the border are not allowed.

Why this pattern fits:
Draw an edge from each cell to every neighbor with a larger value.
    Because values strictly increase along edges, this graph has no cycles, so no visited set is required.
    The longest path starting at a cell depends only on the longest paths starting at its larger neighbors,
    which makes DFS with memoization both correct and linear.

Approach:
Define longest(r, c) as the length of the longest increasing path that starts at (r, c).
    It equals 1 plus the maximum of longest(neighbor) over neighbors with a strictly larger value.
    Cache each result in a table so every cell is computed once.
    The answer is the maximum over all cells.

Worked Example:
Example:
    matrix = 9 9 4
             6 6 8
             2 1 1
    The longest increasing path is 1 -> 2 -> 6 -> 9, which has length 4.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P28_LongestIncreasingPathInMatrixDFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int longestIncreasingPath(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];
        int best = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                best = Math.max(best, longest(matrix, r, c, memo));
            }
        }
        return best;
    }

    private static int longest(int[][] matrix, int r, int c, int[][] memo) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int length = 1;
        for (int[] direction : DIRECTIONS) {
            int nr = r + direction[0];
            int nc = c + direction[1];
            if (nr < 0 || nc < 0 || nr >= matrix.length || nc >= matrix[0].length) {
                continue;
            }
            if (matrix[nr][nc] > matrix[r][c]) {
                length = Math.max(length, 1 + longest(matrix, nr, nc, memo));
            }
        }

        memo[r][c] = length;
        return length;
    }

    public static void main(String[] args) {
        System.out.println(longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
        System.out.println(longestIncreasingPath(new int[][]{{1}}));
    }
}
