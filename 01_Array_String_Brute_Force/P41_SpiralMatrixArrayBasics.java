import java.util.*;

/*
Problem: Spiral Matrix
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an m x n matrix, return all of its elements in spiral order:
    start at the top-left, move right, then down, then left, then up, and keep spiraling inward.

Why this pattern fits:
There is no trick to find here. The task is a careful simulation with four boundaries,
    which is exactly the kind of index discipline that array fundamentals are meant to build.

Approach:
Maintain four boundaries: top, bottom, left, right.
    Walk the top row left to right, then the right column top to bottom.
    If rows remain, walk the bottom row right to left. If columns remain, walk the left column bottom to top.
    Shrink the boundary after each walk and repeat.

Worked Example:
Example:
    matrix = [[1, 2, 3],
              [4, 5, 6],
              [7, 8, 9]]
    Spiral order = [1, 2, 3, 6, 9, 8, 7, 4, 5]

Time Complexity: O(m * n)
Space Complexity: O(1) extra, excluding the output list
*/

public class P41_SpiralMatrixArrayBasics {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix.length == 0) {
            return order;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) {
                order.add(matrix[top][col]);
            }
            top++;

            for (int row = top; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            right--;

            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    order.add(matrix[bottom][col]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    order.add(matrix[row][left]);
                }
                left++;
            }
        }
        return order;
    }

    private static void runDemo(int[][] matrix) {
        System.out.println("Matrix : " + Arrays.deepToString(matrix));
        System.out.println("Spiral : " + spiralOrder(matrix));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        runDemo(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        runDemo(new int[][]{{1}, {2}, {3}});
    }
}
