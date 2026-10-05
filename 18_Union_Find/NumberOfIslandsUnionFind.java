import java.util.*;

/*
Problem: Number of Islands using Union Find
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Given an m x n grid of '1's (land) and '0's (water), return the number of islands.
    An island is a group of land cells connected horizontally or vertically.

    The BFS and DFS versions are in folders 11 and 16. Solve it here with a disjoint set union.

Why this pattern fits:
Islands are connected components of land cells. Union Find counts components directly:
    start with one component per land cell and subtract one every time two different components are merged.

Approach:
Give each cell the id row * cols + col. Count the land cells; that is the initial number of components.
    For each land cell, try to union it with the land cell to its right and the land cell below it.
    Checking only two directions is enough, because every adjacent pair is seen once.
    Each successful union reduces the component count by one.

Worked Example:
Example:
    grid = 1 1 0 0 0
           1 1 0 0 0
           0 0 1 0 0
           0 0 0 1 1
    There are 7 land cells. Four unions succeed (three inside the top-left block and one in the bottom-right pair).
    7 - 4 = 3 islands.

Time Complexity: O(m * n * alpha(m * n))
Space Complexity: O(m * n)
*/

public class NumberOfIslandsUnionFind {
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

    public static int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        DSU dsu = new DSU(rows * cols);
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != '1') {
                    continue;
                }
                int id = r * cols + c;
                if (c + 1 < cols && grid[r][c + 1] == '1' && dsu.union(id, id + 1)) {
                    islands--;
                }
                if (r + 1 < rows && grid[r + 1][c] == '1' && dsu.union(id, id + cols)) {
                    islands--;
                }
            }
        }
        return islands;
    }

    private static char[][] parse(String... rows) {
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            grid[i] = rows[i].toCharArray();
        }
        return grid;
    }

    public static void main(String[] args) {
        System.out.println(numIslands(parse("11110", "11010", "11000", "00000")));
        System.out.println(numIslands(parse("11000", "11000", "00100", "00011")));
    }
}
