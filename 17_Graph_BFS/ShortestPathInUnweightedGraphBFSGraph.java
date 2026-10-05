import java.util.*;

/*
Problem: Shortest Path in an Unweighted Graph
Pattern: 17. Graph BFS
Difficulty: Easy

Interview Prompt:
You are given an undirected, unweighted graph with n nodes labeled 0 to n - 1 as a list of edges,
    along with a source node and a destination node.
    Return one shortest path from the source to the destination as a list of nodes,
    or an empty list if the destination cannot be reached.

Why this pattern fits:
When every edge has the same cost, BFS visits nodes in order of their distance from the source.
    The first time a node is reached is therefore along a shortest path.
    Recording which node each node was reached from lets you rebuild that path afterward.

Approach:
Build an adjacency list. Run BFS from the source with a parent array, where parent[x] is the node
    from which x was first discovered. Stop when the destination is dequeued.
    Rebuild the path by following parents from the destination back to the source, then reverse it.

Worked Example:
Example:
    n = 6, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [3, 4], [4, 5]]
    From 0 to 5, BFS reaches 3 at distance 2 (through 1), then 4, then 5.
    One shortest path is [0, 1, 3, 4, 5], which uses 4 edges.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class ShortestPathInUnweightedGraphBFSGraph {
    public static List<Integer> shortestPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) {
                break;
            }
            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = node;
                    queue.offer(next);
                }
            }
        }

        LinkedList<Integer> path = new LinkedList<>();
        if (!visited[destination]) {
            return path;
        }
        for (int node = destination; node != -1; node = parent[node]) {
            path.addFirst(node);
        }
        return path;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println("0 -> 5: " + shortestPath(6, edges, 0, 5));
        System.out.println("2 -> 1: " + shortestPath(6, edges, 2, 1));
        System.out.println("0 -> 6 in a graph with an isolated node 6: " + shortestPath(7, edges, 0, 6));
    }
}
