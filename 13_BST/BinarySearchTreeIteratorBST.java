import java.util.*;

/*
Problem: Binary Search Tree Iterator
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Implement an iterator over the inorder traversal of a binary search tree.
    - BSTIterator(root) initializes the iterator
    - hasNext() returns true if there is a next value in ascending order
    - next() returns the next smallest value

    next() and hasNext() should run in average O(1) time and use O(h) memory, where h is the tree height.

Why this pattern fits:
Flattening the whole tree into a list would use O(n) memory.
    Instead, pause an inorder traversal and resume it on demand.
    An explicit stack holds exactly the nodes whose left subtree is done but which have not been returned yet.

Approach:
On construction, push the root and all of its left descendants onto the stack.
    The top of the stack is always the next smallest node.
    On next(), pop the top, then push its right child together with that child's entire left chain.
    hasNext() simply checks whether the stack is empty.

Worked Example:
Example:
            7
           / \
          3   15
             /  \
            9    20
    Calls to next() return 3, 7, 9, 15, 20, and hasNext() becomes false after that.

Time Complexity: next and hasNext: amortized O(1)
Space Complexity: O(h)
*/

public class BinarySearchTreeIteratorBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class BSTIterator {
        private final Deque<TreeNode> stack = new ArrayDeque<>();

        BSTIterator(TreeNode root) {
            pushLeftChain(root);
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeftChain(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushLeftChain(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        System.out.println("next()    -> " + iterator.next());
        System.out.println("next()    -> " + iterator.next());
        System.out.println("hasNext() -> " + iterator.hasNext());

        List<Integer> rest = new ArrayList<>();
        while (iterator.hasNext()) {
            rest.add(iterator.next());
        }
        System.out.println("remaining -> " + rest);
        System.out.println("hasNext() -> " + iterator.hasNext());
    }
}
