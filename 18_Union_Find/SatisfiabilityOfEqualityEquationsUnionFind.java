import java.util.*;

/*
Problem: Satisfiability of Equality Equations
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given equations such as "a==b" and "a!=b".
    Return true if it is possible to assign values so that all equations are satisfied.

    Equality equations create components; inequality equations check whether two variables wrongly ended up in the same component.

Why this pattern fits:
This is a clever DSU use case where the nodes are not graph edges from input positions but symbolic variables.

Approach:
First union all pairs connected by ==.
    Then scan all != equations. If any such pair has the same root, the system is impossible.

Worked Example:
Example:
    ["a==b","b!=a"] is impossible, so the answer is false.

Time Complexity: Near O(n * alpha(26))
Space Complexity: O(26)
*/

public class SatisfiabilityOfEqualityEquationsUnionFind {
    static class DSU {
        int[] parent = new int[26];

        DSU() {
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            parent[find(a)] = find(b);
        }
    }

    public static boolean equationsPossible(String[] equations) {
        DSU dsu = new DSU();

        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                dsu.union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
            }
        }

        for (String eq : equations) {
            if (eq.charAt(1) == '!' && dsu.find(eq.charAt(0) - 'a') == dsu.find(eq.charAt(3) - 'a')) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(equationsPossible(new String[]{"a==b","b!=a"}));
        System.out.println(equationsPossible(new String[]{"b==a","a==b"}));
    }
}
