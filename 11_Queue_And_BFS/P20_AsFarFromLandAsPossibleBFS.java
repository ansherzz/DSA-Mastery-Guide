import java.util.*;

/*
Problem: As Far from Land as Possible
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given an n x n grid containing only 0 (water) and 1 (land), find a water cell whose distance
    to the nearest land cell is as large as possible, and return that distance.
    Distance is the Manhattan distance |r1 - r2| + |c1 - c2|.
    If the grid has no land or no water, return -1.

Why this pattern fits:
"Distance to the nearest land" for every water cell is a multi-source shortest path question.
    Starting BFS from all land cells at once fills the water in rings of increasing distance,
    and the last ring reached holds the farthest water cell.

Approach:
Enqueue every land cell. If the queue is empty or covers the whole grid, return -1.
    Expand level by level, turning each newly reached water cell into visited land and enqueueing it.
    Count the levels; the number of expansion rounds that reached new water is the answer.

Worked Example:
Example:
    grid = [[1, 0, 1],
            [0, 0, 0],
            [1, 0, 1]]
    The center cell is 2 steps from every corner, and no water cell is farther. Answer = 2.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P20_AsFarFromLandAsPossibleBFS {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int maxDistance(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    visited[r][c] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int distance = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    int row = cell[0] + direction[0];
                    int col = cell[1] + direction[1];

                    if (row < 0 || col < 0 || row >= n || col >= n || visited[row][col]) {
                        continue;
                    }
                    visited[row][col] = true;
                    queue.offer(new int[]{row, col});
                }
            }
        }
        return distance;
    }

    private static void runDemo(int[][] grid) {
        System.out.println("Grid     : " + Arrays.deepToString(grid));
        System.out.println("Distance : " + maxDistance(grid));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
        runDemo(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        runDemo(new int[][]{{1, 1}, {1, 1}});
    }
}
