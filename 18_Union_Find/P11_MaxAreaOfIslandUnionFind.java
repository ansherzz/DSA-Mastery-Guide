import java.util.*;

/*
Problem: Max Area of Island (Union Find)
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Given a binary grid, return the area of the largest island (4-directionally connected group of 1s), or 0.

Why this pattern fits:
Each land cell is a node, and adjacent land cells are in the same set.
With union by size, the area of every island is available as the size stored at its root.

Approach:
Map cell (r, c) to index r * cols + c. For each land cell union it with the land cell to its right
and the one below, tracking the largest set size seen.

Worked Example:
grid = [[0, 0, 1, 0], [1, 1, 1, 0], [0, 0, 0, 1]] -> the island through the middle has 4 cells.

Time Complexity: O(rows * cols * alpha)
Space Complexity: O(rows * cols)
*/

public class P11_MaxAreaOfIslandUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
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
            components--;
            return true;
        }
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind sets = new UnionFind(rows * cols);
        int best = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }
                int cell = r * cols + c;
                if (c + 1 < cols && grid[r][c + 1] == 1) {
                    sets.union(cell, cell + 1);
                }
                if (r + 1 < rows && grid[r + 1][c] == 1) {
                    sets.union(cell, cell + cols);
                }
                best = Math.max(best, sets.size[sets.find(cell)]);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("3x4 grid -> " + maxAreaOfIsland(new int[][]{{0, 0, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}}));
        System.out.println("[[0, 0]] -> " + maxAreaOfIsland(new int[][]{{0, 0}}));
    }
}
