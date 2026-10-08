import java.util.*;

/*
Problem: Count Unreachable Pairs of Nodes in an Undirected Graph
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given an undirected graph with n nodes numbered 0 to n - 1 and a list of edges.
    Return the number of pairs of different nodes that are unreachable from each other.

Why this pattern fits:
Two nodes are unreachable from each other exactly when they are in different connected components.
    So only the sizes of the components matter, and Union Find with size tracking provides them.

Approach:
Union the endpoints of every edge.
    Then go through the components one at a time. A component of size s pairs with every node
    not yet accounted for: add s * (nodes remaining after removing this component).
    Use long arithmetic, because the number of pairs can exceed the int range.

Worked Example:
Example:
    n = 7, edges = [[0, 2], [0, 5], [2, 4], [1, 6], [5, 4]]
    Components: {0, 2, 4, 5} of size 4, {1, 6} of size 2, and {3} of size 1.
    Unreachable pairs = 4 * 3 + 2 * 1 = 14.

Time Complexity: O((V + E) * alpha(V))
Space Complexity: O(V)
*/

public class P24_CountUnreachablePairsUnionFind {
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

    public static long countPairs(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        long pairs = 0;
        long remaining = n;
        for (int node = 0; node < n; node++) {
            if (dsu.find(node) == node) {
                long componentSize = dsu.size[node];
                remaining -= componentSize;
                pairs += componentSize * remaining;
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println(countPairs(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println(countPairs(7, new int[][]{{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}}));
        System.out.println(countPairs(100000, new int[][]{}));
    }
}
