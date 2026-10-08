import java.util.*;

/*
Problem: Shortest Distance from All Buildings
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
In a grid, 0 is empty land, 1 is a building, and 2 is an obstacle. Find an empty cell that can reach all buildings
with the smallest total travel distance (4-directional moves through empty land). Return that total, or -1.

Why this pattern fits:
The total distance for a cell is a sum over buildings, so each building contributes through its own BFS.
A cell is a valid candidate only if every building's BFS reached it.

Approach:
For each building run a BFS over empty cells, adding the distance to total[r][c] and incrementing reach[r][c].
The answer is the smallest total among cells whose reach equals the number of buildings.

Worked Example:
In the 3 x 5 example with three buildings, the cell (1, 2) has total distance 3 + 3 + 1 = 7.

Time Complexity: O(buildings * rows * cols)
Space Complexity: O(rows * cols)
*/

public class P48_ShortestDistanceFromAllBuildingsBFSGraph {
    public static int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] total = new int[rows][cols];
        int[][] reach = new int[rows][cols];
        int buildings = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int sr = 0; sr < rows; sr++) {
            for (int sc = 0; sc < cols; sc++) {
                if (grid[sr][sc] != 1) {
                    continue;
                }
                buildings++;
                boolean[][] visited = new boolean[rows][cols];
                Deque<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{sr, sc, 0});
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    for (int[] d : directions) {
                        int r = cell[0] + d[0];
                        int c = cell[1] + d[1];
                        if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 0 && !visited[r][c]) {
                            visited[r][c] = true;
                            total[r][c] += cell[2] + 1;
                            reach[r][c]++;
                            queue.add(new int[]{r, c, cell[2] + 1});
                        }
                    }
                }
            }
        }
        int best = Integer.MAX_VALUE;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0 && reach[r][c] == buildings) {
                    best = Math.min(best, total[r][c]);
                }
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    public static void main(String[] args) {
        System.out.println("three buildings -> "
                + shortestDistance(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
        System.out.println("[[1, 0]] -> " + shortestDistance(new int[][]{{1, 0}}));
        System.out.println("[[1]] -> " + shortestDistance(new int[][]{{1}}));
    }
}
