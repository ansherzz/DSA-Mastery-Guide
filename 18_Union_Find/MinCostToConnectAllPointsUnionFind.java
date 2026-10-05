import java.util.*;

/*
Problem: Min Cost to Connect All Points
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given points on a 2D plane, where points[i] = [x, y].
    The cost of connecting two points is the Manhattan distance between them: |x1 - x2| + |y1 - y2|.
    Return the minimum total cost to connect all points so that there is exactly one simple path
    between any two points.

Why this pattern fits:
Connecting everything at minimum cost with no redundant connections is a minimum spanning tree.
    Kruskal's algorithm builds one by taking edges from cheapest to most expensive
    and keeping an edge only if it joins two different components. That test is a Union Find operation.

Approach:
Generate every pair of points as an edge with its Manhattan distance.
    Sort the edges by cost. Go through them in order and union the two endpoints.
    When a union succeeds, add the edge's cost to the total.
    Stop once n - 1 edges have been accepted.

Worked Example:
Example:
    points = [[0, 0], [2, 2], [3, 10], [5, 2], [7, 0]]
    Kruskal picks edges of cost 3 ([2,2]-[5,2]), 4 ([0,0]-[2,2]), 4 ([5,2]-[7,0]), and 9 ([2,2]-[3,10]).
    Total = 20.

Time Complexity: O(n^2 log n)
Space Complexity: O(n^2)
*/

public class MinCostToConnectAllPointsUnionFind {
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

    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{cost, i, j});
            }
        }
        edges.sort((a, b) -> Integer.compare(a[0], b[0]));

        DSU dsu = new DSU(n);
        int total = 0;
        int used = 0;

        for (int[] edge : edges) {
            if (dsu.union(edge[1], edge[2])) {
                total += edge[0];
                if (++used == n - 1) {
                    break;
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
        System.out.println(minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}));
        System.out.println(minCostConnectPoints(new int[][]{{0, 0}}));
    }
}
