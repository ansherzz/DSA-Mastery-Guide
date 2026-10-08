import java.util.*;

/*
Problem: Detect Cycles in 2D Grid
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
A cycle is a path of length 4 or more that starts and ends at the same cell, moves between adjacent cells
with the same letter, and never moves straight back to the cell it just left. Return true if the grid has a cycle.

Why this pattern fits:
Cells with the same letter form an undirected graph. Undirected cycle detection is DFS with a parent:
an already-visited neighbor other than the parent means two different routes lead to it.

Approach:
For each unvisited cell start dfs(cell, parent). Mark the cell; for each same-letter neighbor that is not the parent,
return true if it is already visited, otherwise recurse.

Worked Example:
The ring of 'a' around the 2 x 2 block of 'b' forms a cycle. Answer = true.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P33_DetectCyclesIn2DGridDFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static boolean dfs(char[][] grid, boolean[][] visited, int row, int col, int fromRow, int fromCol) {
        visited[row][col] = true;
        for (int[] d : DIRECTIONS) {
            int r = row + d[0];
            int c = col + d[1];
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != grid[row][col]) {
                continue;
            }
            if (r == fromRow && c == fromCol) {
                continue;
            }
            if (visited[r][c] || dfs(grid, visited, r, c, row, col)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsCycle(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (!visited[r][c] && dfs(grid, visited, r, c, -1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static char[][] grid(String... rows) {
        char[][] result = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i].toCharArray();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[aaaa, abba, abba, aaaa] -> " + containsCycle(grid("aaaa", "abba", "abba", "aaaa")));
        System.out.println("[ccca, cdcc, ccec, fccc] -> " + containsCycle(grid("ccca", "cdcc", "ccec", "fccc")));
        System.out.println("[abb, bzb, bba] -> " + containsCycle(grid("abb", "bzb", "bba")));
    }
}
