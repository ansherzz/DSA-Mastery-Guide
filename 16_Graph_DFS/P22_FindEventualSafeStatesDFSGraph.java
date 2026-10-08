import java.util.*;

/*
Problem: Find Eventual Safe States
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There is a directed graph of n nodes, given as an adjacency list.
    A terminal node has no outgoing edges. A node is safe if every possible path starting from it
    leads to a terminal node (in other words, no path from it can get stuck in a cycle).

    Return all safe nodes in ascending order.

Why this pattern fits:
A node is unsafe exactly when it can reach a cycle. The three-state DFS used for cycle detection
    already computes this: a node that finishes without ever meeting a node on the current path is safe,
    and that verdict can be cached so every node is processed once.

Approach:
Use states: 0 = unvisited, 1 = visiting (or known unsafe), 2 = safe.
    DFS from a node: mark it 1, then check every neighbor.
    If a neighbor is in state 1, or its own DFS reports unsafe, return unsafe and leave the node in state 1.
    If all neighbors are safe, mark the node 2.

Worked Example:
Example:
    graph = [[1, 2], [2, 3], [5], [0], [5], [], []]
    Nodes 5 and 6 are terminal. Nodes 2 and 4 lead only to 5.
    Nodes 0, 1, and 3 can enter the cycle 0 -> 1 -> 3 -> 0.
    Safe nodes: [2, 4, 5, 6]

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class P22_FindEventualSafeStatesDFSGraph {
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int[] state = new int[graph.length];
        List<Integer> safe = new ArrayList<>();

        for (int node = 0; node < graph.length; node++) {
            if (isSafe(graph, node, state)) {
                safe.add(node);
            }
        }
        return safe;
    }

    private static boolean isSafe(int[][] graph, int node, int[] state) {
        if (state[node] != 0) {
            return state[node] == 2;
        }

        state[node] = 1;
        for (int next : graph[node]) {
            if (!isSafe(graph, next, state)) {
                return false;
            }
        }
        state[node] = 2;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
        System.out.println(eventualSafeNodes(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}}));
    }
}
