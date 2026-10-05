import java.util.*;

/*
Problem: Swim in Rising Water
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
You are given an n x n grid where grid[r][c] is the elevation of that cell,
    and all elevations are distinct values from 0 to n * n - 1.
    Rain starts to fall, and at time t the water depth everywhere is t.
    You can swim between two 4-directionally adjacent cells only when both elevations are at most t.
    Swimming takes no time.

    Return the least time at which you can reach the bottom-right cell from the top-left cell.

Why this pattern fits:
As time passes, more and more cells become usable, and usable neighbors become connected.
    Cells are only ever added, never removed, so this is incremental connectivity.
    Add cells in order of elevation and stop the moment the start and the end fall into the same component.

Approach:
Because elevations are a permutation of 0 .. n * n - 1, record the position of each elevation.
    For t = 0, 1, 2, ...: activate the cell with elevation t and union it with every neighbor
    whose elevation is smaller than t (those are already active).
    After each activation, check whether the top-left and bottom-right cells share a root.

Worked Example:
Example:
    grid = 0 2
           1 3
    At t = 0, 1, and 2 the bottom-right cell (elevation 3) is still above water level.
    At t = 3 it becomes usable and connects to its neighbors. Answer = 3.

Time Complexity: O(n^2 * alpha(n^2))
Space Complexity: O(n^2)
*/

public class SwimInRisingWaterUnionFind {
    static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        int[] cellAtElevation = new int[n * n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                cellAtElevation[grid[r][c]] = r * n + c;
            }
        }

        DSU dsu = new DSU(n * n);
        for (int time = 0; time < n * n; time++) {
            int cell = cellAtElevation[time];
            int r = cell / n;
            int c = cell % n;

            for (int[] direction : DIRECTIONS) {
                int nr = r + direction[0];
                int nc = c + direction[1];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                    continue;
                }
                if (grid[nr][nc] < time) {
                    dsu.union(cell, nr * n + nc);
                }
            }

            if (dsu.find(0) == dsu.find(n * n - 1)) {
                return time;
            }
        }
        return n * n - 1;
    }

    public static void main(String[] args) {
        System.out.println(swimInWater(new int[][]{{0, 2}, {1, 3}}));
        System.out.println(swimInWater(new int[][]{
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}}));
        System.out.println(swimInWater(new int[][]{{0}}));
    }
}
