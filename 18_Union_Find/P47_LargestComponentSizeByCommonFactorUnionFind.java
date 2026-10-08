import java.util.*;

/*
Problem: Largest Component Size by Common Factor
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
Build a graph on the given distinct positive integers where two numbers are connected
if they share a common factor greater than 1. Return the size of the largest connected component.

Why this pattern fits:
Testing all pairs for a common factor is too slow. Instead let every possible factor be a node:
a number is united with each of its factors, and two numbers sharing a factor automatically join the same set.

Approach:
Create a union find over values up to the maximum. For each number, for every divisor d from 2 to sqrt(number),
union the number with d and with number / d. Then count how many input numbers fall under each root.

Worked Example:
nums = [4, 6, 15, 35] -> 4-6 share 2, 6-15 share 3, 15-35 share 5: one component of size 4.

Time Complexity: O(n * sqrt(max) * alpha)
Space Complexity: O(max)
*/

public class P47_LargestComponentSizeByCommonFactorUnionFind {
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

    public static int largestComponentSize(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        UnionFind sets = new UnionFind(max + 1);
        for (int num : nums) {
            for (int d = 2; d * d <= num; d++) {
                if (num % d == 0) {
                    sets.union(num, d);
                    sets.union(num, num / d);
                }
            }
        }
        Map<Integer, Integer> count = new HashMap<>();
        int best = 0;
        for (int num : nums) {
            best = Math.max(best, count.merge(sets.find(num), 1, Integer::sum));
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[4, 6, 15, 35] -> " + largestComponentSize(new int[]{4, 6, 15, 35}));
        System.out.println("[20, 50, 9, 63] -> " + largestComponentSize(new int[]{20, 50, 9, 63}));
        System.out.println("[2, 3, 6, 7, 4, 12, 21, 39] -> " + largestComponentSize(new int[]{2, 3, 6, 7, 4, 12, 21, 39}));
    }
}
