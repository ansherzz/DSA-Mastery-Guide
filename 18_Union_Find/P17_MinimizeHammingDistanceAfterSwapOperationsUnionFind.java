import java.util.*;

/*
Problem: Minimize Hamming Distance After Swap Operations
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You may swap source[a] and source[b] for any allowed pair [a, b], any number of times.
Return the minimum Hamming distance between source and target after any sequence of swaps.

Why this pattern fits:
Allowed swaps are transitive: within a connected group of indices, the values can be arranged in any order.
So only the multiset of source values in each group matters when matching against target.

Approach:
Union the indices of every allowed swap. For each group, count the source values.
Then for each index, if the target value is still available in its group's counter use it; otherwise it is a mismatch.

Worked Example:
source = [1, 2, 3, 4], target = [2, 1, 4, 5], swaps (0,1), (2,3) -> group {0,1} matches fully, group {2,3} cannot produce 5. Answer = 1.

Time Complexity: O((n + m) * alpha(n))
Space Complexity: O(n)
*/

public class P17_MinimizeHammingDistanceAfterSwapOperationsUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
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

    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind sets = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            sets.union(swap[0], swap[1]);
        }
        Map<Integer, Map<Integer, Integer>> available = new HashMap<>();
        for (int i = 0; i < n; i++) {
            available.computeIfAbsent(sets.find(i), k -> new HashMap<>()).merge(source[i], 1, Integer::sum);
        }
        int mismatches = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> counter = available.get(sets.find(i));
            int remaining = counter.getOrDefault(target[i], 0);
            if (remaining > 0) {
                counter.put(target[i], remaining - 1);
            } else {
                mismatches++;
            }
        }
        return mismatches;
    }

    public static void main(String[] args) {
        System.out.println("two swap pairs -> " + minimumHammingDistance(
                new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 5}, new int[][]{{0, 1}, {2, 3}}));
        System.out.println("no swaps -> " + minimumHammingDistance(
                new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 4}, new int[][]{}));
        System.out.println("one big group -> " + minimumHammingDistance(
                new int[]{5, 1, 2, 4, 3}, new int[]{1, 5, 4, 2, 3}, new int[][]{{0, 4}, {4, 2}, {1, 3}, {1, 4}}));
    }
}
