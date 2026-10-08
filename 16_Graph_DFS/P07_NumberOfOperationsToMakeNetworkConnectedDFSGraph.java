import java.util.*;

/*
Problem: Number of Operations to Make Network Connected
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
n computers are connected by cables. You may unplug any cable and reconnect it between any two computers.
Return the minimum number of such moves needed to connect all computers, or -1 if it is impossible.

Why this pattern fits:
Connecting n nodes needs at least n - 1 edges. If that many cables exist, every component beyond the first
can be attached using one spare cable, so the answer depends only on the number of connected components.

Approach:
If connections.length < n - 1 return -1. Count components with DFS and return components - 1.

Worked Example:
n = 6 with 5 cables forming one group of four and two isolated computers -> 3 components, 2 moves.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P07_NumberOfOperationsToMakeNetworkConnectedDFSGraph {
    private static void dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] c : connections) {
            graph.get(c[0]).add(c[1]);
            graph.get(c[1]).add(c[0]);
        }
        boolean[] visited = new boolean[n];
        int components = 0;
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                components++;
                dfs(graph, node, visited);
            }
        }
        return components - 1;
    }

    public static void main(String[] args) {
        System.out.println("n = 4 -> " + makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println("n = 6, five cables -> " + makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println("n = 6, four cables -> " + makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}));
    }
}
