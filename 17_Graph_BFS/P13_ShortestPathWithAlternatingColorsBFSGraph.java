import java.util.*;

/*
Problem: Shortest Path with Alternating Colors
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
A directed graph has n nodes labeled 0 to n - 1. Each edge is either red or blue,
    and there may be self-loops and parallel edges.
    Return an array answer where answer[x] is the length of the shortest path from node 0 to node x
    in which the edge colors alternate along the path, or -1 if no such path exists.

Why this pattern fits:
It is still a shortest path with unit-cost edges, so BFS applies.
    The twist is that whether an edge may be used depends on the color of the previous edge.
    The fix is to make the color part of the state: BFS runs over (node, color of last edge) pairs.

Approach:
Keep separate adjacency lists for red and blue edges.
    Start with two states at node 0: one pretending the last edge was red and one pretending it was blue.
    From a state whose last edge was red, follow only blue edges, and vice versa.
    Track visited per (node, color). The first time a node is reached in any state gives its answer.

Worked Example:
Example:
    n = 3, redEdges = [[0, 1]], blueEdges = [[1, 2]]
    0 -> 1 uses a red edge and 1 -> 2 uses a blue edge, so the colors alternate.
    Answer = [0, 1, 2]

    If both edges were red, node 2 would be unreachable: [0, 1, -1]

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P13_ShortestPathWithAlternatingColorsBFSGraph {
    private static final int RED = 0;
    private static final int BLUE = 1;

    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<List<Integer>>> graph = new ArrayList<>();
        for (int color = 0; color < 2; color++) {
            List<List<Integer>> byNode = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                byNode.add(new ArrayList<>());
            }
            graph.add(byNode);
        }
        for (int[] edge : redEdges) {
            graph.get(RED).get(edge[0]).add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            graph.get(BLUE).get(edge[0]).add(edge[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, RED});
        queue.offer(new int[]{0, BLUE});
        visited[0][RED] = true;
        visited[0][BLUE] = true;

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] state = queue.poll();
                int node = state[0];
                int lastColor = state[1];

                if (answer[node] == -1) {
                    answer[node] = steps;
                }

                int nextColor = 1 - lastColor;
                for (int next : graph.get(nextColor).get(node)) {
                    if (!visited[next][nextColor]) {
                        visited[next][nextColor] = true;
                        queue.offer(new int[]{next, nextColor});
                    }
                }
            }
            steps++;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
            shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
        System.out.println(Arrays.toString(
            shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{2, 1}})));
        System.out.println(Arrays.toString(
            shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}})));
    }
}
