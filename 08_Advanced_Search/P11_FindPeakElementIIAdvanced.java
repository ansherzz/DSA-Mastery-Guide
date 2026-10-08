import java.util.*;

/*
Problem: Find a Peak Element II
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
A peak in a 2D grid is a cell that is strictly greater than its neighbors to the left, right, top, and bottom.
    You are given an m x n matrix where no two adjacent cells are equal. Cells outside the matrix count as -1.
    Find any peak and return its position [row, col].

    The solution must run in O(m log n) or O(n log m) time.

Why this pattern fits:
This is the 2D version of Find Peak Element. Binary search works on the columns:
    the maximum of the middle column is already greater than its top and bottom neighbors,
    so comparing it with its left and right neighbors tells you which side must contain a peak.

Approach:
Binary search over the column range.
    In the middle column, find the row holding the column maximum.
    If that cell beats both horizontal neighbors, it is a peak.
    Otherwise move toward the larger horizontal neighbor, because climbing in that direction must end at a peak.

Worked Example:
Example:
    mat = [[10, 20, 15],
           [21, 30, 14],
           [ 7, 16, 32]]
    Middle column is [20, 30, 16] with maximum 30 at row 1.
    30 is greater than 21 on the left and 14 on the right, so [1, 1] is a peak.

Time Complexity: O(m log n)
Space Complexity: O(1)
*/

public class P11_FindPeakElementIIAdvanced {
    public static int[] findPeakGrid(int[][] mat) {
        int left = 0;
        int right = mat[0].length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int maxRow = 0;
            for (int row = 1; row < mat.length; row++) {
                if (mat[row][mid] > mat[maxRow][mid]) {
                    maxRow = row;
                }
            }

            int leftNeighbor = mid > 0 ? mat[maxRow][mid - 1] : -1;
            int rightNeighbor = mid < mat[0].length - 1 ? mat[maxRow][mid + 1] : -1;

            if (mat[maxRow][mid] > leftNeighbor && mat[maxRow][mid] > rightNeighbor) {
                return new int[]{maxRow, mid};
            }
            if (leftNeighbor > mat[maxRow][mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    private static void runDemo(int[][] mat) {
        int[] peak = findPeakGrid(mat);
        System.out.println("Matrix : " + Arrays.deepToString(mat));
        System.out.println("Peak   : " + Arrays.toString(peak) + " with value " + mat[peak[0]][peak[1]]);
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 4}, {3, 2}});
        runDemo(new int[][]{{10, 20, 15}, {21, 30, 14}, {7, 16, 32}});
    }
}
