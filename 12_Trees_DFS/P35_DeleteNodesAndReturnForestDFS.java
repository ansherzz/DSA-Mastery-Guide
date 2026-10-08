import java.util.*;

/*
Problem: Delete Nodes And Return Forest
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree where each node has a distinct value, and a list of values to delete,
    delete every node whose value is in the list. After the deletions, you are left with a forest
    (a disjoint union of trees). Return the roots of the trees in the remaining forest, in any order.

Why this pattern fits:
Whether a node becomes a new root depends on its parent (was the parent deleted?),
    and whether a node keeps a child depends on that child (was it deleted?).
    DFS handles both directions: a flag goes down, and the possibly-null replacement comes back up.

Approach:
Put the values to delete in a set. Recurse with a flag isRoot meaning "this node has no surviving parent".
    If the node survives and isRoot is true, add it to the forest.
    Recurse into the children, passing isRoot = (this node is deleted), and assign the returned nodes back as children.
    Return null to the parent if this node is deleted, otherwise return the node itself.

Worked Example:
Example:
            1
           / \
          2   3
         / \ / \
        4  5 6  7
    Deleting 3 and 5 leaves three trees with roots 1, 6, and 7. The tree at 1 keeps only 2 and 4.

Time Complexity: O(n)
Space Complexity: O(h + d), where d is the number of values to delete
*/

public class P35_DeleteNodesAndReturnForestDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int value : toDelete) {
            deleteSet.add(value);
        }

        List<TreeNode> forest = new ArrayList<>();
        dfs(root, true, deleteSet, forest);
        return forest;
    }

    private static TreeNode dfs(TreeNode node, boolean isRoot, Set<Integer> deleteSet, List<TreeNode> forest) {
        if (node == null) {
            return null;
        }

        boolean deleted = deleteSet.contains(node.val);
        if (isRoot && !deleted) {
            forest.add(node);
        }

        node.left = dfs(node.left, deleted, deleteSet, forest);
        node.right = dfs(node.right, deleted, deleteSet, forest);
        return deleted ? null : node;
    }

    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode parent = queue.poll();
            if (values[i] != null) {
                parent.left = new TreeNode(values[i]);
                queue.offer(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new TreeNode(values[i]);
                queue.offer(parent.right);
            }
            i++;
        }
        return root;
    }

    private static void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    public static void main(String[] args) {
        List<TreeNode> forest = delNodes(build(1, 2, 3, 4, 5, 6, 7), new int[]{3, 5});
        for (TreeNode tree : forest) {
            List<Integer> values = new ArrayList<>();
            preorder(tree, values);
            System.out.println("Tree with preorder " + values);
        }
    }
}
