import java.util.*;

/*
Problem: Diagonal Traverse
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an m x n matrix, return all elements in diagonal order:
the first diagonal goes up-right, the next goes down-left, and so on, alternating.

Why this pattern fits:
Matrix traversal problems are about finding the index invariant. Here every diagonal has a constant r + c.

Approach:
For each diagonal d = r + c from 0 to m + n - 2, collect its cells from the top row downwards.
Even diagonals must be read bottom-to-top, so reverse them before appending.

Worked Example:
matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
Diagonals: [1], [2, 4], [3, 5, 7] reversed to [7, 5, 3], [6, 8], [9].
Answer = [1, 2, 4, 7, 5, 3, 6, 8, 9].

Time Complexity: O(m * n)
Space Complexity: O(min(m, n)) extra
*/

public class P42_DiagonalTraverseArrayBasics {
    public static int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows * cols];
        int index = 0;
        for (int d = 0; d < rows + cols - 1; d++) {
            List<Integer> diagonal = new ArrayList<>();
            for (int r = Math.max(0, d - cols + 1); r <= Math.min(d, rows - 1); r++) {
                diagonal.add(matrix[r][d - r]);
            }
            if (d % 2 == 0) {
                Collections.reverse(diagonal);
            }
            for (int value : diagonal) {
                result[index++] = value;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("3x3 -> " + Arrays.toString(findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
        System.out.println("2x2 -> " + Arrays.toString(findDiagonalOrder(new int[][]{{1, 2}, {3, 4}})));
    }
}
