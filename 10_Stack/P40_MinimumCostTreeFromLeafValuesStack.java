import java.util.*;

/*
Problem: Minimum Cost Tree From Leaf Values
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
arr holds the leaf values of a binary tree in inorder. Every non-leaf node's value is the product of
the largest leaf in its left subtree and the largest leaf in its right subtree.
Return the smallest possible sum of the non-leaf node values.

Why this pattern fits:
Building the tree is repeatedly merging two adjacent values a and b at cost a * b and keeping max(a, b).
The smaller value disappears, so each value should be removed by its cheapest larger neighbor -
the next-greater-element structure of a monotonic stack.

Approach:
Keep a decreasing stack (with an infinite sentinel). When a value a arrives and the top is <= a,
pop mid and pay mid * min(new top, a). Push a. At the end, combine the remaining values from the top down.

Worked Example:
arr = [6, 2, 4] -> 2 is removed by 4 (cost 8), then 4 by 6 (cost 24). Answer = 32.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P40_MinimumCostTreeFromLeafValuesStack {
    public static int mctFromLeafValues(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.MAX_VALUE);
        int cost = 0;
        for (int value : arr) {
            while (stack.peek() <= value) {
                int mid = stack.pop();
                cost += mid * Math.min(stack.peek(), value);
            }
            stack.push(value);
        }
        while (stack.size() > 2) {
            cost += stack.pop() * stack.peek();
        }
        return cost;
    }

    public static void main(String[] args) {
        System.out.println("[6, 2, 4] -> " + mctFromLeafValues(new int[]{6, 2, 4}));
        System.out.println("[4, 11] -> " + mctFromLeafValues(new int[]{4, 11}));
    }
}
