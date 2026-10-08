import java.util.*;

/*
Problem: Find Eventual Safe States (Reverse Kahn)
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
A node is safe if every path starting from it ends at a terminal node (a node with no outgoing edges).
Return all safe nodes in ascending order.

Why this pattern fits:
Terminal nodes are safe, and a node becomes safe once all of its successors are safe.
That is Kahn's algorithm on the reversed graph, using out-degree in place of in-degree.
Nodes on or leading into a cycle never reach out-degree zero.

Approach:
Build reverse adjacency lists and each node's out-degree. Queue nodes with out-degree 0.
Pop a node, mark it safe, and decrement the out-degree of its predecessors, queueing those that reach 0.

Worked Example:
graph = [[1, 2], [2, 3], [5], [0], [5], [], []] -> nodes 5 and 6 are terminal; 2 and 4 lead only to 5. Answer = [2, 4, 5, 6].

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P25_FindEventualSafeStatesKahnBFSGraph {
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverse = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reverse.add(new ArrayList<>());
        }
        int[] outdegree = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < n; node++) {
            outdegree[node] = graph[node].length;
            for (int next : graph[node]) {
                reverse.get(next).add(node);
            }
            if (outdegree[node] == 0) {
                queue.add(node);
            }
        }
        boolean[] safe = new boolean[n];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safe[node] = true;
            for (int previous : reverse.get(node)) {
                if (--outdegree[previous] == 0) {
                    queue.add(previous);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (safe[node]) {
                result.add(node);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("7 nodes -> " + eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
        System.out.println("5 nodes -> " + eventualSafeNodes(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}}));
    }
}
