import java.util.*;

/*
Problem: Longest Path With Different Adjacent Characters
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
A tree is given by a parent array with root 0, and node i has character s[i].
Return the number of nodes on the longest path in which no two adjacent nodes share a character.

Why this pattern fits:
It is the tree-diameter recursion with a filter. Each node combines its two longest valid downward chains,
and a child's chain is usable only when the child's character differs from the node's.

Approach:
dfs(node) returns the longest valid chain starting at node and going down.
For each child compute its chain; if the characters differ, use it to update the two best chains.
The answer is the maximum of best1 + best2 + 1 over all nodes.

Worked Example:
parent = [-1, 0, 0, 1, 1, 2], s = "abacbe" -> the path 3 - 1 - 0 has characters c, b, a. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P49_LongestPathWithDifferentAdjacentCharactersDFSGraph {
    private static int best;

    private static int dfs(List<List<Integer>> children, int node, String s) {
        int longest = 0;
        int second = 0;
        for (int child : children.get(node)) {
            int chain = dfs(children, child, s);
            if (s.charAt(child) == s.charAt(node)) {
                continue;
            }
            if (chain > longest) {
                second = longest;
                longest = chain;
            } else if (chain > second) {
                second = chain;
            }
        }
        best = Math.max(best, longest + second + 1);
        return longest + 1;
    }

    public static int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            children.get(parent[i]).add(i);
        }
        best = 0;
        dfs(children, 0, s);
        return best;
    }

    public static void main(String[] args) {
        System.out.println("abacbe -> " + longestPath(new int[]{-1, 0, 0, 1, 1, 2}, "abacbe"));
        System.out.println("aabc -> " + longestPath(new int[]{-1, 0, 0, 0}, "aabc"));
    }
}
