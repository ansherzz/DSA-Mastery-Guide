import java.util.*;

/*
Problem: Find Smallest Common Element in All Rows
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Every row of an m x n matrix is sorted in strictly increasing order.
Return the smallest element that appears in all rows, or -1 if there is none.

Why this pattern fits:
Any common element must be in the first row, and that row is sorted,
so the first candidate found in all other rows is the answer. Each check is a sorted-row lookup.

Approach:
For each value in row 0, binary search it in rows 1..m - 1. Return the first value found everywhere.

Worked Example:
mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]] -> 5 is the first value present in every row.

Time Complexity: O(m * n * log n)
Space Complexity: O(1)
*/

public class P31_FindSmallestCommonElementInAllRowsBinarySearch {
    public static int smallestCommonElement(int[][] mat) {
        for (int candidate : mat[0]) {
            boolean inAllRows = true;
            for (int r = 1; r < mat.length && inAllRows; r++) {
                inAllRows = Arrays.binarySearch(mat[r], candidate) >= 0;
            }
            if (inAllRows) {
                return candidate;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {3, 5, 7, 9, 11}, {1, 3, 5, 7, 9}};
        System.out.println("4 rows -> " + smallestCommonElement(mat));
        System.out.println("[[1, 2], [3, 4]] -> " + smallestCommonElement(new int[][]{{1, 2}, {3, 4}}));
    }
}
