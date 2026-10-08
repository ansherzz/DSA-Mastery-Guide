import java.util.*;

/*
Problem: Flood Fill
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
An image is represented by an m x n grid of integers, where image[r][c] is the color of a pixel.
    Starting from the pixel (sr, sc), perform a flood fill with a new color:
    recolor the starting pixel and every pixel connected to it 4-directionally through pixels of the same original color.

    Return the modified image.

Why this pattern fits:
The pixels to recolor form one connected region, and a queue-based BFS spreads outward from the start
    through neighbors, which is exactly how a paint-bucket tool works.

Approach:
Remember the original color of the starting pixel. If it already equals the new color, nothing needs to change.
    Otherwise recolor the start and push it onto a queue.
    Repeatedly poll a pixel and check its four neighbors; any neighbor with the original color is recolored and enqueued.
    Recoloring at enqueue time doubles as the visited marker.

Worked Example:
Example:
    image = [[1, 1, 1],
             [1, 1, 0],
             [1, 0, 1]], start = (1, 1), color = 2
    Result = [[2, 2, 2],
              [2, 2, 0],
              [2, 0, 1]]
    The bottom-right 1 is not connected 4-directionally, so it stays.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P06_FloodFillBFS {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) {
            return image;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        image[sr][sc] = color;
        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : DIRECTIONS) {
                int row = cell[0] + direction[0];
                int col = cell[1] + direction[1];

                if (row < 0 || col < 0 || row >= image.length || col >= image[0].length) {
                    continue;
                }
                if (image[row][col] != original) {
                    continue;
                }
                image[row][col] = color;
                queue.offer(new int[]{row, col});
            }
        }
        return image;
    }

    private static void print(int[][] image) {
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("Before:");
        print(image);
        System.out.println("After filling from (1, 1) with color 2:");
        print(floodFill(image, 1, 1, 2));
    }
}
