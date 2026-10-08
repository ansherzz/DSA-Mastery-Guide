import java.util.*;

/*
Problem: Count the Number of Complete Components
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given an integer n. There is an undirected graph with n vertices numbered from 0 to n - 1,
    and a 2D array edges where edges[i] = [a, b] denotes an undirected edge between vertices a and b.
    Return the number of complete connected components of the graph.
    A connected component is complete if there is an edge between every pair of its vertices.

Why this pattern fits:
The question needs two facts per component: how many vertices it has and how many edges it has.
    Union-find already tracks the size of each set, and the edges can be tallied per root afterward.

Approach:
Union the endpoints of every edge.
    Then count the edges of each component by adding 1 to the root of each edge's endpoint.
    A component with k vertices is complete exactly when it has k * (k - 1) / 2 edges.
    Check that condition for every root.

Worked Example:
Example:
    n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
    {0, 1, 2} has 3 vertices and 3 edges, {3, 4} has 2 vertices and 1 edge, and {5} has 1 vertex and 0 edges.
    All three are complete. Answer = 3.

Time Complexity: O((V + E) * alpha(V)), effectively linear
Space Complexity: O(V)
*/

public class P33_CountCompleteComponentsUnionFind {
    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
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
            components--;
            return true;
        }
    }

    public static int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        int[] edgeCount = new int[n];
        for (int[] edge : edges) {
            edgeCount[dsu.find(edge[0])]++;
        }

        int complete = 0;
        for (int node = 0; node < n; node++) {
            if (dsu.find(node) == node) {
                int k = dsu.size[node];
                if (edgeCount[node] == k * (k - 1) / 2) {
                    complete++;
                }
            }
        }
        return complete;
    }

    public static void main(String[] args) {
        System.out.println(countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}}));
        System.out.println(countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}}));
    }
}
