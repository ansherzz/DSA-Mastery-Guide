import java.util.*;

/*
Problem: Optimize Water Distribution in a Village
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
There are n houses. Building a well in house i costs wells[i - 1]; laying a pipe between two houses costs pipes[j][2].
Return the minimum total cost to supply water to every house.

Why this pattern fits:
Two kinds of costs (node costs and edge costs) do not fit a spanning tree directly.
Adding a virtual node 0 that represents "water" and connecting it to each house with the well cost
turns everything into edges, and the answer becomes an ordinary minimum spanning tree.

Approach:
Build edges (0, i, wells[i - 1]) plus all pipes. Sort by cost and union greedily, summing the accepted costs.

Worked Example:
n = 3, wells = [1, 2, 2], pipes (1,2,1), (2,3,1) -> one well in house 1 and two pipes cost 3.

Time Complexity: O((n + m) log(n + m))
Space Complexity: O(n + m)
*/

public class P44_OptimizeWaterDistributionInAVillageUnionFind {
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

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]> edges = new ArrayList<>();
        for (int house = 1; house <= n; house++) {
            edges.add(new int[]{0, house, wells[house - 1]});
        }
        edges.addAll(Arrays.asList(pipes));
        edges.sort((a, b) -> a[2] - b[2]);
        UnionFind sets = new UnionFind(n + 1);
        int total = 0;
        for (int[] edge : edges) {
            if (sets.union(edge[0], edge[1])) {
                total += edge[2];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("n = 3 -> " + minCostToSupplyWater(3, new int[]{1, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}}));
        System.out.println("n = 2 -> " + minCostToSupplyWater(2, new int[]{1, 1}, new int[][]{{1, 2, 1}, {1, 2, 2}}));
    }
}
