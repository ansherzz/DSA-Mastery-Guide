import java.util.*;

/*
Problem: Bricks Falling When Hit
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
A brick is stable if it is in the top row or adjacent to a stable brick. hits[i] erases the brick at that cell (if any),
and bricks that are no longer stable fall immediately. Return the number of bricks that fall after each hit.

Why this pattern fits:
Erasing bricks splits components, which union find cannot do. Reversed in time, each hit adds a brick back,
and the bricks that "fell" are exactly the ones that newly become connected to the roof.

Approach:
Copy the grid with all hits removed and union the remaining bricks, linking top-row bricks to a virtual roof node.
Process hits backwards: if the cell originally had a brick, restore it, union it with its neighbors (and the roof if in row 0),
and record max(0, roofSizeAfter - roofSizeBefore - 1).

Worked Example:
grid = [[1, 0, 0, 0], [1, 1, 1, 0]], hit (1, 0) -> the two bricks to its right lose their support. Answer = [2].
Hits (1, 1) then (1, 0) -> the first hit drops the brick at (1, 2); the second drops nothing. Answer = [1, 0].

Time Complexity: O((rows * cols + hits) * alpha)
Space Complexity: O(rows * cols)
*/

public class P49_BricksFallingWhenHitUnionFind {
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

    private static void connect(UnionFind sets, int[][] grid, int r, int c, int roof) {
        int cols = grid[0].length;
        int cell = r * cols + c;
        if (r == 0) {
            sets.union(cell, roof);
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                sets.union(cell, nr * cols + nc);
            }
        }
    }

    public static int[] hitBricks(int[][] grid, int[][] hits) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] remaining = new int[rows][];
        for (int r = 0; r < rows; r++) {
            remaining[r] = grid[r].clone();
        }
        for (int[] hit : hits) {
            remaining[hit[0]][hit[1]] = 0;
        }
        int roof = rows * cols;
        UnionFind sets = new UnionFind(rows * cols + 1);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (remaining[r][c] == 1) {
                    connect(sets, remaining, r, c, roof);
                }
            }
        }
        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0];
            int c = hits[i][1];
            if (grid[r][c] == 0) {
                continue;
            }
            int before = sets.size[sets.find(roof)];
            remaining[r][c] = 1;
            connect(sets, remaining, r, c, roof);
            int after = sets.size[sets.find(roof)];
            result[i] = Math.max(0, after - before - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 0}};
        System.out.println("hit (1, 0) -> " + Arrays.toString(hitBricks(grid, new int[][]{{1, 0}})));
        System.out.println("hits (1, 1), (1, 0) -> " + Arrays.toString(hitBricks(grid, new int[][]{{1, 1}, {1, 0}})));
    }
}
