import java.util.*;

/*
Problem: Minimum Strokes to Paint a Fence
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
A fence has n planks of width 1 with the given heights. One brush stroke paints either a whole plank vertically
or a horizontal strip of height 1 across consecutive planks. Return the minimum number of strokes.

Why this pattern fits:
Horizontal strokes are only worthwhile when they span the whole segment, up to its lowest plank.
After that, the planks at the minimum height are finished and they split the segment into independent pieces.

Approach:
solve(low, high, base): let m be the minimum height in the segment.
Horizontal cost = (m - base) + the sum of solve over each maximal run of planks taller than m, with base m.
Answer = min(number of planks, horizontal cost).

Worked Example:
heights = [2, 2, 1, 2, 1] -> one horizontal stroke at height 1, then [2, 2] needs 1 stroke and [2] needs 1. Answer = 3.

Time Complexity: O(n^2) worst case
Space Complexity: O(n) recursion depth
*/

public class P28_MinimumStrokesToPaintFenceDivideConquer {
    private static int solve(int[] heights, int low, int high, int base) {
        if (low > high) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            min = Math.min(min, heights[i]);
        }
        int horizontal = min - base;
        int start = low;
        for (int i = low; i <= high + 1; i++) {
            if (i == high + 1 || heights[i] == min) {
                horizontal += solve(heights, start, i - 1, min);
                start = i + 1;
            }
        }
        return Math.min(high - low + 1, horizontal);
    }

    public static int minStrokes(int[] heights) {
        return solve(heights, 0, heights.length - 1, 0);
    }

    public static void main(String[] args) {
        System.out.println("[2, 2, 1, 2, 1] -> " + minStrokes(new int[]{2, 2, 1, 2, 1}));
        System.out.println("[2, 2] -> " + minStrokes(new int[]{2, 2}));
        System.out.println("[5] -> " + minStrokes(new int[]{5}));
    }
}
