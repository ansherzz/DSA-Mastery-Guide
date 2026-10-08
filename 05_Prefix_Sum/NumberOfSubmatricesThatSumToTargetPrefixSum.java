import java.util.*;

/*
Problem: Number of Submatrices That Sum to Target
Pattern: 05. Prefix Sum
Difficulty: Hard

Interview Prompt:
Given a matrix and a target, return the number of non-empty submatrices that sum to target.
    A submatrix is identified by its top-left and bottom-right corners,
    and two submatrices are different if any of their corner coordinates differ.

Why this pattern fits:
This is Subarray Sum Equals K lifted to two dimensions.
    Fixing a pair of rows collapses the matrix into a 1D array of column sums,
    and the 1D prefix-sum-with-hash-map technique counts the matching column ranges.

Approach:
For each top row, keep an array columnSum that accumulates each column from the top row down to the bottom row.
    For each bottom row, add that row into columnSum, then count subarrays of columnSum with sum equal to target
    using a running prefix and a map of prefix counts.
    Every (top, bottom, left, right) combination is counted exactly once.

Worked Example:
Example:
    matrix = [[0, 1, 0], [1, 1, 1], [0, 1, 0]], target = 0
    The four corner cells are the only submatrices with sum 0. Answer = 4.

Time Complexity: O(rows^2 * cols)
Space Complexity: O(cols)
*/

public class NumberOfSubmatricesThatSumToTargetPrefixSum {
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        for (int top = 0; top < rows; top++) {
            int[] columnSum = new int[cols];
            for (int bottom = top; bottom < rows; bottom++) {
                for (int c = 0; c < cols; c++) {
                    columnSum[c] += matrix[bottom][c];
                }
                count += countSubarrays(columnSum, target);
            }
        }
        return count;
    }

    private static int countSubarrays(int[] nums, int target) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += prefixCount.getOrDefault(prefix - target, 0);
            prefixCount.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
        System.out.println(numSubmatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, 0));
        System.out.println(numSubmatrixSumTarget(new int[][]{{904}}, 0));
    }
}
