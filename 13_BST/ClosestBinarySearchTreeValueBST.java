import java.util.*;

/*
Problem: Closest Binary Search Tree Value
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a non-empty binary search tree and a floating-point target,
    return the value in the tree that is closest to the target.
    If two values are equally close, return the smaller one.

Why this pattern fits:
The closest value must lie on the search path that the target itself would follow.
    At each node, the subtree on the far side of the target can only contain values that are even farther away,
    so a single root-to-leaf walk is sufficient.

Approach:
Walk down from the root. At each node, compare its distance to the target with the best distance so far,
    preferring the smaller value on a tie.
    Move left if the target is smaller than the node's value, otherwise move right.

Worked Example:
Example:
            4
           / \
          2   5
         / \
        1   3
    target = 3.714: visit 4 (distance 0.286), then 2 (distance 1.714), then 3 (distance 0.714).
    The closest value is 4.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class ClosestBinarySearchTreeValueBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int closestValue(TreeNode root, double target) {
        int closest = root.val;

        for (TreeNode node = root; node != null; node = target < node.val ? node.left : node.right) {
            double distance = Math.abs(node.val - target);
            double bestDistance = Math.abs(closest - target);
            if (distance < bestDistance || (distance == bestDistance && node.val < closest)) {
                closest = node.val;
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println("Closest to 3.714: " + closestValue(root, 3.714));
        System.out.println("Closest to 3.5  : " + closestValue(root, 3.5));
        System.out.println("Closest to -8.0 : " + closestValue(root, -8.0));
    }
}
