import java.util.*;

/*
Problem: Rotting Oranges
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
In a grid, 0 means empty, 1 means fresh orange, and 2 means rotten orange.
    Every minute, any fresh orange adjacent in four directions to a rotten orange also becomes rotten.
    Return the minimum number of minutes needed to rot all oranges, or -1 if impossible.

    This is a multi-source BFS on a grid.

Why this pattern fits:
All initially rotten oranges begin spreading at the same time.
    That is exactly what multi-source BFS models: a wave expanding from many starting points.

Approach:
Put all rotten orange positions into the queue first.
    Count fresh oranges. Process the grid level by level, where each BFS layer represents one minute.

Worked Example:
Example:
    grid = [[2,1,1],[1,1,0],[0,1,1]]
    The answer is 4.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P10_RottingOrangesBFS {
    private static final int[][] DIRECTIONS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;
        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] dir : DIRECTIONS) {
                    int nr = current[0] + dir[0];
                    int nc = current[1] + dir[1];

                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] != 1) {
                        continue;
                    }

                    grid[nr][nc] = 2;
                    fresh--;
                    queue.offer(new int[]{nr, nc});
                }
            }
            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }

    private static void runDemo(int[][] grid) {
        System.out.println("Minutes: " + orangesRotting(grid));
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{2,1,1},{1,1,0},{0,1,1}});
        runDemo(new int[][]{{2,1,1},{0,1,1},{1,0,1}});
    }
}
