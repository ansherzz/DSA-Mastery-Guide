import java.util.*;

/*
Problem: Regions Cut By Slashes
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
An n x n grid is composed of 1 x 1 squares, where each square contains '/', '\', or a blank space ' '.
    These characters divide the grid into contiguous regions.
    Given the grid as an array of strings, return the number of regions.
    Note that a backslash is escaped in source code, so it is written as "\\".

Why this pattern fits:
The regions are connected components, but the cells themselves are too coarse because a slash splits a cell.
    Cutting every cell into four triangles gives pieces that a slash never splits,
    and then counting regions is just counting components with union-find.

Approach:
Split each cell into four triangles: 0 = top, 1 = right, 2 = bottom, 3 = left.
    Inside a cell: a blank joins all four; '/' joins top with left and right with bottom;
    '\' joins top with right and bottom with left.
    Between cells: a cell's bottom triangle always joins the top triangle of the cell below,
    and its right triangle always joins the left triangle of the cell to the right.
    The number of remaining components is the number of regions.

Worked Example:
Example:
    grid = [" /",
            "/ "]
    The two slashes form one diagonal line across the grid, cutting it into 2 regions.

Time Complexity: O(n^2 * alpha(n^2)), effectively linear in the number of cells
Space Complexity: O(n^2)
*/

public class P21_RegionsCutBySlashesUnionFind {
    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
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
            components--;
            return true;
        }
    }

    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;
        DSU dsu = new DSU(4 * n * n);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int base = 4 * (r * n + c);
                char ch = grid[r].charAt(c);

                if (ch == '/') {
                    dsu.union(base, base + 3);
                    dsu.union(base + 1, base + 2);
                } else if (ch == '\\') {
                    dsu.union(base, base + 1);
                    dsu.union(base + 2, base + 3);
                } else {
                    dsu.union(base, base + 1);
                    dsu.union(base + 1, base + 2);
                    dsu.union(base + 2, base + 3);
                }

                if (r + 1 < n) {
                    dsu.union(base + 2, 4 * ((r + 1) * n + c));
                }
                if (c + 1 < n) {
                    dsu.union(base + 1, 4 * (r * n + c + 1) + 3);
                }
            }
        }
        return dsu.components;
    }

    public static void main(String[] args) {
        System.out.println(regionsBySlashes(new String[]{" /", "/ "}));
        System.out.println(regionsBySlashes(new String[]{" /", "  "}));
        System.out.println(regionsBySlashes(new String[]{"/\\", "\\/"}));
    }
}
