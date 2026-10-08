import java.util.*;

/*
Problem: Maximum Side Length of a Square with Sum Less than or Equal to Threshold
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Given an m x n matrix mat of non-negative integers and an integer threshold,
    return the maximum side length of a square with a sum less than or equal to threshold.
    Return 0 if there is no such square.

Why this pattern fits:
All values are non-negative, so shrinking a valid square keeps it valid.
    If a square of side L exists, a square of side L - 1 exists too, which makes the side length binary-searchable.

Approach:
Build a 2D prefix sum so the sum of any square is available in O(1).
    To test a side length, check every possible top-left corner.
    Binary search the side length between 0 and min(m, n), keeping the largest feasible one.

Worked Example:
Example:
    mat = [[1, 1, 3, 2, 4, 3, 2],
           [1, 1, 3, 2, 4, 3, 2],
           [1, 1, 3, 2, 4, 3, 2]], threshold = 4
    The top-left 2 x 2 square sums to 4. No 3 x 3 square is small enough. Answer = 2.

Time Complexity: O(m * n * log(min(m, n)))
Space Complexity: O(m * n)
*/

public class P32_MaximumSideLengthOfSquareBinarySearchAnswer {
    public static int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] prefix = new int[rows + 1][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                prefix[r + 1][c + 1] = mat[r][c] + prefix[r][c + 1] + prefix[r + 1][c] - prefix[r][c];
            }
        }

        int low = 0;
        int high = Math.min(rows, cols);
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (existsSquare(prefix, mid, threshold)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static boolean existsSquare(int[][] prefix, int side, int threshold) {
        for (int r = side; r < prefix.length; r++) {
            for (int c = side; c < prefix[0].length; c++) {
                int sum = prefix[r][c] - prefix[r - side][c] - prefix[r][c - side] + prefix[r - side][c - side];
                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] first = {
            {1, 1, 3, 2, 4, 3, 2},
            {1, 1, 3, 2, 4, 3, 2},
            {1, 1, 3, 2, 4, 3, 2}};
        System.out.println(maxSideLength(first, 4));

        int[][] second = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        System.out.println(maxSideLength(second, 1));
        System.out.println(maxSideLength(second, 18));
    }
}
