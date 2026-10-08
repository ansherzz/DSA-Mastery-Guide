import java.util.*;

/*
Problem: Redundant Connection II
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
A rooted tree is a directed graph with exactly one root, where every other node has exactly one parent.
    You are given a directed graph that started as a rooted tree with n nodes (labeled 1 to n)
    and then had one extra directed edge added. Each edge is [parent, child].

    Return an edge that can be removed so that the result is a rooted tree again.
    If there are several answers, return the one that appears last in the input.

Why this pattern fits:
The extra edge breaks the tree in one of two ways (or both): some node ends up with two parents,
    or a directed cycle appears. Union Find detects the cycle, and a simple parent table detects the double parent.
    The combination of the two findings determines which edge is the culprit.

Approach:
Scan the edges to find a node with two parents. If one exists, remember both edges pointing to it:
    the earlier one as candidate A and the later one as candidate B.
    Then run Union Find over all edges while ignoring candidate B.
    - If no cycle appears, candidate B was the problem.
    - If a cycle appears and a two-parent node exists, candidate A is the problem.
    - If a cycle appears and no node has two parents, the edge that closed the cycle is the answer.

Worked Example:
Example:
    edges = [[1, 2], [1, 3], [2, 3]]
    Node 3 has two parents: candidate A = [1, 3], candidate B = [2, 3].
    Ignoring B leaves a valid tree with no cycle, so the answer is [2, 3].

Time Complexity: O(n * alpha(n))
Space Complexity: O(n)
*/

public class P39_RedundantConnectionIIUnionFind {
    static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }

    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parentOf = new int[n + 1];
        int[] candidateA = null;
        int[] candidateB = null;

        for (int[] edge : edges) {
            int child = edge[1];
            if (parentOf[child] != 0) {
                candidateA = new int[]{parentOf[child], child};
                candidateB = edge;
            } else {
                parentOf[child] = edge[0];
            }
        }

        DSU dsu = new DSU(n + 1);
        for (int[] edge : edges) {
            if (edge == candidateB) {
                continue;
            }
            if (!dsu.union(edge[0], edge[1])) {
                return candidateA != null ? candidateA : edge;
            }
        }
        return candidateB;
    }

    private static void runDemo(int[][] edges) {
        System.out.println(Arrays.deepToString(edges) + " -> " + Arrays.toString(findRedundantDirectedConnection(edges)));
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2}, {1, 3}, {2, 3}});
        runDemo(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}});
        runDemo(new int[][]{{2, 1}, {3, 1}, {4, 2}, {1, 4}});
    }
}
