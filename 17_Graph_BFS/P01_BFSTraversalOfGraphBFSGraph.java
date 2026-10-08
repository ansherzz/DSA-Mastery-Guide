import java.util.*;

/*
Problem: BFS Traversal of a Graph
Pattern: 17. Graph BFS
Difficulty: Easy

Interview Prompt:
Given an undirected graph with n nodes and a list of edges, return the order in which
a breadth-first search starting from a given node visits the nodes reachable from it.

Why this pattern fits:
Every graph BFS problem is this loop plus bookkeeping: a queue for the frontier
and a visited mark set at the moment a node is enqueued (not when it is dequeued).

Approach:
Build adjacency lists. Enqueue the start and mark it. Repeatedly dequeue a node, record it,
and enqueue each unvisited neighbor after marking it.

Worked Example:
Edges 0-1, 0-2, 1-3, 2-4 from node 0 -> [0, 1, 2, 3, 4]. From node 3 -> [3, 1, 0, 2, 4].

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P01_BFSTraversalOfGraphBFSGraph {
    public static List<Integer> bfsOrder(int n, int[][] edges, int start) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return order;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}};
        System.out.println("start 0 -> " + bfsOrder(5, edges, 0));
        System.out.println("start 3 -> " + bfsOrder(5, edges, 3));
    }
}
