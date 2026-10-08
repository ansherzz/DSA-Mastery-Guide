import java.util.*;

/*
Problem: All Ancestors of a Node in a Directed Acyclic Graph
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given a DAG with n nodes, return for every node the sorted list of all its ancestors
(nodes from which it can be reached).

Why this pattern fits:
"Ancestors of v" is awkward to gather backwards, but "descendants of u" is a plain DFS.
Processing u in increasing order means every ancestor list is built already sorted.

Approach:
For each u from 0 to n - 1, DFS from u. When a node v is reached whose list does not already end with u,
append u to ancestors[v] and continue from v.

Worked Example:
Edges 0->3, 0->4, 1->3, 2->4, 2->7, 3->5, 3->6, 3->7, 4->6: node 6 has ancestors 0, 1, 2, 3, 4.

Time Complexity: O(V * (V + E))
Space Complexity: O(V^2) for the output
*/

public class P23_AllAncestorsOfANodeInADirectedAcyclicGraphDFSGraph {
    private static void dfs(List<List<Integer>> graph, int ancestor, int node, List<List<Integer>> ancestors) {
        for (int next : graph.get(node)) {
            List<Integer> list = ancestors.get(next);
            if (list.isEmpty() || list.get(list.size() - 1) != ancestor) {
                list.add(ancestor);
                dfs(graph, ancestor, next, ancestors);
            }
        }
    }

    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> ancestors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            ancestors.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        for (int node = 0; node < n; node++) {
            dfs(graph, node, node, ancestors);
        }
        return ancestors;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}};
        System.out.println("n = 8 -> " + getAncestors(8, edges));
    }
}
