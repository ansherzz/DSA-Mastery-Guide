import java.util.*;

/*
Problem: Number of Operations to Make Network Connected
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
There are n computers numbered 0 to n - 1 connected by ethernet cables, where connections[i] = [a, b].
    You may unplug any cable between two directly connected computers and plug it in between
    any pair of disconnected computers.

    Return the minimum number of such moves needed to make all computers connected, or -1 if it is impossible.

Why this pattern fits:
Only two numbers matter: how many separate groups exist, and how many cables are spare.
    Joining c groups requires c - 1 cables. A cable is spare exactly when it connects two computers
    that were already connected, which is what a failed union reports.

Approach:
A network of n computers needs at least n - 1 cables, so return -1 if there are fewer.
    Union the endpoints of every cable while counting components.
    Since the total number of cables is sufficient, there are always enough spare ones,
    and the answer is components - 1.

Worked Example:
Example:
    n = 4, connections = [[0, 1], [0, 2], [1, 2]]
    Computers 0, 1, 2 form one group and computer 3 is alone: 2 components.
    The cable 1-2 is redundant, so move it to connect computer 3. Answer = 1.

Time Complexity: O(E * alpha(V))
Space Complexity: O(V)
*/

public class P14_NetworkConnectedOperationsUnionFind {
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

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        DSU dsu = new DSU(n);
        int components = n;
        for (int[] cable : connections) {
            if (dsu.union(cable[0], cable[1])) {
                components--;
            }
        }
        return components - 1;
    }

    public static void main(String[] args) {
        System.out.println(makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println(makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println(makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}));
    }
}
