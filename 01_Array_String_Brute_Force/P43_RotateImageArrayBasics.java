import java.util.*;

/*
Problem: Rotate Image
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees clockwise.
    You must rotate the image in place, without allocating another 2D matrix.

Why this pattern fits:
The brute-force idea is to copy every cell (r, c) into (c, n - 1 - r) of a new matrix.
    Understanding that index mapping is what lets you decompose the rotation into two simple in-place steps.

Approach:
A 90-degree clockwise rotation equals a transpose followed by reversing every row.
    Step 1: transpose by swapping matrix[r][c] with matrix[c][r] for c > r.
    Step 2: reverse each row with a two-ended swap.
    Check: (r, c) -> transpose -> (c, r) -> reverse row -> (c, n - 1 - r), which is the rotation mapping.

Worked Example:
Example:
    1 2 3        1 4 7        7 4 1
    4 5 6   ->   2 5 8   ->   8 5 2
    7 8 9        3 6 9        9 6 3
    (original)  (transposed) (rows reversed)

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P43_RotateImageArrayBasics {
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int r = 0; r < n; r++) {
            for (int c = r + 1; c < n; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }

        for (int[] row : matrix) {
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Before: " + Arrays.deepToString(matrix));
        rotate(matrix);
        System.out.println("After : " + Arrays.deepToString(matrix));

        int[][] small = {{1, 2}, {3, 4}};
        rotate(small);
        System.out.println("After : " + Arrays.deepToString(small));
    }
}
