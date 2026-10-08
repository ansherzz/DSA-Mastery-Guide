import java.util.*;

/*
Problem: Largest Rectangle in Histogram
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Given bar heights in a histogram, return the area of the largest rectangle that can be formed.

    The monotonic stack tells you exactly when a bar's rectangle can no longer extend to the right.

Why this pattern fits:
When a shorter bar appears, any taller bar on the stack has found its right boundary.
    The stack stores indices of increasing heights so areas can be computed when bars are popped.

Approach:
Scan left to right while keeping indices of increasing bar heights on a stack.
    Append a virtual bar of height 0 at the end to flush all remaining bars.

Worked Example:
Example:
    heights = [2, 1, 5, 6, 2, 3]
    The largest rectangle area is 10.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P26_LargestRectangleInHistogramStack {
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;

        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftBoundary - 1;
                best = Math.max(best, height * width);
            }

            stack.push(i);
        }

        return best;
    }

    private static void runDemo(int[] heights) {
        System.out.println("Heights : " + Arrays.toString(heights));
        System.out.println("Best    : " + largestRectangleArea(heights));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 1, 5, 6, 2, 3});
        runDemo(new int[]{2, 4});
    }
}
