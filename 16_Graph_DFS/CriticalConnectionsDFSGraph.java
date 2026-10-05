import java.util.*;

/*
Problem: Critical Connections in a Network
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
There are n servers numbered 0 to n - 1 connected by undirected connections, forming a connected network.
    A critical connection is a connection that, if removed, makes some server unable to reach some other server.
    Return all critical connections in the network, in any order.

Why this pattern fits:
A critical connection is a bridge: an edge that lies on no cycle.
    Tarjan's algorithm finds all bridges in one DFS by recording when each node was discovered
    and the earliest-discovered node its subtree can reach by using at most one back edge.

Approach:
During DFS give each node a discovery time, and compute low[node]:
    the smallest discovery time reachable from the node's DFS subtree.
    For a tree edge node -> child, after the child returns, update low[node] with low[child].
    If low[child] > discovery[node], the child's subtree cannot reach node or anything above it
    without this edge, so the edge is a bridge. For an already-visited neighbor other than the parent,
    update low[node] with that neighbor's discovery time.

Worked Example:
Example:
    n = 4, connections = [[0, 1], [1, 2], [2, 0], [1, 3]]
    Servers 0, 1, 2 form a cycle, so none of those edges is critical.
    Server 3 hangs off server 1 by a single edge. Answer = [[1, 3]]

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class CriticalConnectionsDFSGraph {
    private static int timer;

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> connection : connections) {
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }

        int[] discovery = new int[n];
        int[] low = new int[n];
        Arrays.fill(discovery, -1);
        timer = 0;

        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, graph, discovery, low, bridges);
        return bridges;
    }

    private static void dfs(int node, int parent, List<List<Integer>> graph, int[] discovery, int[] low,
                            List<List<Integer>> bridges) {
        discovery[node] = low[node] = timer++;

        for (int next : graph.get(node)) {
            if (next == parent) {
                continue;
            }
            if (discovery[next] == -1) {
                dfs(next, node, graph, discovery, low, bridges);
                low[node] = Math.min(low[node], low[next]);
                if (low[next] > discovery[node]) {
                    bridges.add(Arrays.asList(node, next));
                }
            } else {
                low[node] = Math.min(low[node], discovery[next]);
            }
        }
    }

    private static List<List<Integer>> edges(int[][] pairs) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] pair : pairs) {
            list.add(Arrays.asList(pair[0], pair[1]));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(criticalConnections(4, edges(new int[][]{{0, 1}, {1, 2}, {2, 0}, {1, 3}})));
        System.out.println(criticalConnections(2, edges(new int[][]{{0, 1}})));
        System.out.println(criticalConnections(5, edges(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 2}})));
    }
}
