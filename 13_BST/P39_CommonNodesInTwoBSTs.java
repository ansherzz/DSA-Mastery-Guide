import java.util.*;

/*
Problem: Common Nodes in Two BSTs
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the roots of two BSTs, return the values that appear in both trees in ascending order,
using only O(h1 + h2) extra space.

Why this pattern fits:
Each BST yields its keys in sorted order through an inorder iterator (a stack).
Intersecting two sorted streams is the classic merge-style two-pointer step.

Approach:
Keep one stack per tree, each pushed down its left chain. Compare the two tops:
equal -> record and advance both; otherwise advance the smaller side (pop, then push the left chain of its right child).

Worked Example:
First tree keys 0, 1, 4, 5, 7, 9, 10; second tree keys 4, 7, 9, 10, 20 -> [4, 7, 9, 10].

Time Complexity: O(n1 + n2)
Space Complexity: O(h1 + h2)
*/

public class P39_CommonNodesInTwoBSTs {
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

    private static void pushLeft(Deque<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static List<Integer> commonNodes(TreeNode root1, TreeNode root2) {
        List<Integer> common = new ArrayList<>();
        Deque<TreeNode> first = new ArrayDeque<>();
        Deque<TreeNode> second = new ArrayDeque<>();
        pushLeft(first, root1);
        pushLeft(second, root2);
        while (!first.isEmpty() && !second.isEmpty()) {
            int a = first.peek().val;
            int b = second.peek().val;
            if (a == b) {
                common.add(a);
            }
            if (a <= b) {
                pushLeft(first, first.pop().right);
            }
            if (b <= a) {
                pushLeft(second, second.pop().right);
            }
        }
        return common;
    }

    public static void main(String[] args) {
        TreeNode a = build(5, 1, 10, 0, 4, 7, null, null, null, null, null, null, 9);
        TreeNode b = build(10, 7, 20, 4, 9);
        System.out.println("common keys -> " + commonNodes(a, b));
    }
}
