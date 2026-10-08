import java.util.*;

/*
Problem: Median of a Row-Wise Sorted Matrix
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given an r x c matrix where every row is sorted in non-decreasing order and r * c is odd.
    Return the median of all the elements in the matrix.

    Do it without copying the elements into one array and sorting them.

Why this pattern fits:
Only the rows are sorted, so there is no global order to index into.
    Two searches are layered: an outer binary search on the candidate value,
    and an inner binary search (upper bound) in each row to count how many elements are <= that value.

Approach:
The median is the smallest value v such that at least (r * c) / 2 + 1 elements are <= v.
    Search v between the smallest first-column value and the largest last-column value.
    For each candidate, sum the upper bounds of all rows and compare against the required count.

Worked Example:
Example:
    matrix = [[1, 3, 5],
              [2, 6, 9],
              [3, 6, 9]]
    Sorted: 1, 2, 3, 3, 5, 6, 6, 9, 9. The median is the 5th value, which is 5.
    Count of elements <= 4 is 4 (too few). Count of elements <= 5 is 5.

Time Complexity: O(r * log c * log(max - min))
Space Complexity: O(1)
*/

public class P13_MedianOfRowWiseSortedMatrixAdvanced {
    private static int countAtMost(int[] row, int value) {
        int left = 0;
        int right = row.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int median(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            low = Math.min(low, row[0]);
            high = Math.max(high, row[cols - 1]);
        }

        int required = (rows * cols) / 2 + 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            int count = 0;
            for (int[] row : matrix) {
                count += countAtMost(row, mid);
            }

            if (count >= required) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static void runDemo(int[][] matrix) {
        System.out.println("Matrix : " + Arrays.deepToString(matrix));
        System.out.println("Median : " + median(matrix));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 3, 5}, {2, 6, 9}, {3, 6, 9}});
        runDemo(new int[][]{{1}, {2}, {3}});
        runDemo(new int[][]{{1, 5, 7, 9, 11}, {2, 3, 4, 8, 9}, {4, 11, 14, 19, 20}});
    }
}
