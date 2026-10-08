import java.util.*;

/*
Problem: Number of Islands using BFS
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given a 2D grid of '1's (land) and '0's (water), return the number of islands.
    An island is formed by horizontally or vertically adjacent land cells.

    This version solves the problem with BFS flood fill.

Why this pattern fits:
Each unvisited land cell starts a new connected component.
    BFS marks the entire component before the next island count begins.

Approach:
Scan the grid.
    When you find an unvisited land cell, increment the island count and run BFS to mark every connected land cell.

Worked Example:
Example:
    grid = [
      ['1','1','0','0','0'],
      ['1','1','0','0','0'],
      ['0','0','1','0','0'],
      ['0','0','0','1','1']
    ]
    The answer is 3.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P18_NumberOfIslandsBFS {
    private static final int[][] DIRECTIONS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    bfs(grid, r, c);
                }
            }
        }

        return count;
    }

    private static void bfs(char[][] grid, int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});
        grid[startRow][startCol] = '0';

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : DIRECTIONS) {
                int nr = current[0] + dir[0];
                int nc = current[1] + dir[1];

                if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length || grid[nr][nc] != '1') {
                    continue;
                }

                grid[nr][nc] = '0';
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };

        System.out.println("Islands: " + numIslands(grid));
    }
}
