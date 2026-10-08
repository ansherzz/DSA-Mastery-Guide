import java.util.*;

/*
Problem: Validate Stack Sequences
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given two integer arrays pushed and popped, each with distinct values,
    return true if this could have been the result of a sequence of push and pop operations
    on an initially empty stack, and false otherwise.

Why this pattern fits:
The question is about what a stack can do, so the most reliable solution is to run one.
    The only decision is when to pop, and popping as early as possible is always safe because values are distinct.

Approach:
Push the values in the given order. After each push, pop while the top of the stack equals the next value
    that popped expects, advancing the popped pointer each time.
    If every value was popped by the end, the sequences are consistent.

Worked Example:
Example:
    pushed = [1, 2, 3, 4, 5], popped = [4, 5, 3, 2, 1]
    Push 1, 2, 3, 4; pop 4. Push 5; pop 5, 3, 2, 1. The stack is empty, so the answer is true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P22_ValidateStackSequencesStack {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int next = 0;

        for (int value : pushed) {
            stack.push(value);
            while (!stack.isEmpty() && stack.peek() == popped[next]) {
                stack.pop();
                next++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }
}
