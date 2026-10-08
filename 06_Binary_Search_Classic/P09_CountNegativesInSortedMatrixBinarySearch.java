import java.util.*;

/*
Problem: Count Negative Numbers in a Sorted Matrix
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given an m x n matrix where every row and every column is sorted in non-increasing order,
    return the number of negative numbers in the matrix.

Why this pattern fits:
Each row looks like: non-negative values first, then negative values.
    The position of the first negative in a row is a boundary, and a boundary in sorted data means binary search.

Approach:
For each row, binary search for the first index whose value is negative.
    Everything from that index to the end of the row is negative, so add (columns - index) to the count.

Worked Example:
Example:
    grid = [[ 4,  3,  2, -1],
            [ 3,  2,  1, -1],
            [ 1,  1, -1, -2],
            [-1, -1, -2, -3]]
    Negatives per row: 1, 1, 2, 4. Answer = 8.

Time Complexity: O(m log n)
Space Complexity: O(1)
*/

public class P09_CountNegativesInSortedMatrixBinarySearch {
    public static int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] row : grid) {
            count += row.length - firstNegativeIndex(row);
        }
        return count;
    }

    private static int firstNegativeIndex(int[] row) {
        int left = 0;
        int right = row.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] < 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[][] grid) {
        System.out.println("Grid     : " + Arrays.deepToString(grid));
        System.out.println("Negatives: " + countNegatives(grid));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}});
        runDemo(new int[][]{{3, 2}, {1, 0}});
    }
}
