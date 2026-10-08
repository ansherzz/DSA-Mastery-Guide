import java.util.*;

/*
Problem: Find Mode in Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree that may contain duplicates, return all the modes
    (the most frequently occurring values). If there are several modes, return them in any order.
    In this tree, a left subtree holds values less than or equal to the node, and a right subtree holds values
    greater than or equal to the node.

    Follow-up: do it without extra space for a frequency map.

Why this pattern fits:
Inorder traversal of a BST yields the values in sorted order, so equal values arrive consecutively.
    Counting runs of equal values is enough; no hash map is required.

Approach:
Traverse inorder while tracking the current value and the length of its run.
    When a run becomes longer than the best run so far, clear the result list and start it with this value.
    When a run ties the best, add the value to the list.

Worked Example:
Example:
            4
           / \
          2   6
         / \   \
        2   4   6
    Inorder: 2, 2, 4, 4, 6, 6. Every value appears twice, so the modes are [2, 4, 6].

Time Complexity: O(n)
Space Complexity: O(h) recursion stack, excluding the output
*/

public class P07_FindModeInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static Integer current;
    private static int run;
    private static int bestRun;
    private static List<Integer> modes;

    public static int[] findMode(TreeNode root) {
        current = null;
        run = 0;
        bestRun = 0;
        modes = new ArrayList<>();
        inorder(root);

        int[] result = new int[modes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = modes.get(i);
        }
        return result;
    }

    private static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        run = (current != null && current == node.val) ? run + 1 : 1;
        current = node.val;
        if (run > bestRun) {
            bestRun = run;
            modes.clear();
            modes.add(node.val);
        } else if (run == bestRun) {
            modes.add(node.val);
        }

        inorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        System.out.println("Modes: " + Arrays.toString(findMode(root)));

        TreeNode tied = new TreeNode(4);
        tied.left = new TreeNode(2);
        tied.right = new TreeNode(6);
        tied.left.left = new TreeNode(2);
        tied.left.right = new TreeNode(4);
        tied.right.right = new TreeNode(6);
        System.out.println("Modes: " + Arrays.toString(findMode(tied)));
    }
}
