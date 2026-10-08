import java.util.*;

/*
Problem: Lexicographically Smallest Equivalent String
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given two strings of the same length s1 and s2, and a string baseStr.
    The characters s1[i] and s2[i] are equivalent. Equivalence is reflexive, symmetric, and transitive.
    Return the lexicographically smallest equivalent string of baseStr,
    obtained by replacing each character with any character equivalent to it.

Why this pattern fits:
Reflexive, symmetric, and transitive relations are exactly what union-find maintains.
    The only customization is which element represents a group: here it should always be the smallest letter,
    so that find() directly returns the best replacement.

Approach:
Use a 26-entry parent array. When uniting two letters, attach the larger root under the smaller root.
    This replaces union by size with "union by smallest letter", keeping the smallest letter as the representative.
    Unite s1[i] with s2[i] for every index, then map each character of baseStr to its root.

Worked Example:
Example:
    s1 = "parker", s2 = "morris", baseStr = "parser"
    Groups: {m, p}, {a, o}, {k, r, s}, {e, i}. Replacing each letter of "parser" by its group's smallest letter gives "makkek".

Time Complexity: O((n + m) * alpha(26)), effectively linear
Space Complexity: O(1), a fixed 26-entry array
*/

public class P19_LexicographicallySmallestEquivalentStringUnionFind {
    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < s1.length(); i++) {
            union(parent, s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : baseStr.toCharArray()) {
            sb.append((char) ('a' + find(parent, ch - 'a')));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(smallestEquivalentString("parker", "morris", "parser"));
        System.out.println(smallestEquivalentString("hello", "world", "hold"));
        System.out.println(smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }
}
