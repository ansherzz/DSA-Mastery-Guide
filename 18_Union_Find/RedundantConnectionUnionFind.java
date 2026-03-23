import java.util.*;

/*
Problem: Redundant Connection
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given edges of a graph that started as a tree with one extra edge added.
    Return the edge that can be removed so the graph becomes a tree again.

    In an undirected graph, an edge is redundant if it connects two nodes already in the same component.

Why this pattern fits:
Union Find shines when edges arrive one by one and you need fast connectivity checks.

Approach:
Initialize each node as its own parent.
    For each edge, if the two endpoints already have the same root, that edge is redundant.
    Otherwise union their sets.

Worked Example:
Example:
    edges = [[1,2],[1,3],[2,3]]
    The redundant edge is [2,3].

Time Complexity: Near O(n * alpha(n))
Space Complexity: O(n)
*/

public class RedundantConnectionUnionFind {
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
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

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
            return true;
        }
    }

    public static int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}})));
    }
}
