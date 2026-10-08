import java.util.*;

/*
Problem: All Possible Full Binary Trees
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
A full binary tree is one where every node has 0 or 2 children.
Return the number of structurally different full binary trees with exactly n nodes (and build them).

Why this pattern fits:
The root uses one node and the remaining n - 1 nodes are divided between two smaller full binary trees.
Every way of dividing them, combined with every pair of sub-solutions, yields a distinct tree.

Approach:
trees(n): for n = 1 return a single node. For each odd left size i from 1 to n - 2,
pair every tree from trees(i) with every tree from trees(n - 1 - i) under a new root. Memoize by n.
Even n has no solutions.

Worked Example:
n = 7 -> left sizes 1, 3, 5 give 1 * 2 + 1 * 1 + 2 * 1 = 5 trees.

Time Complexity: O(2^(n/2)) trees are produced
Space Complexity: O(2^(n/2))
*/

public class P26_AllPossibleFullBinaryTreesDivideConquer {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static final Map<Integer, List<TreeNode>> MEMO = new HashMap<>();

    public static List<TreeNode> allPossibleFBT(int n) {
        if (MEMO.containsKey(n)) {
            return MEMO.get(n);
        }
        List<TreeNode> result = new ArrayList<>();
        if (n == 1) {
            result.add(new TreeNode(0));
        } else if (n % 2 == 1) {
            for (int leftSize = 1; leftSize < n; leftSize += 2) {
                for (TreeNode left : allPossibleFBT(leftSize)) {
                    for (TreeNode right : allPossibleFBT(n - 1 - leftSize)) {
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        result.add(root);
                    }
                }
            }
        }
        MEMO.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        for (int n : new int[]{3, 4, 7}) {
            System.out.println("n = " + n + " -> " + allPossibleFBT(n).size());
        }
    }
}
