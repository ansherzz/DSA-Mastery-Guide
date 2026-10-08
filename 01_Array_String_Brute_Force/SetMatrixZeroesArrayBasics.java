import java.util.*;

/*
Problem: Set Matrix Zeroes
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an m x n integer matrix, if an element is 0, set its entire row and column to 0.
    You must do it in place.

Why this pattern fits:
The naive attempt of zeroing rows and columns while scanning fails because new zeros get mistaken for original ones.
    The fix is the classic brute-force discipline of separating the "record" pass from the "apply" pass.

Approach:
First pass: record which rows and which columns contain an original zero.
    Second pass: set a cell to zero if its row or its column was recorded.
    This version uses two boolean arrays for clarity.
    Upgrade path: reuse the first row and first column of the matrix as the marker arrays to reach O(1) extra space.

Worked Example:
Example:
    1 1 1        1 0 1
    1 0 1   ->   0 0 0
    1 1 1        1 0 1
    Row 1 and column 1 contain a zero, so both are cleared.

Time Complexity: O(m * n)
Space Complexity: O(m + n)
*/

public class SetMatrixZeroesArrayBasics {
    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] zeroRow = new boolean[rows];
        boolean[] zeroCol = new boolean[cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    zeroRow[r] = true;
                    zeroCol[c] = true;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (zeroRow[r] || zeroCol[c]) {
                    matrix[r][c] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] first = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(first);
        System.out.println(Arrays.deepToString(first));

        int[][] second = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(second);
        System.out.println(Arrays.deepToString(second));
    }
}
