import java.util.*;

/*
Problem: Minimum Spanning Tree (Prim's Algorithm)
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given a weighted undirected graph, return the total weight of a minimum spanning tree,
or -1 if the graph is not connected.

Why this pattern fits:
Prim's algorithm is Dijkstra's loop with a different key: the priority of a node is the weight of the
cheapest single edge connecting it to the tree, not its total distance from the start.

Approach:
Push (weight 0, node 0) on a min-heap. Pop the cheapest entry; skip it if the node is already in the tree.
Otherwise add the weight, mark the node, and push all edges to nodes outside the tree.

Worked Example:
Edges 0-1 (1), 1-2 (2), 2-3 (3), 0-3 (4), 0-2 (5) -> the tree uses weights 1, 2, 3. Answer = 6.

Time Complexity: O(E log E)
Space Complexity: O(V + E)
*/

public class P32_MinimumSpanningTreePrimBFSGraph {
    public static int minimumSpanningTree(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        boolean[] inTree = new boolean[n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.add(new int[]{0, 0});
        int total = 0;
        int added = 0;
        while (!heap.isEmpty() && added < n) {
            int[] current = heap.poll();
            if (inTree[current[0]]) {
                continue;
            }
            inTree[current[0]] = true;
            total += current[1];
            added++;
            for (int[] next : graph.get(current[0])) {
                if (!inTree[next[0]]) {
                    heap.add(next);
                }
            }
        }
        return added == n ? total : -1;
    }

    public static void main(String[] args) {
        System.out.println("connected graph -> " + minimumSpanningTree(4,
                new int[][]{{0, 1, 1}, {1, 2, 2}, {2, 3, 3}, {0, 3, 4}, {0, 2, 5}}));
        System.out.println("disconnected graph -> " + minimumSpanningTree(3, new int[][]{{0, 1, 5}}));
    }
}
