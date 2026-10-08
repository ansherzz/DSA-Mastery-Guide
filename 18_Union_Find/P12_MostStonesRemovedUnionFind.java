import java.util.*;

/*
Problem: Most Stones Removed with Same Row or Column
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
There are n stones on a 2D plane at distinct integer coordinates.
    A stone can be removed if it shares its row or its column with another stone that has not been removed yet.
    Return the largest number of stones that can be removed.

Why this pattern fits:
Connect two stones when they share a row or a column. Inside one connected group,
    stones can be removed one by one (always removing a stone that keeps the rest connected)
    until exactly one remains. So each group contributes (size - 1) removals,
    and the total is n minus the number of groups. Counting groups is a Union Find task.

Approach:
Comparing all pairs of stones costs O(n^2). Instead remember, for each row and for each column,
    the index of the first stone seen there. When a later stone appears in the same row or column,
    union it with that remembered stone. The answer is n minus the number of components.

Worked Example:
Example:
    stones = [[0, 0], [0, 1], [1, 0], [1, 2], [2, 1], [2, 2]]
    All six stones are linked through shared rows and columns, forming one group.
    Answer = 6 - 1 = 5.

Time Complexity: O(n * alpha(n))
Space Complexity: O(n)
*/

public class P12_MostStonesRemovedUnionFind {
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

    public static int removeStones(int[][] stones) {
        int n = stones.length;
        DSU dsu = new DSU(n);
        Map<Integer, Integer> firstInRow = new HashMap<>();
        Map<Integer, Integer> firstInCol = new HashMap<>();
        int components = n;

        for (int i = 0; i < n; i++) {
            Integer rowMate = firstInRow.putIfAbsent(stones[i][0], i);
            if (rowMate != null && dsu.union(i, rowMate)) {
                components--;
            }

            Integer colMate = firstInCol.putIfAbsent(stones[i][1], i);
            if (colMate != null && dsu.union(i, colMate)) {
                components--;
            }
        }
        return n - components;
    }

    public static void main(String[] args) {
        System.out.println(removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        System.out.println(removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
        System.out.println(removeStones(new int[][]{{0, 0}}));
    }
}
