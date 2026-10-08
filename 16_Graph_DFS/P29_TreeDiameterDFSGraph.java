import java.util.*;

/*
Problem: Tree Diameter
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
An undirected tree with n nodes is given as a list of n - 1 edges.
Return the number of edges on the longest path between any two nodes.

Why this pattern fits:
The longest path has a highest node (relative to any chosen root) where two downward branches meet.
One DFS that returns the height of each subtree can evaluate every node as that meeting point.

Approach:
Root the tree at node 0. dfs(node, parent) returns the longest downward path.
While visiting the children keep the two largest values of child height + 1 and update diameter with their sum.

Worked Example:
Edges 0-1, 1-2, 2-3, 1-4, 4-5 -> the path 3-2-1-4-5 has 4 edges.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P29_TreeDiameterDFSGraph {
    private static int diameter;

    private static int dfs(List<List<Integer>> graph, int node, int parent) {
        int longest = 0;
        int second = 0;
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) {
                continue;
            }
            int length = dfs(graph, neighbor, node) + 1;
            if (length > longest) {
                second = longest;
                longest = length;
            } else if (length > second) {
                second = length;
            }
        }
        diameter = Math.max(diameter, longest + second);
        return longest;
    }

    public static int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        diameter = 0;
        dfs(graph, 0, -1);
        return diameter;
    }

    public static void main(String[] args) {
        System.out.println("[[0,1],[0,2]] -> " + treeDiameter(new int[][]{{0, 1}, {0, 2}}));
        System.out.println("[[0,1],[1,2],[2,3],[1,4],[4,5]] -> "
                + treeDiameter(new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}}));
    }
}
