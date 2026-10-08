import java.util.*;

/*
Problem: Check if There is a Valid Path in a Grid
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Each cell contains a street piece: 1 left-right, 2 up-down, 3 left-down, 4 right-down, 5 left-up, 6 right-up.
Return true if there is a path along the streets from the top-left cell to the bottom-right cell.

Why this pattern fits:
Adjacency alone is not enough here; a connection exists only if both pieces open toward each other.
Once those valid connections are unions, the question is a single connectivity query.

Approach:
For each cell, if its piece opens to the right and the right neighbor's piece opens to the left, union them;
do the same downward. Finally compare the roots of the first and last cells.

Worked Example:
grid = [[2, 4, 3], [6, 5, 2]] -> the streets form a continuous route from (0, 0) to (1, 2).

Time Complexity: O(rows * cols * alpha)
Space Complexity: O(rows * cols)
*/

public class P31_CheckIfThereIsAValidPathInAGridUnionFind {
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

    private static boolean opens(int piece, String direction) {
        switch (direction) {
            case "left": return piece == 1 || piece == 3 || piece == 5;
            case "right": return piece == 1 || piece == 4 || piece == 6;
            case "up": return piece == 2 || piece == 5 || piece == 6;
            default: return piece == 2 || piece == 3 || piece == 4;
        }
    }

    public static boolean hasValidPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind sets = new UnionFind(rows * cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cell = r * cols + c;
                if (c + 1 < cols && opens(grid[r][c], "right") && opens(grid[r][c + 1], "left")) {
                    sets.union(cell, cell + 1);
                }
                if (r + 1 < rows && opens(grid[r][c], "down") && opens(grid[r + 1][c], "up")) {
                    sets.union(cell, cell + cols);
                }
            }
        }
        return sets.find(0) == sets.find(rows * cols - 1);
    }

    public static void main(String[] args) {
        System.out.println("[[2, 4, 3], [6, 5, 2]] -> " + hasValidPath(new int[][]{{2, 4, 3}, {6, 5, 2}}));
        System.out.println("[[1, 2, 1], [1, 2, 1]] -> " + hasValidPath(new int[][]{{1, 2, 1}, {1, 2, 1}}));
        System.out.println("[[1, 1, 2]] -> " + hasValidPath(new int[][]{{1, 1, 2}}));
    }
}
