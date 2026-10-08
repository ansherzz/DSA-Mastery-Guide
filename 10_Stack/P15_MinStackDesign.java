import java.util.*;

/*
Problem: Min Stack
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Design a stack that supports push, pop, top, and getMin, where getMin returns the minimum element
    currently in the stack in O(1) time.

    This is a stack design problem rather than just a single algorithm call.

Why this pattern fits:
The pattern is to keep auxiliary information synchronized with the main stack.
    Each push should also know the minimum up to that point.

Approach:
Store pairs of (value, minimum-so-far) on the stack.
    Then getMin is simply the second value in the top pair.

Worked Example:
Example operations:
    push(-2), push(0), push(-3), getMin() -> -3, pop(), top() -> 0, getMin() -> -2

Time Complexity: All operations: O(1)
Space Complexity: O(n)
*/

public class P15_MinStackDesign {
    static class MinStack {
        private final Deque<int[]> stack = new ArrayDeque<>();

        public void push(int val) {
            int currentMin = stack.isEmpty() ? val : Math.min(val, stack.peek()[1]);
            stack.push(new int[]{val, currentMin});
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        System.out.println("Current min: " + stack.getMin());
        stack.pop();
        System.out.println("Top now    : " + stack.top());
        System.out.println("Min now    : " + stack.getMin());
    }
}
