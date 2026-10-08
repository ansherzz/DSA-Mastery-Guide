import java.util.*;

/*
Problem: Number of Connected Components in an Undirected Graph
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Given n nodes labeled 0 to n - 1 and a list of undirected edges, return the number of connected components.

    Union Find makes repeated merge operations efficient.

Why this pattern fits:
Start with n separate components.
    Every successful union reduces the component count by one.

Approach:
Initialize component count = n.
    For each edge, union the endpoints if they are not already connected.
    Decrement the component count on each successful merge.

Worked Example:
Example:
    n = 5, edges = [[0,1],[1,2],[3,4]]
    The answer is 2.

Time Complexity: Near O((V + E) * alpha(V))
Space Complexity: O(V)
*/

public class P04_NumberOfConnectedComponentsUnionFind {
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

    public static int countComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int components = n;

        for (int[] edge : edges) {
            if (dsu.union(edge[0], edge[1])) {
                components--;
            }
        }

        return components;
    }

    public static void main(String[] args) {
        System.out.println(countComponents(5, new int[][]{{0,1},{1,2},{3,4}}));
    }
}
