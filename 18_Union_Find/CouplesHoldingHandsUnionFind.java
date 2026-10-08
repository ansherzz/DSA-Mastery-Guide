import java.util.*;

/*
Problem: Couples Holding Hands
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
There are n couples sitting in 2n seats arranged in a row, and they want to hold hands.
    The people are represented by ids: row[i] is the id of the person sitting in seat i.
    The couples are numbered in order: (0, 1), (2, 3), and so on, so person p belongs to couple p / 2.
    Return the minimum number of swaps so that every couple is sitting side by side.
    A swap consists of choosing any two people and switching their seats.

Why this pattern fits:
Each pair of adjacent seats (0-1, 2-3, ...) is a "sofa" holding people from one or two couples.
    Link the two couples that share a sofa. Couples then form cycles: a group of k couples tangled together
    across k sofas needs exactly k - 1 swaps to untangle. Union-find counts the groups.

Approach:
Treat each couple as a node. For every sofa, union the couples of its two occupants.
    If there are n couples and the unions leave c groups, the answer is the sum over groups of (size - 1),
    which equals n - c.
    Equivalently, every successful union adds one required swap.

Worked Example:
Example:
    row = [0, 2, 1, 3]
    Sofa 0 holds couples 0 and 1, and sofa 1 holds couples 0 and 1 as well. One group of 2 couples: 2 - 1 = 1 swap.
    Swapping the people in seats 1 and 2 gives [0, 1, 2, 3].

Time Complexity: O(n * alpha(n)), effectively linear
Space Complexity: O(n)
*/

public class CouplesHoldingHandsUnionFind {
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

    public static int minSwapsCouples(int[] row) {
        int couples = row.length / 2;
        DSU dsu = new DSU(couples);
        for (int seat = 0; seat < row.length; seat += 2) {
            dsu.union(row[seat] / 2, row[seat + 1] / 2);
        }
        return couples - dsu.components;
    }

    public static void main(String[] args) {
        System.out.println(minSwapsCouples(new int[]{0, 2, 1, 3}));
        System.out.println(minSwapsCouples(new int[]{3, 2, 0, 1}));
        System.out.println(minSwapsCouples(new int[]{0, 2, 4, 1, 3, 5}));
    }
}
