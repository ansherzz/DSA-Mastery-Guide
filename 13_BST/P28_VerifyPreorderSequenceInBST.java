import java.util.*;

/*
Problem: Verify Preorder Sequence in Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence
    of some binary search tree.

Why this pattern fits:
In a preorder traversal of a BST, once the traversal turns right from some node,
    every later value must be greater than that node. The sequence is valid exactly when that rule is never broken.

Approach:
Keep a stack of ancestors whose right subtrees have not been entered yet, and a lower bound (initially minus infinity).
    For each value: if it is below the lower bound, the sequence is invalid.
    While the value is greater than the top of the stack, pop: the traversal has moved into that ancestor's right subtree,
    so the popped value becomes the new lower bound.
    Then push the value.
    The stack stays decreasing from bottom to top, mirroring a path of left turns.

Worked Example:
Example:
    preorder = [5, 2, 6, 1, 3]
    After 6 arrives, 2 and 5 are popped and the lower bound becomes 5. The next value 1 is below 5, so the answer is false.
    preorder = [5, 2, 1, 3, 6] is valid.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P28_VerifyPreorderSequenceInBST {
    public static boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int value : preorder) {
            if (value < lowerBound) {
                return false;
            }
            while (!stack.isEmpty() && value > stack.peek()) {
                lowerBound = stack.pop();
            }
            stack.push(value);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(verifyPreorder(new int[]{5, 2, 1, 3, 6}));
        System.out.println(verifyPreorder(new int[]{5, 2, 6, 1, 3}));
        System.out.println(verifyPreorder(new int[]{1, 2, 3}));
    }
}
