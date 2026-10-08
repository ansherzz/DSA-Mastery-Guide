import java.util.*;

/*
Problem: M-Coloring Problem (Graph Coloring)
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an undirected graph and m colors, determine whether every vertex can be colored
so that no two adjacent vertices share a color.

Why this pattern fits:
Each vertex takes one of m values subject to constraints with its neighbors.
A choice that looks fine locally may block a later vertex, so assignments must be undone and retried.

Approach:
dfs(vertex): for each color 1..m that differs from the colors of all adjacent vertices,
assign it and recurse to the next vertex. Reset the color when the recursion fails.

Worked Example:
A 4-cycle 0-1-2-3 with the diagonal 0-2 contains triangles, so 2 colors fail but 3 colors work.

Time Complexity: O(m^V)
Space Complexity: O(V)
*/

public class P30_MColoringProblemBacktracking {
    private static boolean color(List<List<Integer>> graph, int[] colors, int vertex, int m) {
        if (vertex == colors.length) {
            return true;
        }
        for (int c = 1; c <= m; c++) {
            boolean clash = false;
            for (int neighbor : graph.get(vertex)) {
                clash |= colors[neighbor] == c;
            }
            if (clash) {
                continue;
            }
            colors[vertex] = c;
            if (color(graph, colors, vertex + 1, m)) {
                return true;
            }
            colors[vertex] = 0;
        }
        return false;
    }

    public static boolean canColor(int vertices, int[][] edges, int m) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return color(graph, new int[vertices], 0, m);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {0, 2}};
        System.out.println("m = 3 -> " + canColor(4, edges, 3));
        System.out.println("m = 2 -> " + canColor(4, edges, 2));
    }
}
