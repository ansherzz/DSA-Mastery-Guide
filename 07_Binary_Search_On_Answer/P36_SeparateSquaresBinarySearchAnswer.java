import java.util.*;

/*
Problem: Separate Squares I
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
squares[i] = [x, y, side] is an axis-aligned square with bottom-left corner (x, y).
Find the minimum y-coordinate of a horizontal line such that the total area of the squares above the line
equals the total area below it. Overlapping areas are counted once per square.

Why this pattern fits:
The area below a line never decreases as the line rises, so the balancing height can be bisected on real numbers.

Approach:
Compute the total area. Bisect y over the vertical extent for a fixed number of iterations:
the area below y is the sum of side * clamp(y - bottom, 0, side). Move the upper bound down whenever
the area below reaches half, which converges to the lowest balancing line.

Worked Example:
squares = [[0, 0, 2], [1, 1, 1]] -> total area 5. At y = 7/6 the area below is 2 * 7/6 + 1/6 = 2.5.

Time Complexity: O(n * iterations)
Space Complexity: O(1)
*/

public class P36_SeparateSquaresBinarySearchAnswer {
    public static double separateSquares(int[][] squares) {
        double total = 0;
        double low = Double.MAX_VALUE;
        double high = 0;
        for (int[] s : squares) {
            total += (double) s[2] * s[2];
            low = Math.min(low, s[1]);
            high = Math.max(high, (double) s[1] + s[2]);
        }
        for (int step = 0; step < 100; step++) {
            double mid = (low + high) / 2;
            double below = 0;
            for (int[] s : squares) {
                below += s[2] * Math.max(0, Math.min(s[2], mid - s[1]));
            }
            if (below * 2 >= total) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        System.out.println("[[0, 0, 1], [2, 2, 1]] -> "
                + String.format(Locale.ROOT, "%.5f", separateSquares(new int[][]{{0, 0, 1}, {2, 2, 1}})));
        System.out.println("[[0, 0, 2], [1, 1, 1]] -> "
                + String.format(Locale.ROOT, "%.5f", separateSquares(new int[][]{{0, 0, 2}, {1, 1, 1}})));
    }
}
