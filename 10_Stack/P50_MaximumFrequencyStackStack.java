import java.util.*;

/*
Problem: Maximum Frequency Stack
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Design a stack-like structure with push(val) and pop(), where pop removes and returns the most frequent element.
If several elements tie, the one closest to the top of the stack is returned.

Why this pattern fits:
Pushing a value for the f-th time puts it on "level f". The most frequent, most recent element
is always the top of the highest non-empty level, so each level is an ordinary stack.

Approach:
Maintain frequency[val], a map from frequency to a stack of values, and maxFrequency.
push: increment the value's frequency and push it on that level's stack.
pop: pop from the maxFrequency stack, decrement that value's frequency, and lower maxFrequency if the level is empty.

Worked Example:
push 5, 7, 5, 7, 4, 5 -> pops return 5 (frequency 3), 7 (frequency 2, most recent), 5, then 4.

Time Complexity: O(1) per operation
Space Complexity: O(n)
*/

public class P50_MaximumFrequencyStackStack {
    static class FreqStack {
        private final Map<Integer, Integer> frequency = new HashMap<>();
        private final Map<Integer, Deque<Integer>> levels = new HashMap<>();
        private int maxFrequency = 0;

        public void push(int val) {
            int f = frequency.merge(val, 1, Integer::sum);
            maxFrequency = Math.max(maxFrequency, f);
            levels.computeIfAbsent(f, k -> new ArrayDeque<>()).push(val);
        }

        public int pop() {
            Deque<Integer> top = levels.get(maxFrequency);
            int val = top.pop();
            frequency.merge(val, -1, Integer::sum);
            if (top.isEmpty()) {
                maxFrequency--;
            }
            return val;
        }
    }

    public static void main(String[] args) {
        FreqStack stack = new FreqStack();
        for (int val : new int[]{5, 7, 5, 7, 4, 5}) {
            stack.push(val);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("pop() -> " + stack.pop());
        }
    }
}
