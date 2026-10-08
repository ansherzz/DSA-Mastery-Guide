import java.util.*;

/*
Problem: Number of Islands II
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
An m x n grid starts completely filled with water.
    You are given a list of positions, and one at a time each position is turned into land.
    Return a list with the number of islands after each operation.
    An island is a group of land cells connected horizontally or vertically.

Why this pattern fits:
Recounting islands with DFS after every addition costs O(m * n) per operation.
    This is a dynamic connectivity problem where connections are only ever added,
    which is the exact situation Union Find is designed for: each new land cell is one new component,
    and each merge with a neighboring island removes one.

Approach:
Keep a DSU over all cells and a table of which cells are land.
    For each position: if it is already land, the count does not change.
    Otherwise mark it as land and increase the count by one.
    Then for each of its four neighbors that is land, union the two cells and decrease the count
    if the union actually merged two different components. Record the count.

Worked Example:
Example:
    m = 3, n = 3, positions = [[0, 0], [0, 1], [1, 2], [2, 1]]
    Add (0, 0): 1 island. Add (0, 1): joins the first, still 1.
    Add (1, 2): no land neighbor, 2 islands. Add (2, 1): no land neighbor, 3 islands.
    Answer = [1, 1, 2, 3]

Time Complexity: O(k * alpha(m * n)) for k positions, after O(m * n) setup
Space Complexity: O(m * n)
*/

public class P24_NumberOfIslandsIIUnionFind {
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

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);
        boolean[][] land = new boolean[m][n];
        List<Integer> counts = new ArrayList<>();
        int islands = 0;

        for (int[] position : positions) {
            int r = position[0];
            int c = position[1];

            if (!land[r][c]) {
                land[r][c] = true;
                islands++;

                for (int[] direction : DIRECTIONS) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n || !land[nr][nc]) {
                        continue;
                    }
                    if (dsu.union(r * n + c, nr * n + nc)) {
                        islands--;
                    }
                }
            }
            counts.add(islands);
        }
        return counts;
    }

    public static void main(String[] args) {
        System.out.println(numIslands2(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}}));
        System.out.println(numIslands2(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}, {1, 1}, {1, 1}}));
        System.out.println(numIslands2(1, 1, new int[][]{{0, 0}}));
    }
}
