import java.util.*;

/*
Problem: Largest Friend Group After Each Friendship
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
There are n people and a list of friendships that form one at a time. Friend groups are transitive.
After each friendship, report the size of the largest friend group.

Why this pattern fits:
The groups only ever merge, never split, and the question is asked after every merge.
Union find maintains group sizes incrementally, so each answer costs almost nothing.

Approach:
Use union by size. After every union, compare the size of the merged set with the best so far.

Worked Example:
n = 6, friendships (0,1), (2,3), (1,2), (4,5), (0,3) -> largest sizes 2, 2, 4, 4, 4.

Time Complexity: O((n + m) * alpha(n))
Space Complexity: O(n)
*/

public class P07_LargestGroupAfterEachFriendshipUnionFind {
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

    public static int[] largestGroupSizes(int n, int[][] friendships) {
        UnionFind sets = new UnionFind(n);
        int[] result = new int[friendships.length];
        int largest = 1;
        for (int i = 0; i < friendships.length; i++) {
            sets.union(friendships[i][0], friendships[i][1]);
            largest = Math.max(largest, sets.size[sets.find(friendships[i][0])]);
            result[i] = largest;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] friendships = {{0, 1}, {2, 3}, {1, 2}, {4, 5}, {0, 3}};
        System.out.println("n = 6 -> " + Arrays.toString(largestGroupSizes(6, friendships)));
    }
}
