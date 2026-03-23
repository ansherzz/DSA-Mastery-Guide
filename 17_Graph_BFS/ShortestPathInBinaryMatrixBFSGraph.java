import java.util.*;

/*
Problem: Shortest Path in Binary Matrix
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given an n x n binary matrix where 0 means open and 1 means blocked, return the length of the shortest clear path
    from the top-left cell to the bottom-right cell. You may move in 8 directions.
    Return -1 if no such path exists.

    This is shortest path in an unweighted grid, so BFS is the right tool.

Why this pattern fits:
Every move has equal cost.
    BFS discovers cells in increasing path length order, so the first time the target is reached is optimal.

Approach:
Start BFS from (0,0) if it is open.
    Push all 8-direction neighbors that are inside the grid, open, and not yet visited.

Worked Example:
Example:
    grid = [[0,1],[1,0]]
    The answer is 2.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class ShortestPathInBinaryMatrixBFSGraph {
    private static final int[][] DIRECTIONS = {
        {1,0}, {-1,0}, {0,1}, {0,-1},
        {1,1}, {1,-1}, {-1,1}, {-1,-1}
    };

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1});
        grid[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int distance = current[2];

            if (r == n - 1 && c == n - 1) {
                return distance;
            }

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc] != 0) {
                    continue;
                }

                grid[nr][nc] = 1;
                queue.offer(new int[]{nr, nc, distance + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}));
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
    }
}
