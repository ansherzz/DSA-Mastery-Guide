import java.util.*;

/*
Problem: Swim in Rising Water (Dijkstra on the Bottleneck)
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
grid[i][j] is an elevation. At time t you can move between adjacent cells whose elevations are both at most t.
Return the least time needed to travel from the top-left to the bottom-right.

Why this pattern fits:
The cost of a path is its highest cell, a "bottleneck" cost. Dijkstra still works because
extending a path can never lower its cost: replace dist + weight by max(dist, elevation).

Approach:
Min-heap keyed by the bottleneck so far. Pop the best cell; if it is the target return its key.
Push each unvisited neighbor with key max(current key, neighbor elevation).

Worked Example:
grid = [[0, 2], [1, 3]] -> any path ends at elevation 3. Answer = 3.

Time Complexity: O(n^2 log n)
Space Complexity: O(n^2)
*/

public class P37_SwimInRisingWaterDijkstraBFSGraph {
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            if (current[1] == n - 1 && current[2] == n - 1) {
                return current[0];
            }
            for (int[] d : directions) {
                int r = current[1] + d[0];
                int c = current[2] + d[1];
                if (r >= 0 && r < n && c >= 0 && c < n && !visited[r][c]) {
                    visited[r][c] = true;
                    heap.add(new int[]{Math.max(current[0], grid[r][c]), r, c});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("[[0, 2], [1, 3]] -> " + swimInWater(new int[][]{{0, 2}, {1, 3}}));
        int[][] spiral = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        System.out.println("5x5 spiral -> " + swimInWater(spiral));
    }
}
