import java.util.*;

/*
Problem: Flood Fill
Pattern: 16. Graph DFS
Difficulty: Easy

Interview Prompt:
An image is represented by an m x n integer grid where image[r][c] is the pixel value.
    Given a starting pixel (sr, sc) and a color, perform a flood fill:
    recolor the starting pixel and every pixel connected to it 4-directionally through pixels
    of the same original color. Return the modified image.

Why this pattern fits:
The pixels to recolor are exactly the connected component of the starting pixel
    in a graph where equal-colored neighbors are joined. DFS visits one connected component.

Approach:
Remember the original color of the starting pixel.
    If it already equals the new color, return immediately; otherwise the recursion would never terminate,
    because recolored pixels would still look unvisited.
    DFS from the start: stop at the border or at a pixel of a different color,
    otherwise recolor the pixel and recurse in four directions.
    Recoloring doubles as the visited mark.

Worked Example:
Example:
    image = [[1, 1, 1],        [[2, 2, 2],
             [1, 1, 0],   ->    [2, 2, 0],
             [1, 0, 1]]         [2, 0, 1]]
    Start at (1, 1) with new color 2. The bottom-right 1 is not connected, so it stays.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class FloodFillDFSGraph {
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original != color) {
            dfs(image, sr, sc, original, color);
        }
        return image;
    }

    private static void dfs(int[][] image, int r, int c, int original, int color) {
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] != original) {
            return;
        }

        image[r][c] = color;
        dfs(image, r + 1, c, original, color);
        dfs(image, r - 1, c, original, color);
        dfs(image, r, c + 1, original, color);
        dfs(image, r, c - 1, original, color);
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(Arrays.deepToString(floodFill(image, 1, 1, 2)));

        int[][] same = {{0, 0, 0}, {0, 0, 0}};
        System.out.println(Arrays.deepToString(floodFill(same, 0, 0, 0)));
    }
}
