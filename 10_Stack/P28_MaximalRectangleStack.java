import java.util.*;

/*
Problem: Maximal Rectangle
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Given a binary matrix filled with '0' and '1' characters,
    find the largest rectangle containing only '1's and return its area.

Why this pattern fits:
This reduces to Largest Rectangle in Histogram, which is already in this folder.
    Treat each row as the ground level: the height of a column is the number of consecutive '1's ending at that row.
    Every row then defines a histogram, and the monotonic stack solves each one in linear time.

Approach:
Maintain a heights array. For each row, increase heights[c] when the cell is '1' and reset it to 0 otherwise.
    Run the histogram algorithm on the heights:
    keep indices of increasing heights on a stack; when a lower bar arrives, pop and compute the area
    of the popped bar using the current index and the new top as its right and left boundaries.

Worked Example:
Example:
    matrix = ["10100",
              "10111",
              "11111",
              "10010"]
    After the third row the heights are [3, 1, 3, 2, 2].
    The bars at columns 2..4 support a rectangle of height 2 and width 3, area 6. Answer = 6.

Time Complexity: O(rows * cols)
Space Complexity: O(cols)
*/

public class P28_MaximalRectangleStack {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int[] heights = new int[matrix[0].length];
        int best = 0;

        for (char[] row : matrix) {
            for (int c = 0; c < row.length; c++) {
                heights[c] = row[c] == '1' ? heights[c] + 1 : 0;
            }
            best = Math.max(best, largestRectangleInHistogram(heights));
        }
        return best;
    }

    private static int largestRectangleInHistogram(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;

        for (int i = 0; i <= heights.length; i++) {
            int current = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int height = heights[stack.pop()];
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                best = Math.max(best, height * (i - leftBoundary - 1));
            }
            stack.push(i);
        }
        return best;
    }

    private static char[][] parse(String... rows) {
        char[][] matrix = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = rows[i].toCharArray();
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println("Area: " + maximalRectangle(parse("10100", "10111", "11111", "10010")));
        System.out.println("Area: " + maximalRectangle(parse("0")));
        System.out.println("Area: " + maximalRectangle(parse("111", "111")));
    }
}
