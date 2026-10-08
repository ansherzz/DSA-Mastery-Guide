import java.util.*;

/*
Problem: Inorder Predecessor and Successor in a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given a BST and a key (which may or may not be present), return the largest value smaller than the key
and the smallest value larger than the key. Use -1 when one does not exist.

Why this pattern fits:
Each comparison during a BST descent discards half of the candidates while the current node
remains a candidate itself, so the best candidate seen on the way down is the answer.

Approach:
Predecessor: if node.val < key, record it and go right (looking for something larger but still below the key);
otherwise go left. Successor: the mirror image.

Worked Example:
Tree keys 1, 3, 4, 6, 7, 8, 10, 13, 14: for key 8 -> [7, 10]; for the absent key 5 -> [4, 6].

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P23_InorderPredecessorAndSuccessorInBST {
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

    public static int[] predecessorAndSuccessor(TreeNode root, int key) {
        int predecessor = -1;
        for (TreeNode node = root; node != null; ) {
            if (node.val < key) {
                predecessor = node.val;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        int successor = -1;
        for (TreeNode node = root; node != null; ) {
            if (node.val > key) {
                successor = node.val;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return new int[]{predecessor, successor};
    }

    public static void main(String[] args) {
        TreeNode root = build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
        for (int key : new int[]{8, 5, 1, 14}) {
            System.out.println("key " + key + " -> " + Arrays.toString(predecessorAndSuccessor(root, key)));
        }
    }
}
