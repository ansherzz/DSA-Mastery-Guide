import java.util.*;

/*
Problem: Coloring A Border
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given an m x n integer matrix grid, and three integers row, col, and color.
    Two squares belong to the same connected component if they have the same color and are adjacent 4-directionally.
    The border of a connected component is all the squares in the component that are either
    on the boundary of the grid or adjacent to at least one square not in the component.
    Color the border of the connected component that contains grid[row][col] with color, and return the final grid.

Why this pattern fits:
The component is found with DFS, and whether a cell is on the border depends only on its four neighbors.
    The subtlety is that recoloring during the traversal would corrupt the "same color" test,
    so the traversal and the recoloring must be separated.

Approach:
DFS over the component using a visited array rather than recoloring.
    For each cell, look at the four neighbors: a neighbor that is out of bounds, or that has a different color
    and is not a visited component cell, makes this cell a border cell.
    Collect the border cells in a list, and recolor them only after the DFS has finished.

Worked Example:
Example:
    grid = [[1, 1, 1],        [[2, 2, 2],
            [1, 1, 1],   ->    [2, 1, 2],
            [1, 1, 1]]         [2, 2, 2]]
    Start at (1, 1) with color 2. Every cell except the center touches the grid boundary.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class ColoringABorderDFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        List<int[]> border = new ArrayList<>();
        dfs(grid, row, col, grid[row][col], visited, border);

        for (int[] cell : border) {
            grid[cell[0]][cell[1]] = color;
        }
        return grid;
    }

    private static void dfs(int[][] grid, int r, int c, int original, boolean[][] visited, List<int[]> border) {
        visited[r][c] = true;
        boolean isBorder = false;

        for (int[] direction : DIRECTIONS) {
            int nr = r + direction[0];
            int nc = c + direction[1];
            if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] != original) {
                isBorder = true;
            } else if (!visited[nr][nc]) {
                dfs(grid, nr, nc, original, visited, border);
            }
        }

        if (isBorder) {
            border.add(new int[]{r, c});
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(colorBorder(new int[][]{{1, 1}, {1, 2}}, 0, 0, 3)));
        System.out.println(Arrays.deepToString(colorBorder(new int[][]{{1, 2, 2}, {2, 3, 2}}, 0, 1, 3)));
        System.out.println(Arrays.deepToString(colorBorder(
            new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1, 2)));
    }
}
