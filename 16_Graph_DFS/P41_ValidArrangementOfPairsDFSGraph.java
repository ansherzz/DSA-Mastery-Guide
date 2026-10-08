import java.util.*;

/*
Problem: Valid Arrangement of Pairs
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
Arrange the given pairs [start, end] so that for every consecutive pair, the end of one equals the start of the next.
A valid arrangement is guaranteed to exist.

Why this pattern fits:
Using every pair exactly once with matching endpoints means walking every directed edge exactly once:
an Eulerian path. It starts at the node whose out-degree exceeds its in-degree by one (or anywhere if none does).

Approach:
Build adjacency queues and degree balances. Pick the start node. DFS: while the node has unused edges,
take one and recurse; after the loop, record the edge that led here. The recorded edges, reversed, form the path.

Worked Example:
pairs = [[5,1],[4,5],[11,9],[9,4]] -> 11 -> 9 -> 4 -> 5 -> 1.

Time Complexity: O(E)
Space Complexity: O(E)
*/

public class P41_ValidArrangementOfPairsDFSGraph {
    private static void walk(Map<Integer, Deque<Integer>> graph, int node, List<int[]> path) {
        Deque<Integer> next = graph.get(node);
        while (next != null && !next.isEmpty()) {
            int to = next.poll();
            walk(graph, to, path);
            path.add(new int[]{node, to});
        }
    }

    public static int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> balance = new HashMap<>();
        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayDeque<>()).add(pair[1]);
            balance.merge(pair[0], 1, Integer::sum);
            balance.merge(pair[1], -1, Integer::sum);
        }
        int start = pairs[0][0];
        for (Map.Entry<Integer, Integer> entry : balance.entrySet()) {
            if (entry.getValue() == 1) {
                start = entry.getKey();
            }
        }
        List<int[]> path = new ArrayList<>();
        walk(graph, start, path);
        Collections.reverse(path);
        return path.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println("[[5,1],[4,5],[11,9],[9,4]] -> "
                + Arrays.deepToString(validArrangement(new int[][]{{5, 1}, {4, 5}, {11, 9}, {9, 4}})));
        System.out.println("[[1,3],[3,2],[2,1]] -> "
                + Arrays.deepToString(validArrangement(new int[][]{{1, 3}, {3, 2}, {2, 1}})));
    }
}
