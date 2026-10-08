import java.util.*;

/*
Problem: Search a 2D Matrix II
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Write an efficient algorithm that searches for a target in an m x n integer matrix with these properties:
    - each row is sorted in ascending order from left to right
    - each column is sorted in ascending order from top to bottom

    Unlike the simpler version, the first value of a row is not necessarily larger than the last value of the previous row.

Why this pattern fits:
The matrix cannot be flattened into one sorted list, so plain binary search does not apply.
    But from the top-right corner every comparison eliminates an entire row or an entire column,
    which is the same "discard a region on each step" idea that powers binary search.

Approach:
Start at the top-right cell.
    If the value equals the target, return true.
    If the value is larger than the target, nothing below it in that column can match, so move left.
    If the value is smaller, nothing to its left in that row can match, so move down.

Worked Example:
Example:
    matrix = [[ 1,  4,  7, 11, 15],
              [ 2,  5,  8, 12, 19],
              [ 3,  6,  9, 16, 22],
              [10, 13, 14, 17, 24],
              [18, 21, 23, 26, 30]]
    target = 5: 15 -> 11 -> 7 -> 4 (move down) -> 5. Found.

Time Complexity: O(m + n)
Space Complexity: O(1)
*/

public class P10_SearchA2DMatrixIIAdvanced {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            int value = matrix[row][col];
            if (value == target) {
                return true;
            }
            if (value > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        System.out.println("Target 5  found? " + searchMatrix(matrix, 5));
        System.out.println("Target 20 found? " + searchMatrix(matrix, 20));
        System.out.println("Target 30 found? " + searchMatrix(matrix, 30));
    }
}
