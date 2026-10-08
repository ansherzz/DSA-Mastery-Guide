import java.util.*;

/*
Problem: Longest Consecutive Sequence using Union Find
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Given an unsorted array of integers, return the length of the longest sequence of consecutive integers
    that can be formed from its values. The elements do not need to be adjacent in the array.

    The HashSet version is in folder 02. Solve it here with a disjoint set union.

Why this pattern fits:
Think of each distinct value as a node, with an edge between v and v + 1 whenever both are present.
    A run of consecutive integers is then a connected component, and the answer is the size of the largest one.
    Union Find with size tracking gives that directly.

Approach:
Map each distinct value to the index of its first occurrence, ignoring duplicates.
    For each value v, if v + 1 is also in the map, union their indices.
    Track the largest component size seen while merging.

Worked Example:
Example:
    nums = [100, 4, 200, 1, 3, 2]
    Unions: 1-2, 2-3, 3-4 create a component of size 4. The values 100 and 200 stay alone.
    Answer = 4.

Time Complexity: O(n * alpha(n)) average
Space Complexity: O(n)
*/

public class P14_LongestConsecutiveSequenceUnionFind {
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

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> indexByValue = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexByValue.putIfAbsent(nums[i], i);
        }

        DSU dsu = new DSU(nums.length);
        int best = 1;

        for (Map.Entry<Integer, Integer> entry : indexByValue.entrySet()) {
            int value = entry.getKey();
            if (value == Integer.MAX_VALUE) {
                continue;
            }
            Integer nextIndex = indexByValue.get(value + 1);
            if (nextIndex != null) {
                dsu.union(entry.getValue(), nextIndex);
                best = Math.max(best, dsu.size[dsu.find(nextIndex)]);
            }
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array  : " + Arrays.toString(nums));
        System.out.println("Longest consecutive length: " + longestConsecutive(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{100, 4, 200, 1, 3, 2});
        runDemo(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
        runDemo(new int[]{});
    }
}
