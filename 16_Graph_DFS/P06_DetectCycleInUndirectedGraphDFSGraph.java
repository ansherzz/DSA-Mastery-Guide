import java.util.*;

/*
Problem: Detect Cycle in an Undirected Graph
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given an undirected graph with n nodes and a list of edges (no self-loops or duplicate edges),
return true if the graph contains a cycle.

Why this pattern fits:
In an undirected DFS every edge is seen from both ends, so the edge back to the parent must be ignored.
Any other edge to an already visited node proves a second route between two nodes - a cycle.

Approach:
dfs(node, parent): mark the node. For each neighbor: if it is unvisited, recurse and propagate true;
if it is visited and is not the parent, return true. Run from every unvisited node.

Worked Example:
Edges 0-1, 1-2, 2-0, 2-3 contain the triangle 0-1-2. Answer = true. A simple path 0-1-2-3 has no cycle.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P06_DetectCycleInUndirectedGraphDFSGraph {
    private static boolean dfs(List<List<Integer>> graph, int node, int parent, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(graph, neighbor, node, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        for (int node = 0; node < n; node++) {
            if (!visited[node] && dfs(graph, node, -1, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("triangle with a tail -> " + hasCycle(4, new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}}));
        System.out.println("simple path -> " + hasCycle(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}));
    }
}
