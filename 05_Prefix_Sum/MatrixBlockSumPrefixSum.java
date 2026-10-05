import java.util.*;

/*
Problem: Matrix Block Sum
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an m x n matrix mat and an integer k, return a matrix answer where answer[i][j] is the sum of all
    elements mat[r][c] with i - k <= r <= i + k and j - k <= c <= j + k, restricted to valid positions.

Why this pattern fits:
Every cell asks for the sum of a rectangle around it. Summing each rectangle directly costs O(k^2) per cell.
    A 2D prefix-sum table answers each rectangle in O(1) with inclusion-exclusion.

Approach:
Build a 2D prefix table with one extra row and column.
    For each cell, clamp the rectangle corners to the matrix boundaries.
    Compute the rectangle sum from four prefix values.

Worked Example:
Example:
    mat = [[1, 2, 3],
           [4, 5, 6],
           [7, 8, 9]], k = 1
    answer[0][0] covers rows 0..1 and columns 0..1: 1 + 2 + 4 + 5 = 12
    answer[1][1] covers the whole matrix: 45
    answer = [[12, 21, 16], [27, 45, 33], [24, 39, 28]]

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class MatrixBlockSumPrefixSum {
    public static int[][] matrixBlockSum(int[][] mat, int k) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[][] prefix = new int[rows + 1][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                prefix[r + 1][c + 1] = mat[r][c] + prefix[r][c + 1] + prefix[r + 1][c] - prefix[r][c];
            }
        }

        int[][] answer = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int top = Math.max(0, r - k);
                int left = Math.max(0, c - k);
                int bottom = Math.min(rows - 1, r + k);
                int right = Math.min(cols - 1, c + k);

                answer[r][c] = prefix[bottom + 1][right + 1]
                    - prefix[top][right + 1]
                    - prefix[bottom + 1][left]
                    + prefix[top][left];
            }
        }
        return answer;
    }

    private static void runDemo(int[][] mat, int k) {
        System.out.println("Matrix : " + Arrays.deepToString(mat) + ", k = " + k);
        System.out.println("Answer : " + Arrays.deepToString(matrixBlockSum(mat, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1);
        runDemo(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 2);
    }
}
