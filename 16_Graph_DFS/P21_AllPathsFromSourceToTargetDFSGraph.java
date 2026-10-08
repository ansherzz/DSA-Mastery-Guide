import java.util.*;

/*
Problem: All Paths From Source to Target
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given a directed acyclic graph (DAG) with n nodes labeled 0 to n - 1,
    find all possible paths from node 0 to node n - 1 and return them in any order.
    The graph is given as an adjacency list: graph[i] lists the nodes you can go to directly from node i.

Why this pattern fits:
Listing every path means exploring every route, which is what DFS does when it is allowed to revisit nodes
    through different routes. Because the graph is acyclic, no visited set is needed;
    the search cannot loop. The current path is maintained with push and pop as the DFS goes down and up.

Approach:
Start a DFS at node 0 with the path [0].
    When the current node is n - 1, record a copy of the path.
    Otherwise, for each neighbor: append it to the path, recurse, and remove it again.

Worked Example:
Example:
    graph = [[1, 2], [3], [3], []]
    Node 0 goes to 1 and 2, and both go to 3.
    Paths: [0, 1, 3] and [0, 2, 3]

Time Complexity: O(2^n * n) in the worst case
Space Complexity: O(n) recursion depth, excluding the output
*/

public class P21_AllPathsFromSourceToTargetDFSGraph {
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, 0, path, paths);
        return paths;
    }

    private static void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> paths) {
        if (node == graph.length - 1) {
            paths.add(new ArrayList<>(path));
            return;
        }

        for (int next : graph[node]) {
            path.add(next);
            dfs(graph, next, path, paths);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
        System.out.println(allPathsSourceTarget(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}));
    }
}
