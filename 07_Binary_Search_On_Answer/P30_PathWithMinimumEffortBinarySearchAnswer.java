import java.util.*;

/*
Problem: Path With Minimum Effort
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given a rows x columns grid heights, where heights[r][c] is the height of cell (r, c).
    You start at the top-left cell and want to reach the bottom-right cell, moving up, down, left, or right.
    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Why this pattern fits:
The answer is a threshold on step size. If the destination is reachable using only steps of difference <= E,
    it is also reachable for any larger E. Reachability under a fixed threshold is a plain graph traversal.

Approach:
Binary search the effort limit between 0 and the largest height.
    For each candidate limit, run BFS from the top-left cell, only crossing edges whose height difference
    is at most the limit. If the bottom-right cell is reached, the limit is feasible.
    Keep the smallest feasible limit.

Worked Example:
Example:
    heights = [[1, 2, 2], [3, 8, 2], [5, 3, 5]]
    The route 1 -> 3 -> 5 -> 3 -> 5 has a maximum step of 2, and no route does better. Answer = 2.

Time Complexity: O(rows * cols * log(max height))
Space Complexity: O(rows * cols)
*/

public class P30_PathWithMinimumEffortBinarySearchAnswer {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int minimumEffortPath(int[][] heights) {
        int low = 0;
        int high = 0;
        for (int[] row : heights) {
            for (int height : row) {
                high = Math.max(high, height);
            }
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canReach(heights, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean canReach(int[][] heights, int limit) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            if (cell[0] == rows - 1 && cell[1] == cols - 1) {
                return true;
            }

            for (int[] direction : DIRECTIONS) {
                int nr = cell[0] + direction[0];
                int nc = cell[1] + direction[1];
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols || visited[nr][nc]) {
                    continue;
                }
                if (Math.abs(heights[nr][nc] - heights[cell[0]][cell[1]]) <= limit) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{{7}}));
    }
}
