import java.util.*;

/*
Problem: Diameter of Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
The diameter of a binary tree is the number of edges on the longest path between any two nodes.
    Return the diameter.

    The longest path through a node is leftHeight + rightHeight.

Why this pattern fits:
This problem teaches a powerful DFS pattern:
    the recursive function returns one thing upward (height) while updating another thing globally (diameter).

Approach:
Use postorder DFS to compute the height of each subtree.
    At each node, update the best diameter with leftHeight + rightHeight.

Worked Example:
Example:
    In the tree [1,2,3,4,5], the diameter is 3.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P03_DiameterOfBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int diameter;

    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Diameter: " + diameterOfBinaryTree(root));
    }
}
