import java.util.*;

/*
Problem: Smallest String With Swaps
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given a string s and a list of index pairs, where pairs[i] = [a, b] means
    you may swap the characters at indices a and b. Each pair can be used any number of times.

    Return the lexicographically smallest string that can be obtained.

Why this pattern fits:
Swaps are transitive: if a can swap with b and b can swap with c, then the three positions
    can be rearranged in any order at all. So the indices fall into groups, and inside a group
    the characters can be freely sorted. Finding those groups is a Union Find job.

Approach:
Union the two indices of every pair.
    Collect the characters of each group (by root) into a min-heap.
    Then walk the indices from left to right, and for each index take the smallest remaining character
    from its group's heap. Smaller indices get smaller characters, which gives the smallest string.

Worked Example:
Example:
    s = "dcab", pairs = [[0, 3], [1, 2]]
    Groups: {0, 3} holds 'd' and 'b'; {1, 2} holds 'c' and 'a'.
    Sorting within groups gives b at index 0, a at index 1, c at index 2, d at index 3: "bacd".

Time Complexity: O(n log n + m * alpha(n))
Space Complexity: O(n)
*/

public class P16_SmallestStringWithSwapsUnionFind {
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

    public static String smallestStringWithSwaps(String s, int[][] pairs) {
        int n = s.length();
        DSU dsu = new DSU(n);
        for (int[] pair : pairs) {
            dsu.union(pair[0], pair[1]);
        }

        Map<Integer, PriorityQueue<Character>> groupChars = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groupChars.computeIfAbsent(dsu.find(i), key -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(groupChars.get(dsu.find(i)).poll());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(smallestStringWithSwaps("dcab", new int[][]{{0, 3}, {1, 2}}));
        System.out.println(smallestStringWithSwaps("dcab", new int[][]{{0, 3}, {1, 2}, {0, 2}}));
        System.out.println(smallestStringWithSwaps("cba", new int[][]{{0, 1}, {1, 2}}));
    }
}
