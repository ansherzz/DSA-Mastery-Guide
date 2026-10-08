import java.util.*;

/*
Problem: Find if Path Exists in Graph using Union Find
Pattern: 18. Union Find
Difficulty: Easy

Interview Prompt:
There is an undirected graph with n vertices labeled 0 to n - 1, given as a list of edges.
    Determine whether there is a valid path from the vertex source to the vertex destination.

    The DFS version is in folder 16. Solve it here with a disjoint set union.

Why this pattern fits:
In an undirected graph, "a path exists between a and b" means exactly "a and b are in the same connected component".
    Union Find builds the components by merging the two endpoints of every edge,
    and then answers the question with two find operations.

Approach:
Create a DSU with n elements. Union the endpoints of every edge.
    Return whether find(source) equals find(destination).
    Path compression and union by size keep every operation nearly constant time.

Worked Example:
Example:
    n = 6, edges = [[0, 1], [0, 2], [3, 5], [5, 4], [4, 3]]
    After all unions the components are {0, 1, 2} and {3, 4, 5}.
    0 and 2 share a root, so a path exists. 0 and 5 do not.

Time Complexity: O((V + E) * alpha(V)), effectively linear
Space Complexity: O(V)
*/

public class P02_FindIfPathExistsUnionFind {
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

    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }
        return dsu.find(source) == dsu.find(destination);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        System.out.println("Path 0 -> 2? " + validPath(6, edges, 0, 2));
        System.out.println("Path 0 -> 5? " + validPath(6, edges, 0, 5));
    }
}
