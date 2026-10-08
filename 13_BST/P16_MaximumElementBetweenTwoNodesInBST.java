import java.util.*;

/*
Problem: Maximum Element Between Two Nodes in a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given a BST and two keys that both exist in it, return the largest value on the path between them.

Why this pattern fits:
Everything on the path to the smaller key is in the LCA's left subtree, hence smaller than the LCA.
So only the stretch from the LCA to the larger key needs to be examined.

Approach:
Find the split node as in the LCA search. Walk from it to max(a, b), tracking the largest value passed.

Worked Example:
Tree [8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13], keys 4 and 13 -> path includes 14. Answer = 14.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P16_MaximumElementBetweenTwoNodesInBST {
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

    public static int maxOnPath(TreeNode root, int a, int b) {
        TreeNode node = root;
        while ((a < node.val && b < node.val) || (a > node.val && b > node.val)) {
            node = a < node.val ? node.left : node.right;
        }
        int target = Math.max(a, b);
        int best = node.val;
        while (node.val != target) {
            node = target < node.val ? node.left : node.right;
            best = Math.max(best, node.val);
        }
        return best;
    }

    public static void main(String[] args) {
        TreeNode root = build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
        System.out.println("4 and 13 -> " + maxOnPath(root, 4, 13));
        System.out.println("1 and 7 -> " + maxOnPath(root, 1, 7));
        System.out.println("1 and 4 -> " + maxOnPath(root, 1, 4));
    }
}
