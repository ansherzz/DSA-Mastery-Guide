import java.util.*;

/*
Problem: Connecting Cities With Minimum Cost
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
There are n cities (1-indexed) and connections [city1, city2, cost].
Return the minimum cost to connect all cities, or -1 if it is impossible.

Why this pattern fits:
This is the minimum spanning tree problem. Kruskal's greedy rule needs one fast test per edge -
"are these two cities already connected?" - which is exactly a union-find query.

Approach:
Sort connections by cost. For each one, union its endpoints; if the union succeeds add its cost.
Stop when n - 1 edges are used. If fewer were used, the cities cannot all be connected.

Worked Example:
n = 3, connections (1,2,5), (1,3,6), (2,3,1) -> take cost 1 and cost 5. Answer = 6.

Time Complexity: O(E log E)
Space Complexity: O(n)
*/

public class P19_ConnectingCitiesWithMinimumCostUnionFind {
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

    public static int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        UnionFind sets = new UnionFind(n + 1);
        int total = 0;
        int used = 0;
        for (int[] connection : connections) {
            if (sets.union(connection[0], connection[1])) {
                total += connection[2];
                used++;
            }
        }
        return used == n - 1 ? total : -1;
    }

    public static void main(String[] args) {
        System.out.println("n = 3 -> " + minimumCost(3, new int[][]{{1, 2, 5}, {1, 3, 6}, {2, 3, 1}}));
        System.out.println("n = 4 -> " + minimumCost(4, new int[][]{{1, 2, 3}, {3, 4, 4}}));
    }
}
