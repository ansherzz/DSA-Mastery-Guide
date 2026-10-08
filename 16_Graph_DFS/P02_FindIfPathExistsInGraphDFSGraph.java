import java.util.*;

/*
Problem: Find if Path Exists in Graph
Pattern: 16. Graph DFS
Difficulty: Easy

Interview Prompt:
There is an undirected graph with n vertices labeled 0 to n - 1, given as a list of edges.
    Determine whether there is a valid path from the vertex source to the vertex destination.

Why this pattern fits:
"Is B reachable from A?" is the most basic graph question.
    DFS answers it by exploring everything reachable from the source and checking whether the destination shows up.
    A visited array prevents going around cycles forever.

Approach:
Build an adjacency list from the edge list, adding each edge in both directions.
    Run DFS from the source using an explicit stack, marking vertices as visited when they are pushed.
    Return true as soon as the destination is reached, or false if the search runs out of vertices.

Worked Example:
Example:
    n = 6, edges = [[0, 1], [0, 2], [3, 5], [5, 4], [4, 3]]
    The vertices {0, 1, 2} and {3, 4, 5} form two separate components.
    There is a path from 0 to 2, but no path from 0 to 5.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P02_FindIfPathExistsInGraphDFSGraph {
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (node == destination) {
                return true;
            }
            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        System.out.println("Path 0 -> 2? " + validPath(6, edges, 0, 2));
        System.out.println("Path 0 -> 5? " + validPath(6, edges, 0, 5));
        System.out.println("Path 3 -> 4? " + validPath(6, edges, 3, 4));
    }
}
