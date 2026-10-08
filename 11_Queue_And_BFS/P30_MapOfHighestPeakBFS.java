import java.util.*;

/*
Problem: Map of Highest Peak
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
isWater[i][j] = 1 marks water cells, which must have height 0. Adjacent cells may differ in height by at most 1.
Assign heights so that the maximum height is as large as possible and return the height matrix.

Why this pattern fits:
The tallest height a cell can have is its distance to the nearest water cell.
Distances to the nearest of many sources come from one BFS that starts with all sources in the queue.

Approach:
Put every water cell in the queue with height 0 and mark all land as unassigned (-1).
Expand in BFS order, giving each unassigned neighbor the current height + 1.

Worked Example:
isWater = [[0, 1], [0, 0]] -> heights [[1, 0], [2, 1]].

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P30_MapOfHighestPeakBFS {
    public static int[][] highestPeak(int[][] isWater) {
        int rows = isWater.length;
        int cols = isWater[0].length;
        int[][] height = new int[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isWater[r][c] == 1) {
                    queue.add(new int[]{r, c});
                } else {
                    height[r][c] = -1;
                }
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : directions) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < rows && c >= 0 && c < cols && height[r][c] == -1) {
                    height[r][c] = height[cell[0]][cell[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return height;
    }

    public static void main(String[] args) {
        System.out.println("[[0, 1], [0, 0]] -> " + Arrays.deepToString(highestPeak(new int[][]{{0, 1}, {0, 0}})));
        System.out.println("[[0, 0, 1], [1, 0, 0], [0, 0, 0]] -> "
                + Arrays.deepToString(highestPeak(new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 0, 0}})));
    }
}
