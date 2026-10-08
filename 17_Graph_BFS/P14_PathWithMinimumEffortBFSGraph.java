import java.util.*;

/*
Problem: Path With Minimum Effort
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are a hiker on a rows x columns map where heights[r][c] is the height of a cell.
    You start at the top-left cell and want to reach the bottom-right cell, moving up, down, left, or right.
    The effort of a route is the maximum absolute height difference between two consecutive cells on the route.

    Return the minimum effort required to travel from the top-left to the bottom-right.

Why this pattern fits:
This is a shortest-path problem with an unusual path cost: the maximum edge along the path instead of the sum.
    Dijkstra's algorithm (BFS with a priority queue) still works, because extending a path can never make
    its cost smaller. Only the way a path's cost is combined with a new edge changes.

Approach:
Keep the best known effort for every cell, starting at 0 for the top-left.
    Use a min-heap ordered by effort. Pop the cell with the smallest effort;
    when the bottom-right cell is popped, its effort is final.
    For each neighbor, the new effort is max(current effort, height difference). Push it if it improves the neighbor.

Worked Example:
Example:
    heights = 1 2 2
              3 8 2
              5 3 5
    The route 1 -> 3 -> 5 -> 3 -> 5 has differences 2, 2, 2, 2, so its effort is 2.
    The route along the top row and right column includes a jump of 3 (2 -> 5), so it is worse.

Time Complexity: O(m * n * log(m * n))
Space Complexity: O(m * n)
*/

public class P14_PathWithMinimumEffortBFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] effort = new int[rows][cols];
        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        effort[0][0] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        heap.offer(new int[]{0, 0, 0});

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int r = current[0];
            int c = current[1];
            int currentEffort = current[2];

            if (r == rows - 1 && c == cols - 1) {
                return currentEffort;
            }
            if (currentEffort > effort[r][c]) {
                continue;
            }

            for (int[] direction : DIRECTIONS) {
                int nr = r + direction[0];
                int nc = c + direction[1];
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                    continue;
                }

                int nextEffort = Math.max(currentEffort, Math.abs(heights[nr][nc] - heights[r][c]));
                if (nextEffort < effort[nr][nc]) {
                    effort[nr][nc] = nextEffort;
                    heap.offer(new int[]{nr, nc, nextEffort});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{
            {1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}));
    }
}
