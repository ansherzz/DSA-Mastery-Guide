import java.util.*;

/*
Problem: Minimum Number of Vertices to Reach All Nodes
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given a directed acyclic graph, find the smallest set of vertices from which all nodes are reachable.

Why this pattern fits:
A node with an incoming edge can be reached from its predecessor, so it never needs to be chosen.
A node with no incoming edge cannot be reached from anywhere else, so it must be chosen.
In-degree counting - the first step of Kahn's algorithm - already gives the answer.

Approach:
Mark every node that appears as the target of an edge. Return all unmarked nodes.

Worked Example:
n = 6, edges 0->1, 0->2, 2->5, 3->4, 4->2 -> only nodes 0 and 3 have no incoming edges.

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class P11_MinimumNumberOfVerticesToReachAllNodesBFSGraph {
    public static List<Integer> findSmallestSetOfVertices(int n, int[][] edges) {
        boolean[] hasIncoming = new boolean[n];
        for (int[] edge : edges) {
            hasIncoming[edge[1]] = true;
        }
        List<Integer> result = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (!hasIncoming[node]) {
                result.add(node);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 6 -> " + findSmallestSetOfVertices(6, new int[][]{{0, 1}, {0, 2}, {2, 5}, {3, 4}, {4, 2}}));
        System.out.println("n = 5 -> " + findSmallestSetOfVertices(5, new int[][]{{0, 1}, {2, 1}, {3, 1}, {1, 4}, {2, 4}}));
    }
}
