import java.util.*;

/*
Problem: Sum of Distances in Tree
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
    Return an array answer of length n where answer[i] is the sum of the distances
    between the i-th node and all other nodes in the tree.

Why this pattern fits:
Running a traversal from every node costs O(n^2). The rerooting technique uses two DFS passes instead:
    one to solve the problem for a single root, and one to transfer that answer across each edge in O(1).

Approach:
Pass 1 (post-order, rooted at 0): compute count[v], the size of v's subtree,
    and answer[0], the sum of distances from the root, by adding count[child] for every edge
    (each node in a child's subtree is one edge farther from the parent).
    Pass 2 (pre-order): move the root from a parent to a child.
    The count[child] nodes inside the child's subtree get one step closer, and the other n - count[child] nodes
    get one step farther, so answer[child] = answer[parent] - count[child] + (n - count[child]).

Worked Example:
Example:
    n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
    answer[0] = 1 + 1 + 2 + 2 + 2 = 8. Moving the root to node 2 (subtree size 4): 8 - 4 + 2 = 6.
    Result: [8, 12, 6, 10, 10, 10].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P48_SumOfDistancesInTreeDFSGraph {
    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] count = new int[n];
        int[] answer = new int[n];
        collect(graph, 0, -1, count, answer);
        reroot(graph, 0, -1, count, answer);
        return answer;
    }

    // Returns the sum of distances from node to every node in its own subtree.
    private static int collect(List<List<Integer>> graph, int node, int parent, int[] count, int[] answer) {
        count[node] = 1;
        int distanceSum = 0;
        for (int child : graph.get(node)) {
            if (child == parent) {
                continue;
            }
            distanceSum += collect(graph, child, node, count, answer) + count[child];
            count[node] += count[child];
        }

        if (parent == -1) {
            answer[node] = distanceSum;
        }
        return distanceSum;
    }

    private static void reroot(List<List<Integer>> graph, int node, int parent, int[] count, int[] answer) {
        for (int child : graph.get(node)) {
            if (child == parent) {
                continue;
            }
            answer[child] = answer[node] - count[child] + (count.length - count[child]);
            reroot(graph, child, node, count, answer);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumOfDistancesInTree(
            6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}})));
        System.out.println(Arrays.toString(sumOfDistancesInTree(1, new int[][]{})));
        System.out.println(Arrays.toString(sumOfDistancesInTree(2, new int[][]{{1, 0}})));
    }
}
