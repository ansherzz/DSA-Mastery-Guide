import java.util.*;

/*
Problem: Is Graph Bipartite? (Union Find)
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
graph[u] lists the neighbors of node u in an undirected graph. Return true if the nodes can be split
into two sets such that every edge connects a node from one set to a node from the other.

Why this pattern fits:
In a bipartite graph, every neighbor of u is on the side opposite to u, so all of u's neighbors share a side.
Uniting them expresses "same side"; the graph fails exactly when a node ends up in the same set as one of its neighbors.

Approach:
For each node u, union all of its neighbors with its first neighbor.
If find(u) equals find(neighbor) for any neighbor, return false.

Worked Example:
A 4-cycle 0-1-2-3 is bipartite ({0, 2} and {1, 3}). Adding the chord 0-2 creates a triangle, which is not.

Time Complexity: O((V + E) * alpha(V))
Space Complexity: O(V)
*/

public class P29_IsGraphBipartiteUnionFind {
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

    public static boolean isBipartite(int[][] graph) {
        UnionFind sets = new UnionFind(graph.length);
        for (int node = 0; node < graph.length; node++) {
            for (int neighbor : graph[node]) {
                if (sets.find(node) == sets.find(neighbor)) {
                    return false;
                }
                sets.union(graph[node][0], neighbor);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("4-cycle with a chord -> " + isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        System.out.println("4-cycle -> " + isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
    }
}
