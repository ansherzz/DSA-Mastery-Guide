import java.util.*;

/*
Problem: Making A Large Island (Union Find)
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
You may change at most one 0 to 1 in a binary grid.
Return the size of the largest island that can result.

Why this pattern fits:
Flipping a water cell merges up to four neighboring islands. Knowing each island's size and identity
(its root) lets every water cell be evaluated in constant time.

Approach:
Union adjacent land cells. For each water cell collect the distinct roots of its four neighbors
and sum their sizes plus one. The answer is the best such sum, or the largest existing island if there is no water.

Worked Example:
grid = [[1, 0], [0, 1]] -> flipping either 0 joins both cells: 1 + 1 + 1 = 3.

Time Complexity: O(n^2 * alpha)
Space Complexity: O(n^2)
*/

public class P35_MakingALargeIslandUnionFind {
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

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        UnionFind sets = new UnionFind(n * n);
        int best = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }
                if (c + 1 < n && grid[r][c + 1] == 1) {
                    sets.union(r * n + c, r * n + c + 1);
                }
                if (r + 1 < n && grid[r + 1][c] == 1) {
                    sets.union(r * n + c, (r + 1) * n + c);
                }
                best = Math.max(best, sets.size[sets.find(r * n + c)]);
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    continue;
                }
                Set<Integer> roots = new HashSet<>();
                int total = 1;
                for (int[] d : directions) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        int root = sets.find(nr * n + nc);
                        if (roots.add(root)) {
                            total += sets.size[root];
                        }
                    }
                }
                best = Math.max(best, total);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[[1, 0], [0, 1]] -> " + largestIsland(new int[][]{{1, 0}, {0, 1}}));
        System.out.println("[[1, 1], [1, 0]] -> " + largestIsland(new int[][]{{1, 1}, {1, 0}}));
        System.out.println("[[1, 1], [1, 1]] -> " + largestIsland(new int[][]{{1, 1}, {1, 1}}));
    }
}
