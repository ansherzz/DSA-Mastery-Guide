import java.util.*;

/*
Problem: Number of Good Paths
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
In a tree, a good path starts and ends at nodes with the same value, and every node on the path has a value
less than or equal to that value. A single node counts as a good path. Return the number of good paths.

Why this pattern fits:
For a value v, two nodes with value v form a good path exactly when they are connected using only nodes of value <= v.
Processing values in increasing order and uniting edges whose other endpoint is not larger maintains precisely that graph.

Approach:
Group nodes by value in sorted order. For each value: union every node of that value with its neighbors of value <= it.
Then count the nodes of that value per component; a component holding c of them contributes c * (c + 1) / 2 paths.

Worked Example:
vals = [1, 3, 2, 1, 3], edges 0-1, 0-2, 2-3, 2-4 -> five single-node paths plus the path 1-0-2-4 between the two 3s. Answer = 6.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P50_NumberOfGoodPathsUnionFind {
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

    public static int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        TreeMap<Integer, List<Integer>> nodesByValue = new TreeMap<>();
        for (int node = 0; node < n; node++) {
            nodesByValue.computeIfAbsent(vals[node], k -> new ArrayList<>()).add(node);
        }
        UnionFind sets = new UnionFind(n);
        int paths = 0;
        for (List<Integer> nodes : nodesByValue.values()) {
            for (int node : nodes) {
                for (int neighbor : graph.get(node)) {
                    if (vals[neighbor] <= vals[node]) {
                        sets.union(node, neighbor);
                    }
                }
            }
            Map<Integer, Integer> countByRoot = new HashMap<>();
            for (int node : nodes) {
                countByRoot.merge(sets.find(node), 1, Integer::sum);
            }
            for (int count : countByRoot.values()) {
                paths += count * (count + 1) / 2;
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 2, 1, 3] -> " + numberOfGoodPaths(
                new int[]{1, 3, 2, 1, 3}, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}}));
        System.out.println("[1, 1, 2, 2, 3] -> " + numberOfGoodPaths(
                new int[]{1, 1, 2, 2, 3}, new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}}));
        System.out.println("[1] -> " + numberOfGoodPaths(new int[]{1}, new int[][]{}));
    }
}
