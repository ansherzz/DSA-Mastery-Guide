import java.util.*;

/*
Problem: Path With Minimum Effort (Union Find)
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
The effort of a route through a grid of heights is the maximum absolute height difference between
consecutive cells. Return the minimum effort needed to go from the top-left cell to the bottom-right cell.

Why this pattern fits:
The answer is the smallest threshold at which the two corners are connected using only edges within the threshold.
Sorting the edges and uniting them one by one finds that threshold directly - Kruskal's idea applied to a path question.

Approach:
Create an edge between every pair of adjacent cells with weight |height difference|. Sort the edges.
Union them in order; as soon as the first and last cells share a root, the current weight is the answer.

Worked Example:
heights = [[1, 2, 2], [3, 8, 2], [5, 3, 5]] -> the route 1, 3, 5, 3, 5 never climbs more than 2.

Time Complexity: O(rows * cols * log(rows * cols))
Space Complexity: O(rows * cols)
*/

public class P20_PathWithMinimumEffortUnionFind {
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

    public static int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cell = r * cols + c;
                if (c + 1 < cols) {
                    edges.add(new int[]{cell, cell + 1, Math.abs(heights[r][c] - heights[r][c + 1])});
                }
                if (r + 1 < rows) {
                    edges.add(new int[]{cell, cell + cols, Math.abs(heights[r][c] - heights[r + 1][c])});
                }
            }
        }
        edges.sort((a, b) -> a[2] - b[2]);
        UnionFind sets = new UnionFind(rows * cols);
        for (int[] edge : edges) {
            sets.union(edge[0], edge[1]);
            if (sets.find(0) == sets.find(rows * cols - 1)) {
                return edge[2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("[[1,2,2],[3,8,2],[5,3,5]] -> " + minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        System.out.println("[[1,2,3],[3,8,4],[5,3,5]] -> " + minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
        System.out.println("5x5 corridor -> " + minimumEffortPath(new int[][]{
                {1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}));
    }
}
