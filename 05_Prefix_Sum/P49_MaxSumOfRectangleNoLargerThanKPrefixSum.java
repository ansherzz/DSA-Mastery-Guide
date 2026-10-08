import java.util.*;

/*
Problem: Max Sum of Rectangle No Larger Than K
Pattern: 05. Prefix Sum
Difficulty: Hard

Interview Prompt:
Given an m x n matrix and an integer k, return the maximum sum of a rectangle
such that the sum is no larger than k. Such a rectangle is guaranteed to exist.

Why this pattern fits:
Fixing the left and right columns reduces the problem to 1D: "largest subarray sum <= k".
For each prefix p we need the smallest earlier prefix that is >= p - k, which an ordered set finds with ceiling().

Approach:
For each pair of columns accumulate row sums. Sweep the rows with a running prefix,
query ceiling(prefix - k) in a TreeSet of earlier prefixes (seeded with 0), and update the best.

Worked Example:
matrix = [[1, 0, 1], [0, -2, 3]], k = 2 -> the rectangle [[0, 1], [-2, 3]] sums to 2.

Time Complexity: O(cols^2 * rows * log rows)
Space Complexity: O(rows)
*/

public class P49_MaxSumOfRectangleNoLargerThanKPrefixSum {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int best = Integer.MIN_VALUE;
        for (int left = 0; left < cols; left++) {
            int[] rowSum = new int[rows];
            for (int right = left; right < cols; right++) {
                for (int r = 0; r < rows; r++) {
                    rowSum[r] += matrix[r][right];
                }
                TreeSet<Integer> seen = new TreeSet<>();
                seen.add(0);
                int prefix = 0;
                for (int value : rowSum) {
                    prefix += value;
                    Integer earlier = seen.ceiling(prefix - k);
                    if (earlier != null) {
                        best = Math.max(best, prefix - earlier);
                    }
                    seen.add(prefix);
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[[1, 0, 1], [0, -2, 3]], k = 2 -> " + maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
        System.out.println("[[2, 2, -1]], k = 3 -> " + maxSumSubmatrix(new int[][]{{2, 2, -1}}, 3));
    }
}
