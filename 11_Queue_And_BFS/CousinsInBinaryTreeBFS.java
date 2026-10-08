import java.util.*;

/*
Problem: Cousins in Binary Tree
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree with unique values and the values of two different nodes x and y,
    return true if the nodes corresponding to x and y are cousins, and false otherwise.
    Two nodes are cousins if they have the same depth but different parents.

Why this pattern fits:
"Same depth" is a statement about levels, and BFS visits a tree one level at a time.
    Checking the two children of a node together is enough to detect the "same parent" case.

Approach:
Run a level-order traversal. While expanding a node, first check whether its two children are exactly x and y;
    if so they are siblings, so return false.
    Within each level, record whether x and whether y were seen.
    If both were seen in the same level, they are cousins. If only one was seen, the depths differ, so return false.

Worked Example:
Example:
            1
           / \
          2   3
           \    \
            4    5
    4 and 5 are both at depth 2 and have different parents (2 and 3), so they are cousins.

Time Complexity: O(n)
Space Complexity: O(w), where w is the maximum width of the tree
*/

public class CousinsInBinaryTreeBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            boolean foundX = false;
            boolean foundY = false;

            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if (node.val == x) foundX = true;
                if (node.val == y) foundY = true;

                if (node.left != null && node.right != null) {
                    int a = node.left.val;
                    int b = node.right.val;
                    if ((a == x && b == y) || (a == y && b == x)) {
                        return false;
                    }
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            if (foundX || foundY) {
                return foundX && foundY;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println("4 and 5: " + isCousins(root, 4, 5));
        System.out.println("2 and 3: " + isCousins(root, 2, 3));
        System.out.println("4 and 3: " + isCousins(root, 4, 3));
    }
}
