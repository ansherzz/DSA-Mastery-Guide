import java.util.*;

/*
Problem: Find if Path Exists in Graph
Pattern: 17. Graph BFS
Difficulty: Easy

Interview Prompt:
There is a bi-directional graph with n vertices labeled from 0 to n - 1.
    The edges are given as a 2D array where edges[i] = [u, v] denotes an edge between u and v.
    Determine whether there is a valid path from vertex source to vertex destination.

Why this pattern fits:
This is the most basic reachability question. BFS explores outward from the source in layers
    and visits every reachable vertex exactly once, so it either meets the destination or exhausts the component.

Approach:
Build an adjacency list. Start a queue with the source and mark it visited.
    Repeatedly poll a vertex; if it is the destination, return true.
    Otherwise enqueue every unvisited neighbor, marking it visited at the moment it is enqueued
    so that no vertex enters the queue twice.

Worked Example:
Example:
    n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]]
    Vertices {0, 1, 2} and {3, 4, 5} form two separate components.
    0 -> 2 is reachable, but 0 -> 5 is not.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P02_FindIfPathExistsInGraphBFSGraph {
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) {
                return true;
            }
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        System.out.println("Path 0 -> 2? " + validPath(6, edges, 0, 2));
        System.out.println("Path 0 -> 5? " + validPath(6, edges, 0, 5));
    }
}
