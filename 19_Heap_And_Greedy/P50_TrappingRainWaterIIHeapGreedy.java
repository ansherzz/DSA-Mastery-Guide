import java.util.*;

/*
Problem: Trapping Rain Water II
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
Given an m x n height map, return the volume of water it can trap after raining.

Why this pattern fits:
Water in a cell is limited by the lowest point on the best enclosing boundary.
Processing boundary cells from lowest to highest guarantees that when a cell is reached,
the wall it was reached through is the lowest wall that could hold water above it.

Approach:
Push all border cells into a min-heap by height and mark them visited.
Pop the lowest cell; for each unvisited neighbor, it traps max(0, poppedHeight - neighborHeight),
and it is pushed with height max(poppedHeight, neighborHeight).

Worked Example:
The 3 x 6 example traps 4 units; a 5 x 5 bowl with walls of 3, a ring of 2, and a center of 1 traps 8 + 2 = 10.

Time Complexity: O(m * n * log(m * n))
Space Complexity: O(m * n)
*/

public class P50_TrappingRainWaterIIHeapGreedy {
    public static int trapRainWater(int[][] heightMap) {
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || c == 0 || r == rows - 1 || c == cols - 1) {
                    heap.add(new int[]{heightMap[r][c], r, c});
                    visited[r][c] = true;
                }
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int water = 0;
        while (!heap.isEmpty()) {
            int[] cell = heap.poll();
            for (int[] d : directions) {
                int r = cell[1] + d[0];
                int c = cell[2] + d[1];
                if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c]) {
                    continue;
                }
                visited[r][c] = true;
                water += Math.max(0, cell[0] - heightMap[r][c]);
                heap.add(new int[]{Math.max(cell[0], heightMap[r][c]), r, c});
            }
        }
        return water;
    }

    public static void main(String[] args) {
        System.out.println("3x6 map -> " + trapRainWater(
                new int[][]{{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}}));
        System.out.println("5x5 bowl -> " + trapRainWater(new int[][]{
                {3, 3, 3, 3, 3}, {3, 2, 2, 2, 3}, {3, 2, 1, 2, 3}, {3, 2, 2, 2, 3}, {3, 3, 3, 3, 3}}));
    }
}
