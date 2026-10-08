import java.util.*;

/*
Problem: Similar String Groups
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
Two strings X and Y are similar if we can swap two letters (in different positions) of X so that it equals Y.
    Two strings are also similar if they are equal.
    Similarity connects strings into groups: a string belongs to a group if it is similar to at least one
    other string of the group. For example, "tars" and "rats" are similar, "rats" and "arts" are similar,
    so all three are in one group even though "tars" and "arts" are not similar to each other.
    You are given a list strs where every string is an anagram of every other string. Return the number of groups.

Why this pattern fits:
Similarity is not transitive, but group membership is: groups are the connected components
    of the graph whose edges are similar pairs. Union-find merges components as similar pairs are discovered.

Approach:
Compare every pair of strings. Because all strings are anagrams of each other,
    two strings are similar exactly when they differ in 0 or 2 positions.
    Stop counting differences as soon as a third one is found.
    Union every similar pair, and return the number of components that remain.

Worked Example:
Example:
    strs = [tars, rats, arts, star]
    tars ~ rats (swap t and r), rats ~ arts (swap r and a). "star" is not similar to any of them.
    Groups: {tars, rats, arts} and {star}. Answer = 2.

Time Complexity: O(n^2 * L), where L is the string length
Space Complexity: O(n)
*/

public class SimilarStringGroupsUnionFind {
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

    public static int numSimilarGroups(String[] strs) {
        DSU dsu = new DSU(strs.length);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }
        return dsu.components;
    }

    private static boolean isSimilar(String a, String b) {
        int differences = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i) && ++differences > 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(numSimilarGroups(new String[]{"tars", "rats", "arts", "star"}));
        System.out.println(numSimilarGroups(new String[]{"omv", "ovm"}));
    }
}
