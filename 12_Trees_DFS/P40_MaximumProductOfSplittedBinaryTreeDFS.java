import java.util.*;

/*
Problem: Maximum Product of Splitted Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Remove one edge to split the tree into two subtrees so that the product of their sums is maximized.
Return the maximum product modulo 1e9 + 7.

Why this pattern fits:
Cutting the edge above a node separates exactly that node's subtree.
So the candidates are all subtree sums, which a postorder DFS produces.

Approach:
First DFS: the total sum. Second DFS: for every subtree sum s, update best = max(best, s * (total - s)) using long.
Apply the modulo only at the end.

Worked Example:
[1, 2, 3, 4, 5, 6] -> cutting above node 2 gives sums 11 and 10. Answer = 110.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P40_MaximumProductOfSplittedBinaryTreeDFS {
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

    private static long total;
    private static long best;

    private static long subtreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        long sum = node.val + subtreeSum(node.left) + subtreeSum(node.right);
        best = Math.max(best, sum * (total - sum));
        return sum;
    }

    public static int maxProduct(TreeNode root) {
        total = 0;
        total = subtreeSum(root);
        best = 0;
        subtreeSum(root);
        return (int) (best % 1_000_000_007L);
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 4, 5, 6] -> " + maxProduct(build(1, 2, 3, 4, 5, 6)));
        System.out.println("[1, null, 2, 3, 4, null, null, 5, 6] -> " + maxProduct(build(1, null, 2, 3, 4, null, null, 5, 6)));
    }
}
