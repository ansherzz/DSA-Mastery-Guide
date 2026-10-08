import java.util.*;

/*
Problem: Number of Enclaves
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given a binary grid where 0 is a sea cell and 1 is a land cell.
    A move consists of walking from one land cell to a 4-directionally adjacent land cell,
    or walking off the boundary of the grid.

    Return the number of land cells from which it is impossible to walk off the boundary in any number of moves.

Why this pattern fits:
A land cell can escape exactly when its island touches the border.
    Rather than testing each island, erase every island that touches the border with DFS from the border cells.
    Whatever land remains is enclosed.

Approach:
Run DFS from every land cell on the border, turning each reachable land cell into sea.
    After that, count the land cells left in the grid.

Worked Example:
Example:
    grid = 0 0 0 0
           1 0 1 0
           0 1 1 0
           0 0 0 0
    The 1 in the left column is on the border, so it can escape.
    The other three land cells form an island with no border cell. Answer = 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class P12_NumberOfEnclavesDFSGraph {
    public static int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            sink(grid, r, 0);
            sink(grid, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            sink(grid, 0, c);
            sink(grid, rows - 1, c);
        }

        int enclosed = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                enclosed += cell;
            }
        }
        return enclosed;
    }

    private static void sink(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 1) {
            return;
        }

        grid[r][c] = 0;
        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }

    public static void main(String[] args) {
        System.out.println("Enclaves: " + numEnclaves(new int[][]{
            {0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}));
        System.out.println("Enclaves: " + numEnclaves(new int[][]{
            {0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}));
    }
}
