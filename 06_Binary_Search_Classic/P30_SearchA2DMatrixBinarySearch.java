import java.util.*;

/*
Problem: Search a 2D Matrix
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given an m x n integer matrix with two properties:
    - each row is sorted in non-decreasing order
    - the first value of each row is greater than the last value of the previous row

    Given a target, return true if it is in the matrix. The solution must run in O(log(m * n)) time.

Why this pattern fits:
The two properties mean that reading the matrix row by row produces one fully sorted list.
    You can binary search that virtual list and translate each index back into a row and column.

Approach:
Treat the matrix as a sorted array of length m * n.
    For a virtual index mid, the cell is matrix[mid / n][mid % n].
    Run the standard binary search using that mapping.

Worked Example:
Example:
    matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 3
    Virtual index 5 is 11 (too big), virtual index 2 is 5 (too big), virtual index 0 is 1 (too small),
    virtual index 1 is 3. Answer = true.

Time Complexity: O(log(m * n))
Space Complexity: O(1)
*/

public class P30_SearchA2DMatrixBinarySearch {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = matrix[mid / cols][mid % cols];
            if (value == target) {
                return true;
            }
            if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    private static void runDemo(int[][] matrix, int target) {
        System.out.println("Matrix : " + Arrays.deepToString(matrix));
        System.out.println("Target : " + target);
        System.out.println("Found? : " + searchMatrix(matrix, target));
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        runDemo(matrix, 3);
        runDemo(matrix, 13);
        runDemo(matrix, 60);
    }
}
