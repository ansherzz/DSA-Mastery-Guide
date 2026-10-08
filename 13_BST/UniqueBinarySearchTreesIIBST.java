import java.util.*;

/*
Problem: Unique Binary Search Trees II
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given an integer n, return all the structurally unique binary search trees
    that have exactly n nodes with unique values from 1 to n. The answer may be returned in any order.

Why this pattern fits:
Choosing the root value splits the remaining values by the BST property:
    everything smaller must form the left subtree and everything larger must form the right subtree.
    Each side is the same problem on a smaller range.

Approach:
Write generate(low, high), which returns every BST that can be built from the values low..high.
    If low > high, the only option is the empty tree, so return a list containing null.
    Otherwise, for each root value in the range, generate all left subtrees from (low, root - 1)
    and all right subtrees from (root + 1, high), and create one tree for every left/right combination.

Worked Example:
Example:
    n = 3 produces 5 trees. In preorder (with children shown in brackets):
    1[null, 2[null, 3]], 1[null, 3[2, null]], 2[1, 3], 3[1[null, 2], null], 3[2[1, null], null].

Time Complexity: O(Catalan(n) * n), which is the size of the output
Space Complexity: O(Catalan(n) * n)
*/

public class UniqueBinarySearchTreesIIBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generate(1, n);
    }

    private static List<TreeNode> generate(int low, int high) {
        List<TreeNode> trees = new ArrayList<>();
        if (low > high) {
            trees.add(null);
            return trees;
        }

        for (int rootValue = low; rootValue <= high; rootValue++) {
            List<TreeNode> leftTrees = generate(low, rootValue - 1);
            List<TreeNode> rightTrees = generate(rootValue + 1, high);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(rootValue);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }
        return trees;
    }

    private static String describe(TreeNode node) {
        if (node == null) {
            return "null";
        }
        if (node.left == null && node.right == null) {
            return String.valueOf(node.val);
        }
        return node.val + "[" + describe(node.left) + ", " + describe(node.right) + "]";
    }

    public static void main(String[] args) {
        List<TreeNode> trees = generateTrees(3);
        System.out.println("Number of trees for n = 3: " + trees.size());
        for (TreeNode tree : trees) {
            System.out.println(describe(tree));
        }
        System.out.println("Number of trees for n = 5: " + generateTrees(5).size());
    }
}
