import java.util.*;

/*
Problem: Shortest Path Visiting All Nodes
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
You are given a connected undirected graph of n nodes labeled 0 to n - 1 (n is at most 12),
    as an adjacency list. Return the length of the shortest path that visits every node.
    You may start and stop at any node, revisit nodes, and reuse edges.

Why this pattern fits:
Because nodes can be revisited, the current node alone does not describe progress.
    The full state is (current node, set of nodes visited so far).
    Every move costs one, so BFS over these states finds the shortest way to reach a state
    in which the visited set is complete. With n at most 12, the set fits in a bitmask.

Approach:
Represent the visited set as an n-bit mask. Start BFS from all n single-node states at once,
    because any node may be the starting point.
    From state (node, mask), moving to a neighbor gives (neighbor, mask with the neighbor's bit set).
    Track visited states in a table indexed by node and mask.
    The first time a state with the full mask appears, the current number of steps is the answer.

Worked Example:
Example:
    graph = [[1, 2, 3], [0], [0], [0]]  (a star with center 0)
    One shortest route is 1 -> 0 -> 2 -> 0 -> 3, which uses 4 edges.

Time Complexity: O(2^n * n * average degree)
Space Complexity: O(2^n * n)
*/

public class P42_ShortestPathVisitingAllNodesBFSGraph {
    public static int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n == 1) {
            return 0;
        }

        int fullMask = (1 << n) - 1;
        boolean[][] seen = new boolean[n][1 << n];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int node = 0; node < n; node++) {
            int mask = 1 << node;
            seen[node][mask] = true;
            queue.offer(new int[]{node, mask});
        }

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int[] state = queue.poll();
                for (int next : graph[state[0]]) {
                    int nextMask = state[1] | (1 << next);
                    if (nextMask == fullMask) {
                        return steps;
                    }
                    if (!seen[next][nextMask]) {
                        seen[next][nextMask] = true;
                        queue.offer(new int[]{next, nextMask});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(shortestPathLength(new int[][]{{1, 2, 3}, {0}, {0}, {0}}));
        System.out.println(shortestPathLength(new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}}));
        System.out.println(shortestPathLength(new int[][]{{}}));
    }
}
