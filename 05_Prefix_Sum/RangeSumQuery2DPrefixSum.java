import java.util.*;

/*
Problem: Range Sum Query 2D - Immutable
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given a 2D matrix, answer many queries of the form:
    "what is the sum of the elements inside the rectangle with upper-left corner (row1, col1)
    and lower-right corner (row2, col2)?"

    The matrix does not change, and each query must be answered in O(1) time.

Why this pattern fits:
This is the 2D version of the immutable range-sum query.
    Precomputing the sum of every rectangle that starts at the origin lets any rectangle be built
    from four of those values using inclusion-exclusion.

Approach:
Build prefix with one extra row and column, where prefix[r][c] is the sum of the rectangle from (0, 0)
    to (r - 1, c - 1). Each cell is: value + top + left - top-left.
    A query is: prefix[row2+1][col2+1] - prefix[row1][col2+1] - prefix[row2+1][col1] + prefix[row1][col1].

Worked Example:
Example:
    matrix = [[3, 0, 1, 4, 2],
              [5, 6, 3, 2, 1],
              [1, 2, 0, 1, 5],
              [4, 1, 0, 1, 7],
              [1, 0, 3, 0, 5]]
    sumRegion(2, 1, 4, 3) = 2 + 0 + 1 + 1 + 0 + 1 + 0 + 3 + 0 = 8

Time Complexity: O(m * n) to build, O(1) per query
Space Complexity: O(m * n)
*/

public class RangeSumQuery2DPrefixSum {
    static class NumMatrix {
        private final int[][] prefix;

        NumMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = rows == 0 ? 0 : matrix[0].length;
            prefix = new int[rows + 1][cols + 1];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    prefix[r + 1][c + 1] = matrix[r][c] + prefix[r][c + 1] + prefix[r + 1][c] - prefix[r][c];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefix[row2 + 1][col2 + 1]
                - prefix[row1][col2 + 1]
                - prefix[row2 + 1][col1]
                + prefix[row1][col1];
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);

        System.out.println("sumRegion(2,1,4,3) : " + numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println("sumRegion(1,1,2,2) : " + numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println("sumRegion(1,2,2,4) : " + numMatrix.sumRegion(1, 2, 2, 4));
    }
}
