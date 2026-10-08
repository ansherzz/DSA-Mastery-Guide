import java.util.*;

/*
Problem: Merge Two BSTs into a Balanced BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given two BSTs, build a single height-balanced BST containing all keys of both.

Why this pattern fits:
Inserting one tree's keys into the other can produce a badly skewed tree.
Going through sorted order instead costs linear time and gives full control over the final shape.

Approach:
Inorder each tree into a list. Merge the two sorted lists. Build recursively: the middle element becomes the root,
the left half its left subtree, and the right half its right subtree.

Worked Example:
Trees with keys {1, 2, 4} and {3, 5} -> merged 1, 2, 3, 4, 5 -> root 3 with subtrees built from [1, 2] and [4, 5].

Time Complexity: O(n1 + n2)
Space Complexity: O(n1 + n2)
*/

public class P42_MergeTwoBSTsIntoBalancedBST {
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

    // Prints a tree in level order, using null for a missing child.
    private static String format(TreeNode root) {
        List<String> out = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add("null");
                continue;
            }
            out.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1).equals("null")) {
            out.remove(out.size() - 1);
        }
        return out.toString();
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static TreeNode buildBalanced(List<Integer> values, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = buildBalanced(values, low, mid - 1);
        root.right = buildBalanced(values, mid + 1, high);
        return root;
    }

    public static TreeNode mergeBSTs(TreeNode root1, TreeNode root2) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        inorder(root1, first);
        inorder(root2, second);
        List<Integer> merged = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < first.size() || j < second.size()) {
            if (j == second.size() || (i < first.size() && first.get(i) <= second.get(j))) {
                merged.add(first.get(i++));
            } else {
                merged.add(second.get(j++));
            }
        }
        return buildBalanced(merged, 0, merged.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println("{1, 2, 4} + {3, 5} -> " + format(mergeBSTs(build(2, 1, 4), build(3, null, 5))));
    }
}
