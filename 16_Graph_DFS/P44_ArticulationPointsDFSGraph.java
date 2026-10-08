import java.util.*;

/*
Problem: Articulation Points in a Graph
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
Given a connected undirected graph, return all articulation points:
nodes whose removal disconnects the graph. Return them in increasing order.

Why this pattern fits:
DFS assigns each node a discovery time, and low[v] is the earliest discovery time reachable from v's subtree
using at most one back edge. If a child's low value is not smaller than the parent's discovery time,
the child's subtree is cut off when the parent is removed.

Approach:
dfs(node, parent): set disc and low. For each neighbor: a visited neighbor updates low with its disc;
an unvisited one is a child - recurse, update low, and if low[child] >= disc[node] (and node is not the root)
mark node. The root is an articulation point only when it has two or more DFS children.

Worked Example:
Edges 1-0, 0-2, 2-1, 0-3, 3-4: removing 0 isolates {3, 4}, removing 3 isolates {4}. Answer = [0, 3].

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P44_ArticulationPointsDFSGraph {
    private static int timer;

    private static void dfs(List<List<Integer>> graph, int node, int parent, int[] disc, int[] low, boolean[] isCut) {
        disc[node] = low[node] = ++timer;
        int children = 0;
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) {
                continue;
            }
            if (disc[neighbor] != 0) {
                low[node] = Math.min(low[node], disc[neighbor]);
            } else {
                children++;
                dfs(graph, neighbor, node, disc, low, isCut);
                low[node] = Math.min(low[node], low[neighbor]);
                if (parent != -1 && low[neighbor] >= disc[node]) {
                    isCut[node] = true;
                }
            }
        }
        if (parent == -1 && children > 1) {
            isCut[node] = true;
        }
    }

    public static List<Integer> articulationPoints(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        timer = 0;
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] isCut = new boolean[n];
        for (int node = 0; node < n; node++) {
            if (disc[node] == 0) {
                dfs(graph, node, -1, disc, low, isCut);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (isCut[node]) {
                result.add(node);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("triangle with a tail of two -> "
                + articulationPoints(5, new int[][]{{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}}));
        System.out.println("chain of four -> " + articulationPoints(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}));
        System.out.println("triangle -> " + articulationPoints(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
    }
}
