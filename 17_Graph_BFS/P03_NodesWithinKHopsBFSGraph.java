import java.util.*;

/*
Problem: Nodes Within K Hops
Pattern: 17. Graph BFS
Difficulty: Easy

Interview Prompt:
Given an undirected graph, a start node, and an integer k, return all nodes whose shortest distance
from the start is at most k edges, in the order BFS discovers them.

Why this pattern fits:
BFS explores nodes in increasing distance, so "everything within k hops" is exactly the first k + 1 levels.

Approach:
Standard BFS that processes the queue one level at a time. Stop expanding once k levels have been expanded.

Worked Example:
Edges 0-1, 1-2, 2-3, 3-4, 0-5 from node 0 with k = 2 -> level 0: 0; level 1: 1, 5; level 2: 2.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P03_NodesWithinKHopsBFSGraph {
    public static List<Integer> withinKHops(int n, int[][] edges, int start, int k) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        for (int level = 0; level <= k && !queue.isEmpty(); level++) {
            for (int size = queue.size(); size > 0; size--) {
                int node = queue.poll();
                result.add(node);
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {0, 5}};
        System.out.println("k = 2 -> " + withinKHops(6, edges, 0, 2));
        System.out.println("k = 0 -> " + withinKHops(6, edges, 0, 0));
    }
}
