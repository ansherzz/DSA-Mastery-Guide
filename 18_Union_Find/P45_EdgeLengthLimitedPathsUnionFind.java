import java.util.*;

/*
Problem: Checking Existence of Edge Length Limited Paths
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [u, v, distance]
    denotes an edge between nodes u and v with that distance. There may be multiple edges between two nodes.
    Given an array queries, where queries[j] = [p, q, limit], determine for each query whether there is a path
    between p and q such that each edge on the path has a distance strictly less than limit.
    Return a boolean array with the answer to every query.

Why this pattern fits:
For one query, the answer is whether p and q are connected using only edges shorter than the limit.
    Union-find answers connectivity quickly but cannot remove edges.
    Answering the queries offline in increasing order of limit avoids removal entirely:
    the set of allowed edges only grows from one query to the next.

Approach:
Sort the edges by distance. Sort the query indices by limit.
    Sweep through the queries in that order with an edge pointer:
    before answering a query, union every edge whose distance is strictly less than the query's limit.
    Then the answer is whether p and q have the same root.
    Store each answer at the query's original index.

Worked Example:
Example:
    n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
    Query [0, 1, 2]: no edge is shorter than 2, so false.
    Query [0, 2, 5]: edges of distance 2 and 4 connect 0 - 1 - 2, so true.

Time Complexity: O(E log E + Q log Q + (E + Q) * alpha(V))
Space Complexity: O(V + Q)
*/

public class P45_EdgeLengthLimitedPathsUnionFind {
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

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int[][] edges = edgeList.clone();
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        Integer[] order = new Integer[queries.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a][2], queries[b][2]));

        DSU dsu = new DSU(n);
        boolean[] answer = new boolean[queries.length];
        int nextEdge = 0;
        for (int index : order) {
            int[] query = queries[index];
            while (nextEdge < edges.length && edges[nextEdge][2] < query[2]) {
                dsu.union(edges[nextEdge][0], edges[nextEdge][1]);
                nextEdge++;
            }
            answer[index] = dsu.find(query[0]) == dsu.find(query[1]);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(distanceLimitedPathsExist(3,
            new int[][]{{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}},
            new int[][]{{0, 1, 2}, {0, 2, 5}})));
        System.out.println(Arrays.toString(distanceLimitedPathsExist(5,
            new int[][]{{0, 1, 10}, {1, 2, 5}, {2, 3, 9}, {3, 4, 13}},
            new int[][]{{0, 4, 14}, {1, 4, 13}})));
    }
}
