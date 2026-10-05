import java.util.*;

/*
Problem: Subtree of Another Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the roots of two binary trees root and subRoot, return true if there is a node in root
    whose entire subtree (that node and all of its descendants) is identical to subRoot.

Why this pattern fits:
Two recursive questions are layered:
    "is the tree rooted here identical to subRoot?" and "if not, does the answer hold somewhere below?".
    The outer DFS visits each candidate node and the inner DFS compares two trees.

Approach:
If root is null, there is nothing to match, so return false.
    If the tree rooted at the current node is identical to subRoot, return true.
    Otherwise search the left subtree and the right subtree.
    The identity check is the Same Tree comparison.

Worked Example:
Example:
    root:      3            subRoot:   4
              / \                     / \
             4   5                   1   2
            / \
           1   2
    The subtree rooted at node 4 matches exactly. Answer = true.
    If node 2 in root had an extra child, the match would fail, because the whole subtree must be identical.

Time Complexity: O(n * m) in the worst case
Space Complexity: O(h) recursion stack
*/

public class SubtreeOfAnotherTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (isSame(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        System.out.println("Is subtree? " + isSubtree(root, subRoot));

        root.left.right.left = new TreeNode(0);
        System.out.println("After adding an extra node below 2: " + isSubtree(root, subRoot));
    }
}
