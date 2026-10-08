import java.util.*;

/*
Problem: Largest Color Value in a Directed Graph
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
Each node of a directed graph has a lowercase color. The color value of a path is the highest number of nodes
of one color on it. Return the largest color value of any path, or -1 if the graph contains a cycle.

Why this pattern fits:
In a DAG, the best count of each color on a path ending at a node depends only on its predecessors.
Kahn's order guarantees predecessors are finished first, and it detects cycles for free.

Approach:
count[node][c] = most nodes of color c on a path ending at node. When a node is popped, add one for its own color,
update the answer, and for each successor take the element-wise maximum. If not every node is popped, return -1.

Worked Example:
colors = "abaca", edges 0->1, 0->2, 2->3, 3->4 -> the path 0, 2, 3, 4 contains three 'a' nodes.

Time Complexity: O(26 * (V + E))
Space Complexity: O(26 * V)
*/

public class P40_LargestColorValueInADirectedGraphBFSGraph {
    public static int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        int[][] count = new int[n][26];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < n; node++) {
            if (indegree[node] == 0) {
                queue.add(node);
            }
        }
        int processed = 0;
        int best = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;
            best = Math.max(best, ++count[node][colors.charAt(node) - 'a']);
            for (int next : graph.get(node)) {
                for (int c = 0; c < 26; c++) {
                    count[next][c] = Math.max(count[next][c], count[node][c]);
                }
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return processed == n ? best : -1;
    }

    public static void main(String[] args) {
        System.out.println("abaca -> " + largestPathValue("abaca", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}));
        System.out.println("self loop -> " + largestPathValue("a", new int[][]{{0, 0}}));
    }
}
