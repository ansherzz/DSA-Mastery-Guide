import java.util.*;

/*
Problem: Number of Provinces using Union Find
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Given the same city connectivity matrix as the DFS version, return the number of provinces.
    This time solve it with Union Find.

    This file helps compare traversal-based and DSU-based thinking on the same problem.

Why this pattern fits:
Every direct connection can be treated as a union operation.
    The number of distinct roots at the end equals the number of provinces.

Approach:
Initialize each city as its own set.
    For every isConnected[i][j] = 1, union i and j.
    Count how many indices are their own root after path compression.

Worked Example:
Example:
    isConnected = [[1,0,0],[0,1,0],[0,0,1]]
    The answer is 3.

Time Complexity: O(n^2 * alpha(n))
Space Complexity: O(n)
*/

public class P05_NumberOfProvincesUnionFind {
    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            parent[find(a)] = find(b);
        }
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {1,0,0},
            {0,1,0},
            {0,0,1}
        };
        System.out.println(findCircleNum(graph));
    }
}
