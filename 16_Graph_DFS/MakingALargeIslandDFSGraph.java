import java.util.*;

/*
Problem: Making A Large Island
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
    Return the size of the largest island in grid after applying this operation.
    An island is a 4-directionally connected group of 1s.

Why this pattern fits:
Trying every 0 and re-running a flood fill costs O(n^4).
    The fix is to do the expensive DFS work once: label every island and record its size.
    After that, each 0 can be evaluated in O(1) by looking at the labels of its four neighbors.

Approach:
Pass 1: DFS over each unlabeled island, writing a unique id (starting from 2) into its cells and storing its size.
    Pass 2: for each 0 cell, collect the distinct island ids among its four neighbors
    (distinct, because the same island may touch the cell from more than one side),
    and add their sizes plus 1 for the flipped cell.
    The answer is the best such total, or the largest existing island if there is no 0 to flip.

Worked Example:
Example:
    grid = [[1, 0],
            [0, 1]]
    Two islands of size 1. Flipping either 0 joins both: 1 + 1 + 1 = 3.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class MakingALargeIslandDFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        Map<Integer, Integer> sizeById = new HashMap<>();
        int nextId = 2;
        int best = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    int size = label(grid, r, c, nextId);
                    sizeById.put(nextId++, size);
                    best = Math.max(best, size);
                }
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != 0) {
                    continue;
                }

                Set<Integer> neighborIds = new HashSet<>();
                for (int[] direction : DIRECTIONS) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];
                    if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] > 1) {
                        neighborIds.add(grid[nr][nc]);
                    }
                }

                int total = 1;
                for (int id : neighborIds) {
                    total += sizeById.get(id);
                }
                best = Math.max(best, total);
            }
        }
        return best;
    }

    private static int label(int[][] grid, int r, int c, int id) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid.length || grid[r][c] != 1) {
            return 0;
        }

        grid[r][c] = id;
        int size = 1;
        for (int[] direction : DIRECTIONS) {
            size += label(grid, r + direction[0], c + direction[1], id);
        }
        return size;
    }

    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{{1, 0}, {0, 1}}));
        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 0}}));
        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 1}}));
        System.out.println(largestIsland(new int[][]{{1, 1, 0}, {0, 0, 0}, {0, 1, 1}}));
    }
}
