import java.util.*;

/*
Problem: Is Graph Bipartite?
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given an undirected graph, return true if it is bipartite.
    A graph is bipartite if its nodes can be colored using two colors so that no edge connects two nodes of the same color.

    BFS naturally colors the graph layer by layer.

Why this pattern fits:
Bipartite checking is a coloring problem.
    BFS propagates opposite colors to neighbors and detects conflicts cleanly.

Approach:
For every uncolored connected component, start BFS and color the start node 1.
    Every neighbor should receive the opposite color.
    If a neighbor already has the same color, the graph is not bipartite.

Worked Example:
Example:
    graph = [[1,3],[0,2],[1,3],[0,2]]
    The answer is true.

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class P06_IsGraphBipartiteBFSGraph {
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        for (int start = 0; start < n; start++) {
            if (color[start] != 0) {
                continue;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            color[start] = 1;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : graph[node]) {
                    if (color[neighbor] == 0) {
                        color[neighbor] = -color[node];
                        queue.offer(neighbor);
                    } else if (color[neighbor] == color[node]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isBipartite(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
        System.out.println(isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));
    }
}
