import java.util.*;

/*
Problem: Is Graph Bipartite?
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There is an undirected graph with n nodes, where graph[u] is an array of the nodes adjacent to u.
    A graph is bipartite if the nodes can be partitioned into two independent sets A and B
    such that every edge connects a node in A to a node in B.
    Return true if and only if the graph is bipartite. The graph may be disconnected.

Why this pattern fits:
Bipartite means two-colorable. Once one node of a component is colored, every other color in that component is forced:
    each neighbor must take the opposite color. DFS propagates the forced colors and detects a contradiction.

Approach:
Keep a color array with 0 for uncolored and +1 / -1 for the two colors.
    For every uncolored node, start a DFS that colors it +1.
    In the DFS, for each neighbor: if it is uncolored, color it with the opposite color and recurse;
    if it already has the same color as the current node, the graph is not bipartite.
    Starting a DFS from every uncolored node handles disconnected graphs.

Worked Example:
Example:
    graph = [[1, 3], [0, 2], [1, 3], [0, 2]] is a 4-cycle: color 0 and 2 one way, 1 and 3 the other. Bipartite.
    graph = [[1, 2, 3], [0, 2], [0, 1, 3], [0, 2]] contains the triangle 0-1-2, so it is not bipartite.

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class IsGraphBipartiteDFSGraph {
    public static boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        for (int node = 0; node < graph.length; node++) {
            if (color[node] == 0 && !paint(graph, node, 1, color)) {
                return false;
            }
        }
        return true;
    }

    private static boolean paint(int[][] graph, int node, int nodeColor, int[] color) {
        color[node] = nodeColor;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == nodeColor) {
                return false;
            }
            if (color[neighbor] == 0 && !paint(graph, neighbor, -nodeColor, color)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        System.out.println(isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        System.out.println(isBipartite(new int[][]{{}, {2}, {1}}));
    }
}
