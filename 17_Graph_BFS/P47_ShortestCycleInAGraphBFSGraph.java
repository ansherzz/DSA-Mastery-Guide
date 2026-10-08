import java.util.*;

/*
Problem: Shortest Cycle in a Graph
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
Given an undirected graph, return the length of its shortest cycle, or -1 if it has none.

Why this pattern fits:
BFS from a start node builds a shortest-path tree. Any edge that reaches an already visited node other than the parent
closes a cycle through the tree. Trying every node as the start guarantees the shortest cycle is found.

Approach:
For each start: BFS with dist and parent arrays. For an edge (u, v): if v is unvisited, set its distance and parent;
otherwise, if v is not u's parent, update the answer with dist[u] + dist[v] + 1.

Worked Example:
Two components, a triangle and a square -> the triangle gives the shortest cycle, length 3.

Time Complexity: O(V * (V + E))
Space Complexity: O(V + E)
*/

public class P47_ShortestCycleInAGraphBFSGraph {
    public static int findShortestCycle(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int best = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            int[] dist = new int[n];
            int[] parent = new int[n];
            Arrays.fill(dist, -1);
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            dist[start] = 0;
            parent[start] = -1;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : graph.get(node)) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[node] + 1;
                        parent[neighbor] = node;
                        queue.add(neighbor);
                    } else if (neighbor != parent[node]) {
                        best = Math.min(best, dist[node] + dist[neighbor] + 1);
                    }
                }
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    public static void main(String[] args) {
        System.out.println("triangle and square -> " + findShortestCycle(7,
                new int[][]{{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 6}, {6, 3}}));
        System.out.println("tree -> " + findShortestCycle(4, new int[][]{{0, 1}, {0, 2}}));
    }
}
