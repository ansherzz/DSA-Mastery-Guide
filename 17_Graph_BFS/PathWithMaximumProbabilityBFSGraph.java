import java.util.*;

/*
Problem: Path with Maximum Probability
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given an undirected weighted graph of n nodes, where edges[i] = [a, b] is an edge
    with a probability of success succProb[i] of traversing it.
    Given two nodes start and end, find the path with the maximum probability of success from start to end
    and return that probability. If there is no path, return 0.

Why this pattern fits:
This is a shortest-path problem in disguise: path values are multiplied instead of added, and larger is better.
    Because every probability is at most 1, extending a path never increases its value,
    which is the same monotonic property that makes Dijkstra's priority-queue BFS correct.

Approach:
Run Dijkstra with a max-heap keyed by probability.
    best[node] holds the highest probability found so far, starting with best[start] = 1.
    Poll the most probable node; if it is the end node, its probability is final.
    Skip stale heap entries, and relax each edge with probability * edge probability.

Worked Example:
Example:
    n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5, 0.5, 0.2], start = 0, end = 2
    Path 0 -> 2 has probability 0.2. Path 0 -> 1 -> 2 has probability 0.5 * 0.5 = 0.25. Answer = 0.25.

Time Complexity: O(E log V)
Space Complexity: O(V + E)
*/

public class PathWithMaximumProbabilityBFSGraph {
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<double[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(new double[]{edges[i][1], succProb[i]});
            graph.get(edges[i][1]).add(new double[]{edges[i][0], succProb[i]});
        }

        double[] best = new double[n];
        best[start] = 1.0;
        PriorityQueue<double[]> heap = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        heap.offer(new double[]{start, 1.0});

        while (!heap.isEmpty()) {
            double[] current = heap.poll();
            int node = (int) current[0];
            double probability = current[1];
            if (node == end) {
                return probability;
            }
            if (probability < best[node]) {
                continue;
            }

            for (double[] edge : graph.get(node)) {
                int next = (int) edge[0];
                double candidate = probability * edge[1];
                if (candidate > best[next]) {
                    best[next] = candidate;
                    heap.offer(new double[]{next, candidate});
                }
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        System.out.println(maxProbability(3, edges, new double[]{0.5, 0.5, 0.2}, 0, 2));
        System.out.println(maxProbability(3, edges, new double[]{0.5, 0.5, 0.3}, 0, 2));
        System.out.println(maxProbability(3, new int[][]{{0, 1}}, new double[]{0.5}, 0, 2));
    }
}
