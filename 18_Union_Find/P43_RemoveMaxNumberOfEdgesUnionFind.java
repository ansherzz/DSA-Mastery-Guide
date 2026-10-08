import java.util.*;

/*
Problem: Remove Max Number of Edges to Keep Graph Fully Traversable
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
Alice and Bob have an undirected graph of n nodes (numbered from 1 to n) and three types of edges:
    type 1 can be traversed by Alice only, type 2 by Bob only, and type 3 by both.
    Given edges where edges[i] = [type, u, v], find the maximum number of edges you can remove so that
    the graph can still be fully traversed by both Alice and Bob, meaning each of them can reach every node
    from any starting node. Return -1 if Alice and Bob cannot fully traverse the graph even with all edges.

Why this pattern fits:
An edge is removable exactly when it connects two nodes that are already connected for everyone who can use it.
    Union-find reports that situation directly: union() returns false for a redundant edge.
    Two structures are needed because Alice and Bob see different graphs.

Approach:
Keep one DSU for Alice and one for Bob.
    Process all type 3 edges first, because one shared edge does the work of two single-person edges.
    A type 3 edge is redundant only if it is redundant for both; otherwise keep it.
    Then process type 1 edges on Alice's DSU and type 2 edges on Bob's DSU, counting the redundant ones.
    At the end both DSUs must have exactly one component; otherwise return -1.

Worked Example:
Example:
    n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
    The two shared edges connect 1-2-3 for both. Alice's edges 1-3 and 1-2 are redundant; her edge 2-4 is needed.
    Bob's edge 3-4 is needed. Removable edges = 2.

Time Complexity: O(E * alpha(V)), effectively linear
Space Complexity: O(V)
*/

public class P43_RemoveMaxNumberOfEdgesUnionFind {
    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
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
            components--;
            return true;
        }
    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n + 1);
        DSU bob = new DSU(n + 1);
        int removed = 0;

        for (int[] edge : edges) {
            if (edge[0] == 3) {
                boolean usefulForAlice = alice.union(edge[1], edge[2]);
                boolean usefulForBob = bob.union(edge[1], edge[2]);
                if (!usefulForAlice && !usefulForBob) {
                    removed++;
                }
            }
        }

        for (int[] edge : edges) {
            if (edge[0] == 1 && !alice.union(edge[1], edge[2])) {
                removed++;
            } else if (edge[0] == 2 && !bob.union(edge[1], edge[2])) {
                removed++;
            }
        }

        // Node 0 is unused, so a fully connected graph leaves two components: {0} and everything else.
        return alice.components == 2 && bob.components == 2 ? removed : -1;
    }

    public static void main(String[] args) {
        System.out.println(maxNumEdgesToRemove(4,
            new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}}));
        System.out.println(maxNumEdgesToRemove(4,
            new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}}));
        System.out.println(maxNumEdgesToRemove(4,
            new int[][]{{3, 2, 3}, {1, 1, 2}, {2, 3, 4}}));
    }
}
