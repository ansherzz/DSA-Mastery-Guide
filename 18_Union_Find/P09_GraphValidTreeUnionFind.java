import java.util.*;

/*
Problem: Graph Valid Tree
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You have a graph of n nodes labeled 0 to n - 1 and a list of undirected edges.
    Return true if these edges make up a valid tree.

Why this pattern fits:
A tree is a connected graph with no cycles. Union Find checks both properties while reading the edges:
    an edge whose endpoints are already in the same set closes a cycle,
    and a tree on n nodes must have exactly n - 1 edges, which then guarantees it is connected.

Approach:
If the number of edges is not n - 1, the graph cannot be a tree.
    Otherwise union the endpoints of each edge. If any union finds both endpoints already connected,
    there is a cycle, so return false. If every union succeeds, the n - 1 merges joined all n nodes into one set.

Worked Example:
Example:
    n = 5, edges = [[0, 1], [0, 2], [0, 3], [1, 4]] -> 4 edges, no cycle. Valid tree.
    n = 5, edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]] -> 5 edges, so it cannot be a tree
    (the edges 1-2, 2-3, 1-3 form a cycle).

Time Complexity: O(E * alpha(V))
Space Complexity: O(V)
*/

public class P09_GraphValidTreeUnionFind {
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

    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        System.out.println(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
        System.out.println(validTree(4, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
    }
}
