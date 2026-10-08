import java.util.*;

/*
Problem: Design a Stack With Increment Operation
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Design a stack with a maximum size supporting push(x) (ignored when full), pop() (returns -1 when empty),
and increment(k, val), which adds val to the bottom k elements. All operations should be O(1).

Why this pattern fits:
Updating k elements directly is O(k). Since elements are only read when popped from the top,
an increment can be remembered at one position and applied when that position is popped.

Approach:
Keep values[] and extra[]. increment(k, val) adds val to extra[min(k, size) - 1].
pop() returns values[top] + extra[top] and moves extra[top] down to extra[top - 1] before clearing it.

Worked Example:
maxSize 3: push 1, push 2, pop = 2, push 2, push 3, push 4 (ignored),
increment(5, 100), increment(2, 100), pop = 103, pop = 202, pop = 201, pop = -1.

Time Complexity: O(1) per operation
Space Complexity: O(maxSize)
*/

public class P16_DesignAStackWithIncrementOperationStack {
    static class CustomStack {
        private final int[] values;
        private final int[] extra;
        private int size = 0;

        CustomStack(int maxSize) {
            values = new int[maxSize];
            extra = new int[maxSize];
        }

        public void push(int x) {
            if (size < values.length) {
                values[size++] = x;
            }
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }
            int top = --size;
            int result = values[top] + extra[top];
            if (top > 0) {
                extra[top - 1] += extra[top];
            }
            extra[top] = 0;
            return result;
        }

        public void increment(int k, int val) {
            int index = Math.min(k, size) - 1;
            if (index >= 0) {
                extra[index] += val;
            }
        }
    }

    public static void main(String[] args) {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        System.out.println("pop() -> " + stack.pop());
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.increment(5, 100);
        stack.increment(2, 100);
        for (int i = 0; i < 4; i++) {
            System.out.println("pop() -> " + stack.pop());
        }
    }
}
