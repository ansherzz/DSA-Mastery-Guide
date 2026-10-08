import java.util.*;

/*
Problem: Merge Two Binary Trees
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
You are given two binary trees. Imagine laying one on top of the other.
    Where two nodes overlap, the merged node's value is the sum of both values.
    Where only one node exists, that node is used as it is.

    Return the merged tree. The merge must start from the roots of both trees.

Why this pattern fits:
The two trees are walked in lockstep, and the merged tree at a position depends only on
    the two nodes at that position and the merged results of their children. That is a paired DFS.

Approach:
If one of the nodes is null, the merged subtree is simply the other node.
    Otherwise create a new node holding the sum of the two values,
    and build its left and right children by merging the corresponding children.

Worked Example:
Example:
    Tree 1:     1          Tree 2:     2           Merged:      3
               / \                    / \                      / \
              3   2                  1   3                    4   5
             /                        \   \                  / \   \
            5                          4   7                5   4   7

Time Complexity: O(min(n, m))
Space Complexity: O(min(h1, h2)) recursion stack
*/

public class P12_MergeTwoBinaryTreesDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode mergeTrees(TreeNode first, TreeNode second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        TreeNode merged = new TreeNode(first.val + second.val);
        merged.left = mergeTrees(first.left, second.left);
        merged.right = mergeTrees(first.right, second.right);
        return merged;
    }

    private static void preorder(TreeNode node, List<String> out) {
        if (node == null) {
            out.add("null");
            return;
        }
        out.add(String.valueOf(node.val));
        if (node.left != null || node.right != null) {
            preorder(node.left, out);
            preorder(node.right, out);
        }
    }

    public static void main(String[] args) {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(3);
        first.right = new TreeNode(2);
        first.left.left = new TreeNode(5);

        TreeNode second = new TreeNode(2);
        second.left = new TreeNode(1);
        second.right = new TreeNode(3);
        second.left.right = new TreeNode(4);
        second.right.right = new TreeNode(7);

        List<String> out = new ArrayList<>();
        preorder(mergeTrees(first, second), out);
        System.out.println("Merged tree (preorder, null marks a missing child): " + out);
    }
}
