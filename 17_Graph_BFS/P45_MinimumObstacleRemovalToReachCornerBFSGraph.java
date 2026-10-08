import java.util.*;

/*
Problem: Minimum Obstacle Removal to Reach Corner
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
In a grid, 0 is an empty cell and 1 is an obstacle that can be removed.
Return the minimum number of obstacles to remove so you can move from the top-left to the bottom-right corner.

Why this pattern fits:
Edge weights are only 0 or 1. Dijkstra works, but a deque is enough: push 0-cost moves to the front
and 1-cost moves to the back, and the deque stays ordered by distance.

Approach:
dist starts at infinity except the source. Pop from the front; for each neighbor compute dist + grid[neighbor].
If it improves, record it and add the neighbor to the front (cost 0) or to the back (cost 1).

Worked Example:
grid = [[0, 1, 1], [1, 1, 0], [1, 1, 0]] -> two obstacles must be removed.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P45_MinimumObstacleRemovalToReachCornerBFSGraph {
    public static int minimumObstacles(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});
        dist[0][0] = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!deque.isEmpty()) {
            int[] cell = deque.pollFirst();
            for (int[] d : directions) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= rows || c < 0 || c >= cols) {
                    continue;
                }
                int candidate = dist[cell[0]][cell[1]] + grid[r][c];
                if (candidate < dist[r][c]) {
                    dist[r][c] = candidate;
                    if (grid[r][c] == 0) {
                        deque.addFirst(new int[]{r, c});
                    } else {
                        deque.addLast(new int[]{r, c});
                    }
                }
            }
        }
        return dist[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        System.out.println("3x3 -> " + minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
        System.out.println("3x5 -> " + minimumObstacles(new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}}));
    }
}
