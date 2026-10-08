import java.util.*;

/*
Problem: Minimum Time to Collect All Apples in a Tree
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given an undirected tree consisting of n vertices numbered from 0 to n - 1, some vertices have apples.
    You spend 1 second to walk over one edge of the tree.
    Return the minimum time in seconds you have to spend to collect all apples in the tree,
    starting at vertex 0 and coming back to this vertex.

Why this pattern fits:
In a tree there is exactly one route to each vertex, so the only question per edge is whether it must be walked at all.
    An edge is needed exactly when the subtree below it contains an apple, and subtree facts are computed by post-order DFS.

Approach:
Build an adjacency list. DFS from vertex 0, passing the parent to avoid walking back up.
    The DFS returns the time needed to collect every apple in the subtree and return to the subtree's root.
    For each child: get the child's time. If that time is positive or the child itself has an apple,
    the edge to the child must be walked down and back, so add the child's time + 2.

Worked Example:
Example:
    n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], apples at vertices 2, 4, and 5
    Needed edges: 0-1, 1-4, 1-5, 0-2. Each is walked twice. Answer = 8.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P36_MinimumTimeToCollectAllApplesDFSGraph {
    public static int minTime(int n, int[][] edges, boolean[] hasApple) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return dfs(graph, 0, -1, hasApple);
    }

    private static int dfs(List<List<Integer>> graph, int node, int parent, boolean[] hasApple) {
        int time = 0;
        for (int child : graph.get(node)) {
            if (child == parent) {
                continue;
            }
            int childTime = dfs(graph, child, node, hasApple);
            if (childTime > 0 || hasApple[child]) {
                time += childTime + 2;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        System.out.println(minTime(7, edges, new boolean[]{false, false, true, false, true, true, false}));
        System.out.println(minTime(7, edges, new boolean[]{false, false, true, false, false, true, false}));
        System.out.println(minTime(7, edges, new boolean[7]));
    }
}
