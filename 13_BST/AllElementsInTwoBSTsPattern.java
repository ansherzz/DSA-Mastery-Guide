import java.util.*;

/*
Problem: All Elements in Two Binary Search Trees
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given two binary search trees root1 and root2, return a list containing all the integers
    from both trees sorted in ascending order.

Why this pattern fits:
An in-order traversal turns each BST into a sorted list for free.
    Two sorted lists are combined with the merge step of merge sort, so no general-purpose sort is needed.

Approach:
Collect the in-order traversal of each tree into its own list.
    Merge the two lists with two indices, always taking the smaller front element.
    Append whatever remains of either list.

Worked Example:
Example:
    root1 = BST of [2, 1, 4] -> in-order [1, 2, 4]
    root2 = BST of [1, 0, 3] -> in-order [0, 1, 3]
    Merged: [0, 1, 1, 2, 3, 4].

Time Complexity: O(n + m)
Space Complexity: O(n + m)
*/

public class AllElementsInTwoBSTsPattern {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        inorder(root1, first);
        inorder(root2, second);

        List<Integer> merged = new ArrayList<>(first.size() + second.size());
        int i = 0;
        int j = 0;
        while (i < first.size() && j < second.size()) {
            if (first.get(i) <= second.get(j)) {
                merged.add(first.get(i++));
            } else {
                merged.add(second.get(j++));
            }
        }
        while (i < first.size()) merged.add(first.get(i++));
        while (j < second.size()) merged.add(second.get(j++));
        return merged;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    private static TreeNode buildBST(int... values) {
        TreeNode root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(getAllElements(buildBST(2, 1, 4), buildBST(1, 0, 3)));
        System.out.println(getAllElements(buildBST(1, 8), buildBST(8, 1)));
        System.out.println(getAllElements(null, buildBST(5)));
    }
}
