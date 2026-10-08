import java.util.*;

/*
Problem: Kth Smallest Element in a Sorted Matrix
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Given an n x n matrix where every row and every column is sorted in ascending order,
    return the kth smallest element in the matrix (counting duplicates, in sorted order).

    Find a solution that uses less than O(n^2) memory.

Why this pattern fits:
The matrix is not fully sorted, so you cannot index into it directly.
    Instead, binary search on the value: for a candidate value v, count how many elements are <= v.
    That count is monotonic in v, and the answer is the smallest v whose count reaches k.

Approach:
Search v between the smallest element (top-left) and the largest element (bottom-right).
    Count elements <= v with a staircase walk that starts at the bottom-left corner:
    if the current value is <= v, the whole column above it qualifies, so add (row + 1) and move right;
    otherwise move up.

Worked Example:
Example:
    matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]], k = 8
    Sorted order: 1, 5, 9, 10, 11, 12, 13, 13, 15. The 8th smallest is 13.
    Count of elements <= 12 is 6 (too few) and count of elements <= 13 is 8.

Time Complexity: O(n log(max - min))
Space Complexity: O(1)
*/

public class P19_KthSmallestInSortedMatrixBinarySearchAnswer {
    private static int countAtMost(int[][] matrix, int value) {
        int n = matrix.length;
        int row = n - 1;
        int col = 0;
        int count = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= value) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        return count;
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countAtMost(matrix, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[][] matrix, int k) {
        System.out.println("Matrix : " + Arrays.deepToString(matrix));
        System.out.println("k      : " + k);
        System.out.println("Answer : " + kthSmallest(matrix, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8);
        runDemo(new int[][]{{-5}}, 1);
    }
}
