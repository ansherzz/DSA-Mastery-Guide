import java.util.*;

/*
Problem: Reverse First K Elements of a Queue
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given a queue and an integer k, reverse the order of the first k elements
while keeping the remaining elements in their original relative order.

Why this pattern fits:
A queue preserves order and a stack reverses it. Combining them reverses just a prefix
using only queue and stack operations.

Approach:
Pop k elements from the queue onto a stack. Pop the stack back into the queue (now reversed, but at the back).
Finally move the other n - k elements from the front to the back.

Worked Example:
queue = [1, 2, 3, 4, 5], k = 3 -> after the stack step: [4, 5, 3, 2, 1]; after rotating two: [3, 2, 1, 4, 5].

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class P11_ReverseFirstKElementsOfQueueQueue {
    public static Deque<Integer> reverseFirstK(Deque<Integer> queue, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        for (int i = queue.size() - k; i > 0; i--) {
            queue.add(queue.poll());
        }
        return queue;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 4, 5], k = 3 -> " + reverseFirstK(new ArrayDeque<>(Arrays.asList(1, 2, 3, 4, 5)), 3));
        System.out.println("[10, 20], k = 2 -> " + reverseFirstK(new ArrayDeque<>(Arrays.asList(10, 20)), 2));
    }
}
