import java.util.*;

/*
Problem: Implement Stack using Queues
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Implement a last-in-first-out stack using only queue operations.
    The stack must support push(x), pop(), top(), and empty().
    You may use only standard queue operations: add to back, remove from front, peek at front, size, and isEmpty.

    Follow-up: can you do it with a single queue?

Why this pattern fits:
A queue only exposes its front, while a stack needs access to the most recently added item.
    Rotating the queue after each push brings the newest element to the front,
    so the front of the queue always behaves like the top of the stack.

Approach:
Use one queue. On push, add the new element to the back,
    then move every element that was already in the queue from the front to the back.
    After this rotation the newest element is at the front, so pop and top are plain queue operations.

Worked Example:
Example:
    push(1)  -> queue: [1]
    push(2)  -> add 2, rotate once -> queue: [2, 1]
    top()    -> 2
    pop()    -> 2, queue: [1]
    empty()  -> false

Time Complexity: push: O(n), pop and top: O(1)
Space Complexity: O(n)
*/

public class ImplementStackUsingQueuesQueue {
    static class MyStack {
        private final Queue<Integer> queue = new ArrayDeque<>();

        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("top()   -> " + stack.top());
        System.out.println("pop()   -> " + stack.pop());
        System.out.println("pop()   -> " + stack.pop());
        System.out.println("empty() -> " + stack.empty());
        System.out.println("pop()   -> " + stack.pop());
        System.out.println("empty() -> " + stack.empty());
    }
}
