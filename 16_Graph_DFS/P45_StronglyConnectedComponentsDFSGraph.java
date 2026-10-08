import java.util.*;

/*
Problem: Strongly Connected Components (Tarjan)
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
Given a directed graph, return its strongly connected components
(maximal groups of nodes in which every node can reach every other node).

Why this pattern fits:
In a directed DFS, nodes of one strongly connected component form a subtree of the DFS tree.
Low-link values identify the root of each such subtree, and an explicit stack holds its members.

Approach:
dfs(node): assign disc and low, push the node on the stack. For each edge: recurse into unvisited nodes and
take their low; for nodes still on the stack take their disc. If low[node] == disc[node],
pop the stack down to node - those nodes form one component.

Worked Example:
Edges 1->0, 0->2, 2->1, 0->3, 3->4: nodes 0, 1, 2 form a cycle; 3 and 4 are components on their own.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P45_StronglyConnectedComponentsDFSGraph {
    private static int timer;

    private static void dfs(List<List<Integer>> graph, int node, int[] disc, int[] low, boolean[] onStack,
                            Deque<Integer> stack, List<List<Integer>> components) {
        disc[node] = low[node] = ++timer;
        stack.push(node);
        onStack[node] = true;
        for (int next : graph.get(node)) {
            if (disc[next] == 0) {
                dfs(graph, next, disc, low, onStack, stack, components);
                low[node] = Math.min(low[node], low[next]);
            } else if (onStack[next]) {
                low[node] = Math.min(low[node], disc[next]);
            }
        }
        if (low[node] == disc[node]) {
            List<Integer> component = new ArrayList<>();
            int member;
            do {
                member = stack.pop();
                onStack[member] = false;
                component.add(member);
            } while (member != node);
            Collections.sort(component);
            components.add(component);
        }
    }

    public static List<List<Integer>> stronglyConnectedComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        timer = 0;
        int[] disc = new int[n];
        int[] low = new int[n];
        List<List<Integer>> components = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (disc[node] == 0) {
                dfs(graph, node, disc, low, new boolean[n], new ArrayDeque<>(), components);
            }
        }
        components.sort((a, b) -> a.get(0) - b.get(0));
        return components;
    }

    public static void main(String[] args) {
        System.out.println("cycle with a tail -> "
                + stronglyConnectedComponents(5, new int[][]{{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}}));
        System.out.println("directed 4-cycle -> "
                + stronglyConnectedComponents(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}}));
    }
}
