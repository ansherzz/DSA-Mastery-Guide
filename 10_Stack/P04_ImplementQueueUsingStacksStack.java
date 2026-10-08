import java.util.*;

/*
Problem: Implement Queue using Stacks
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
Implement a first-in-first-out queue using only two stacks.
    The queue must support push(x), pop(), peek(), and empty().
    You may use only standard stack operations: push to top, pop from top, peek at top, size, and isEmpty.

    Follow-up: make each operation amortized O(1).

Why this pattern fits:
A stack reverses order. Reversing twice restores the original order.
    So moving elements from one stack to another turns last-in-first-out into first-in-first-out.

Approach:
Keep an input stack for pushes and an output stack for pops and peeks.
    When the output stack is empty and a pop or peek is requested, move everything from input to output.
    Each element is moved at most once, which gives amortized O(1) per operation.

Worked Example:
Example:
    push(1), push(2)  -> input = [1, 2]
    peek()            -> move to output, the top of output is 1
    pop()             -> returns 1
    empty()           -> false, because 2 is still in the queue

Time Complexity: push: O(1), pop and peek: amortized O(1)
Space Complexity: O(n)
*/

public class P04_ImplementQueueUsingStacksStack {
    static class MyQueue {
        private final Deque<Integer> input = new ArrayDeque<>();
        private final Deque<Integer> output = new ArrayDeque<>();

        public void push(int x) {
            input.push(x);
        }

        public int pop() {
            refill();
            return output.pop();
        }

        public int peek() {
            refill();
            return output.peek();
        }

        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }

        private void refill() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("peek()  -> " + queue.peek());
        System.out.println("pop()   -> " + queue.pop());
        queue.push(3);
        System.out.println("pop()   -> " + queue.pop());
        System.out.println("pop()   -> " + queue.pop());
        System.out.println("empty() -> " + queue.empty());
    }
}
