import java.util.*;

/*
Problem: Shortest Path in a Grid with Obstacles Elimination
Pattern: 11. Queue / BFS
Difficulty: Hard

Interview Prompt:
You are given an m x n grid where each cell is either 0 (empty) or 1 (obstacle).
    You can move up, down, left, or right from and to an empty cell in one step.
    Return the minimum number of steps to walk from the upper-left corner to the lower-right corner
    given that you can eliminate at most k obstacles. If no such walk exists, return -1.

Why this pattern fits:
Every step costs 1, so BFS finds the shortest walk.
    The catch is that a cell alone is not a complete state: arriving with more eliminations left is strictly better.
    The BFS state must be (row, column, eliminations remaining).

Approach:
Run BFS level by level over states (row, col, remaining).
    Moving into an obstacle costs one elimination and is only allowed when remaining > 0.
    Instead of a 3D visited array, keep best[row][col], the largest number of eliminations remaining
    with which the cell has been reached. A new arrival is useful only if it has strictly more remaining,
    because BFS guarantees it is not faster than the earlier arrival.
    The first time the target is polled, the current level number is the answer.

Worked Example:
Example:
    grid = [[0,0,0],
            [1,1,0],
            [0,0,0],
            [0,1,1],
            [0,0,0]], k = 1
    Without eliminations the shortest walk takes 10 steps. Eliminating the obstacle at (3, 2) allows a walk of 6 steps.

Time Complexity: O(m * n * k)
Space Complexity: O(m * n * k)
*/

public class P30_ShortestPathWithObstaclesEliminationBFS {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] best = new int[rows][cols];
        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, k});
        best[0][0] = k;

        for (int steps = 0; !queue.isEmpty(); steps++) {
            for (int size = queue.size(); size > 0; size--) {
                int[] state = queue.poll();
                if (state[0] == rows - 1 && state[1] == cols - 1) {
                    return steps;
                }

                for (int[] direction : DIRECTIONS) {
                    int nr = state[0] + direction[0];
                    int nc = state[1] + direction[1];
                    if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                        continue;
                    }

                    int remaining = state[2] - grid[nr][nc];
                    if (remaining > best[nr][nc]) {
                        best[nr][nc] = remaining;
                        queue.offer(new int[]{nr, nc, remaining});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] first = {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}};
        System.out.println(shortestPath(first, 1));
        System.out.println(shortestPath(first, 0));

        int[][] second = {{0, 1, 1}, {1, 1, 1}, {1, 0, 0}};
        System.out.println(shortestPath(second, 1));
    }
}
