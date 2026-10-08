import java.util.*;

/*
Problem: Pseudo-Palindromic Paths in a Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Node values are digits 1..9. A root-to-leaf path is pseudo-palindromic if some permutation of its values
forms a palindrome. Return the number of such paths.

Why this pattern fits:
A multiset can be arranged into a palindrome when at most one value has an odd count.
Parities fit in a bitmask that is passed by value, so no backtracking is needed.

Approach:
Toggle bit `val` in the mask at each node. At a leaf, the path qualifies if mask & (mask - 1) == 0.

Worked Example:
[2, 3, 1, 3, 1, null, 1] -> paths 2-3-3 and 2-1-1 qualify; 2-3-1 does not. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P33_PseudoPalindromicPathsInABinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Builds a tree from level-order values, using null for a missing child.
    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.add(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    private static int dfs(TreeNode node, int mask) {
        if (node == null) {
            return 0;
        }
        mask ^= 1 << node.val;
        if (node.left == null && node.right == null) {
            return (mask & (mask - 1)) == 0 ? 1 : 0;
        }
        return dfs(node.left, mask) + dfs(node.right, mask);
    }

    public static int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    public static void main(String[] args) {
        System.out.println("[2, 3, 1, 3, 1, null, 1] -> " + pseudoPalindromicPaths(build(2, 3, 1, 3, 1, null, 1)));
        System.out.println("[2, 1, 1, 1, 3, null, null, null, null, null, 1] -> "
                + pseudoPalindromicPaths(build(2, 1, 1, 1, 3, null, null, null, null, null, 1)));
        System.out.println("[9] -> " + pseudoPalindromicPaths(build(9)));
    }
}
