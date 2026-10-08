import java.util.*;

/*
Problem: DFS Traversal of a Graph
Pattern: 16. Graph DFS
Difficulty: Easy

Interview Prompt:
Given an undirected graph with n nodes labeled 0..n - 1 and a list of edges,
return the order in which a depth-first search visits the nodes, starting from node 0 and
continuing from the smallest unvisited node whenever the search runs out.

Why this pattern fits:
Every graph DFS problem is this traversal plus some bookkeeping: an adjacency list,
a visited array, and a recursive visit that goes as deep as possible before backing up.

Approach:
Build adjacency lists. For each node in increasing order, if it is unvisited call dfs:
mark it, append it to the order, and recurse into unvisited neighbors.

Worked Example:
n = 5, edges 0-1, 0-2, 1-3, 2-4 -> from 0 go deep through 1 to 3, back up, then 2 and 4: [0, 1, 3, 2, 4].

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P01_DFSTraversalOfGraphDFSGraph {
    private static void dfs(List<List<Integer>> graph, int node, boolean[] visited, List<Integer> order) {
        visited[node] = true;
        order.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, order);
            }
        }
    }

    public static List<Integer> dfsOrder(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        List<Integer> order = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                dfs(graph, node, visited, order);
            }
        }
        return order;
    }

    public static void main(String[] args) {
        System.out.println("connected graph -> " + dfsOrder(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 4}}));
        System.out.println("two components -> " + dfsOrder(4, new int[][]{{0, 1}, {2, 3}}));
    }
}
