import java.util.*;

/*
Problem: Univalued Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
A binary tree is uni-valued if every node in the tree has the same value.
    Given the root of a binary tree, return true if the tree is uni-valued, or false otherwise.

Why this pattern fits:
The property is local and recursive: a tree is uni-valued if the root matches each of its children
    and both subtrees are uni-valued. That sentence is already the recursive function.

Approach:
For a node, check each existing child: its value must equal the node's value, and its subtree must be uni-valued.
    An empty subtree is trivially uni-valued.
    Comparing each node only with its parent is enough, because equality chains through the whole tree.

Worked Example:
Example:
            1
           / \
          1   1
         / \    \
        1   1    1
    Every node is 1, so the answer is true. Changing any one node to 2 makes it false.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P13_UnivaluedBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val != root.val) {
            return false;
        }
        if (root.right != null && root.right.val != root.val) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        System.out.println(isUnivalTree(root));

        root.left.right.val = 2;
        System.out.println(isUnivalTree(root));
    }
}
