import java.util.*;

/*
Problem: Largest Rectangle in Histogram (Divide and Conquer)
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Given bar heights of a histogram (each of width 1), return the area of the largest rectangle inside it.

Why this pattern fits:
Any rectangle that covers the lowest bar of a range is limited to that bar's height,
so the widest one is best. Every other rectangle avoids that bar and therefore lives in the left or right part.

Approach:
solve(low, high): find the index of the minimum height. The answer is the maximum of
min * (high - low + 1), solve(low, minIndex - 1), and solve(minIndex + 1, high).
(Finding the minimum with a segment tree gives O(n log n); a linear scan is O(n^2) in the worst case.)

Worked Example:
heights = [2, 1, 5, 6, 2, 3] -> splitting at 1, the right part [5, 6, 2, 3] contains the 5-6 rectangle of area 10.

Time Complexity: O(n log n) average, O(n^2) worst case with a linear minimum scan
Space Complexity: O(n) recursion depth in the worst case
*/

public class P44_LargestRectangleInHistogramDivideConquer {
    private static int solve(int[] heights, int low, int high) {
        if (low > high) {
            return 0;
        }
        int minIndex = low;
        for (int i = low + 1; i <= high; i++) {
            if (heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }
        int spanning = heights[minIndex] * (high - low + 1);
        int sides = Math.max(solve(heights, low, minIndex - 1), solve(heights, minIndex + 1, high));
        return Math.max(spanning, sides);
    }

    public static int largestRectangleArea(int[] heights) {
        return solve(heights, 0, heights.length - 1);
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 5, 6, 2, 3] -> " + largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println("[2, 4] -> " + largestRectangleArea(new int[]{2, 4}));
    }
}
