import java.util.*;

/*
Problem: Design Front Middle Back Queue
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Design a queue supporting pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
When there are two middle positions, operate on the frontmost one. Pops return -1 when empty.

Why this pattern fits:
A single deque cannot reach its middle in O(1). Two deques holding the front half and the back half
make the middle the tail of the first or the head of the second.

Approach:
Invariant: back.size() equals front.size() or front.size() + 1. Rebalance after every operation.
pushMiddle goes to the head of `back` when the halves are equal, otherwise to the tail of `front`.
popMiddle takes the tail of `front` when the halves are equal, otherwise the head of `back`.

Worked Example:
pushFront(1), pushBack(2), pushMiddle(3), pushMiddle(4) -> [1, 4, 3, 2].
popFront = 1, popMiddle = 3, popMiddle = 4, popBack = 2, popFront = -1.

Time Complexity: O(1) per operation
Space Complexity: O(n)
*/

public class P39_DesignFrontMiddleBackQueueQueue {
    static class FrontMiddleBackQueue {
        private final Deque<Integer> front = new ArrayDeque<>();
        private final Deque<Integer> back = new ArrayDeque<>();

        private void rebalance() {
            if (front.size() > back.size()) {
                back.addFirst(front.pollLast());
            } else if (back.size() > front.size() + 1) {
                front.addLast(back.pollFirst());
            }
        }

        public void pushFront(int val) {
            front.addFirst(val);
            rebalance();
        }

        public void pushMiddle(int val) {
            if (front.size() == back.size()) {
                back.addFirst(val);
            } else {
                front.addLast(val);
            }
        }

        public void pushBack(int val) {
            back.addLast(val);
            rebalance();
        }

        public int popFront() {
            if (back.isEmpty()) {
                return -1;
            }
            int val = front.isEmpty() ? back.pollFirst() : front.pollFirst();
            rebalance();
            return val;
        }

        public int popMiddle() {
            if (back.isEmpty()) {
                return -1;
            }
            return front.size() == back.size() ? front.pollLast() : back.pollFirst();
        }

        public int popBack() {
            if (back.isEmpty()) {
                return -1;
            }
            int val = back.pollLast();
            rebalance();
            return val;
        }
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();
        queue.pushFront(1);
        queue.pushBack(2);
        queue.pushMiddle(3);
        queue.pushMiddle(4);
        System.out.println("popFront() -> " + queue.popFront());
        System.out.println("popMiddle() -> " + queue.popMiddle());
        System.out.println("popMiddle() -> " + queue.popMiddle());
        System.out.println("popBack() -> " + queue.popBack());
        System.out.println("popFront() -> " + queue.popFront());
    }
}
