import java.util.*;

/*
Problem: Leftmost Column with at Least a One
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
A row-sorted binary matrix has every row sorted in non-decreasing order, so each row is some 0s followed by some 1s.
    Return the index of the leftmost column that contains at least one 1, or -1 if no such column exists.
    Reading a cell is expensive, so the number of cell reads should be far below rows * cols.

Why this pattern fits:
Each row is a sorted 0/1 array, so the first 1 in a row is a lower-bound search.
    The extra insight is that the search space shrinks across rows: a row can only improve the answer
    if its first 1 is strictly left of the best column found so far.

Approach:
Keep best, the leftmost column with a 1 found so far (initially the number of columns).
    For each row, binary search for the first 1 only inside columns [0, best).
    If one is found, it becomes the new best.
    If best never moves, there is no 1 in the matrix.

Worked Example:
Example:
    matrix = [[0, 0, 0, 1],
              [0, 0, 1, 1],
              [0, 1, 1, 1]]
    Row 0 gives column 3, row 1 improves it to 2, row 2 improves it to 1. Answer = 1.

Time Complexity: O(rows * log(cols))
Space Complexity: O(1)
*/

public class P30_LeftmostColumnWithAtLeastAOneAdvanced {
    public static int leftMostColumnWithOne(int[][] matrix) {
        int cols = matrix[0].length;
        int best = cols;

        for (int[] row : matrix) {
            int left = 0;
            int right = best;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (row[mid] == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            best = left;
        }
        return best == cols ? -1 : best;
    }

    public static void main(String[] args) {
        System.out.println(leftMostColumnWithOne(new int[][]{{0, 0}, {1, 1}}));
        System.out.println(leftMostColumnWithOne(new int[][]{{0, 0}, {0, 1}}));
        System.out.println(leftMostColumnWithOne(new int[][]{{0, 0}, {0, 0}}));
        System.out.println(leftMostColumnWithOne(new int[][]{{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 1}}));
    }
}
