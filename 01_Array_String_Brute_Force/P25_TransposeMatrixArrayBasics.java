import java.util.*;

/*
Problem: Transpose Matrix
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an m x n integer matrix, return its transpose:
the matrix flipped over its main diagonal, so rows become columns.

Why this pattern fits:
This is a pure index-mapping exercise and the basis of matrix rotation.

Approach:
Allocate a matrix with swapped dimensions and copy each cell (r, c) to (c, r).

Worked Example:
matrix = [[1, 2, 3], [4, 5, 6]]
Row 0 becomes column 0 and row 1 becomes column 1: [[1, 4], [2, 5], [3, 6]].

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P25_TransposeMatrixArrayBasics {
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result[c][r] = matrix[r][c];
            }
        }
        return result;
    }

    private static void runDemo(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix) + " -> " + Arrays.deepToString(transpose(matrix)));
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2, 3}, {4, 5, 6}});
        runDemo(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
