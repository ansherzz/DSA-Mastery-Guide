import java.util.*;

/*
Problem: Bellman-Ford Shortest Paths with Negative Edges
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given a directed graph that may contain negative edge weights, return the shortest distances from a source
to every node, or null if a negative-weight cycle is reachable from the source.

Why this pattern fits:
Dijkstra assumes that a popped node's distance is final, which negative edges break.
Bellman-Ford makes no such assumption: a shortest path has at most V - 1 edges,
so V - 1 rounds of relaxing all edges are enough.

Approach:
Initialize dist[source] = 0. Repeat V - 1 times: for every edge (u, v, w), if dist[u] + w < dist[v], update it.
Then scan the edges once more; any further improvement means a negative cycle.

Worked Example:
The 5-node textbook graph with edges such as 1 -> 4 (-4) and 3 -> 1 (-2) gives distances [0, 2, 7, 4, -2] from node 0.

Time Complexity: O(V * E)
Space Complexity: O(V)
*/

public class P35_BellmanFordShortestPathBFSGraph {
    public static int[] shortestPaths(int n, int[][] edges, int source) {
        int infinity = Integer.MAX_VALUE;
        int[] dist = new int[n];
        Arrays.fill(dist, infinity);
        dist[source] = 0;
        for (int round = 1; round < n; round++) {
            for (int[] edge : edges) {
                if (dist[edge[0]] != infinity && dist[edge[0]] + edge[2] < dist[edge[1]]) {
                    dist[edge[1]] = dist[edge[0]] + edge[2];
                }
            }
        }
        for (int[] edge : edges) {
            if (dist[edge[0]] != infinity && dist[edge[0]] + edge[2] < dist[edge[1]]) {
                return null;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 6}, {0, 2, 7}, {1, 2, 8}, {1, 3, 5}, {1, 4, -4}, {2, 3, -3}, {2, 4, 9},
                {3, 1, -2}, {4, 0, 2}, {4, 3, 7}};
        System.out.println("textbook graph -> " + Arrays.toString(shortestPaths(5, edges, 0)));
        System.out.println("negative cycle -> " + Arrays.toString(
                shortestPaths(3, new int[][]{{0, 1, 1}, {1, 2, -1}, {2, 1, -1}}, 0)));
    }
}
