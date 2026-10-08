import java.util.*;

/*
Problem: Number of Distinct Islands
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Two islands are the same if one can be translated (not rotated or reflected) to match the other.
Return the number of distinct island shapes in a binary grid.

Why this pattern fits:
Flood fill finds each island; the extra requirement is a canonical description of its shape.
Because DFS always explores directions in the same order, the path of moves is identical for identical shapes.

Approach:
For each unvisited land cell run DFS, appending a letter for each move direction and a marker when returning.
Add the resulting string to a set. The set's size is the answer.

Worked Example:
Two 2 x 2 blocks in different places produce the same move string, so they count once.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P15_NumberOfDistinctIslandsDFSGraph {
    private static void dfs(int[][] grid, int row, int col, char move, StringBuilder shape) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }
        grid[row][col] = 0;
        shape.append(move);
        dfs(grid, row + 1, col, 'D', shape);
        dfs(grid, row - 1, col, 'U', shape);
        dfs(grid, row, col + 1, 'R', shape);
        dfs(grid, row, col - 1, 'L', shape);
        shape.append('#');
    }

    public static int numDistinctIslands(int[][] grid) {
        Set<String> shapes = new HashSet<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, r, c, 'S', shape);
                    shapes.add(shape.toString());
                }
            }
        }
        return shapes.size();
    }

    public static void main(String[] args) {
        System.out.println("two identical squares -> " + numDistinctIslands(
                new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}));
        System.out.println("four islands, three shapes -> " + numDistinctIslands(
                new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}}));
    }
}
