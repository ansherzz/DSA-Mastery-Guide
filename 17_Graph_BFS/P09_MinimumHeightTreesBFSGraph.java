import java.util.*;

/*
Problem: Minimum Height Trees
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
A tree is an undirected graph in which any two nodes are connected by exactly one path.
    Given a tree of n nodes labeled 0 to n - 1 as a list of n - 1 edges, you may choose any node as the root.
    Among all possible roots, return the labels of those that produce a rooted tree of minimum height.

Why this pattern fits:
The best roots are the centers of the tree: the middle node or middle two nodes of its longest path.
    They can be found by peeling the tree like an onion. Removing all leaves at once is one BFS layer,
    and repeating until at most two nodes remain leaves exactly the centers.

Approach:
Compute the degree of every node and enqueue all leaves (degree 1).
    Process the queue one full layer at a time: remove the leaves, decrease their neighbors' degrees,
    and enqueue any neighbor that becomes a leaf.
    Stop when the number of remaining nodes is 2 or fewer. The nodes left in the queue are the answer.

Worked Example:
Example:
    n = 6, edges = [[3, 0], [3, 1], [3, 2], [3, 4], [5, 4]]
    First layer of leaves: 0, 1, 2, 5. After removing them, nodes 3 and 4 remain.
    Answer = [3, 4]

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P09_MinimumHeightTreesBFSGraph {
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        Queue<Integer> leaves = new ArrayDeque<>();
        for (int node = 0; node < n; node++) {
            if (degree[node] == 1) {
                leaves.offer(node);
            }
        }

        int remaining = n;
        while (remaining > 2) {
            int size = leaves.size();
            remaining -= size;

            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                for (int neighbor : graph.get(leaf)) {
                    if (--degree[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }
        return new ArrayList<>(leaves);
    }

    public static void main(String[] args) {
        System.out.println(findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
        System.out.println(findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
        System.out.println(findMinHeightTrees(1, new int[][]{}));
    }
}
