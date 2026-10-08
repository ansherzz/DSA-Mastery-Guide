import java.util.*;

/*
Problem: Flip Equivalent Binary Trees
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
A flip swaps the left and right children of a node.
Return true if one tree can be made equal to another by some number of flips.

Why this pattern fits:
It is the "same tree" recursion with one extra option at every node: compare the children crosswise.

Approach:
Both null -> true. One null or different values -> false.
Otherwise return (left~left and right~right) or (left~right and right~left).

Worked Example:
Flipping nodes 1, 3, and 5 of the first tree gives the second tree. Answer = true.

Time Complexity: O(min(n1, n2)) when values are unique
Space Complexity: O(h)
*/

public class P22_FlipEquivalentBinaryTreesDFS {
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

    public static boolean flipEquiv(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return (flipEquiv(a.left, b.left) && flipEquiv(a.right, b.right))
                || (flipEquiv(a.left, b.right) && flipEquiv(a.right, b.left));
    }

    public static void main(String[] args) {
        TreeNode a = build(1, 2, 3, 4, 5, 6, null, null, null, 7, 8);
        TreeNode b = build(1, 3, 2, null, 6, 4, 5, null, null, null, null, 8, 7);
        System.out.println("three flips apart -> " + flipEquiv(a, b));
        System.out.println("[1, 2, 3] vs [1, 2, 4] -> " + flipEquiv(build(1, 2, 3), build(1, 2, 4)));
    }
}
