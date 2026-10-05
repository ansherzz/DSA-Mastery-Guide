import java.util.*;

/*
Problem: Design Circular Queue
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Design a circular queue (ring buffer) with a fixed capacity k. Do not use a built-in queue.
    - enQueue(value): insert at the rear; return false if the queue is full
    - deQueue(): remove from the front; return false if the queue is empty
    - Front(): return the front item, or -1 if empty
    - Rear(): return the last item, or -1 if empty
    - isEmpty() and isFull()

Why this pattern fits:
Understanding how a queue is built on an array is a common interview check.
    A naive array queue wastes the space freed at the front. Wrapping indices with modulo reuses that space,
    which is how BFS queues and bounded buffers are implemented in practice.

Approach:
Store elements in an array of length k. Track the index of the front element and the current count.
    The rear position is derived: (front + count - 1) % k.
    Enqueue writes at (front + count) % k and increases the count.
    Dequeue advances front by one (modulo k) and decreases the count.

Worked Example:
Example with k = 3:
    enQueue(1), enQueue(2), enQueue(3) -> true, true, true
    enQueue(4) -> false (full)
    Rear() -> 3
    deQueue() -> true (removes 1)
    enQueue(4) -> true (reuses the freed slot)
    Rear() -> 4, Front() -> 2

Time Complexity: O(1) for every operation
Space Complexity: O(k)
*/

public class DesignCircularQueue {
    static class MyCircularQueue {
        private final int[] data;
        private int front = 0;
        private int count = 0;

        MyCircularQueue(int k) {
            data = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            data[(front + count) % data.length] = value;
            count++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % data.length;
            count--;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : data[front];
        }

        public int Rear() {
            return isEmpty() ? -1 : data[(front + count - 1) % data.length];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == data.length;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        System.out.println("enQueue(1) -> " + queue.enQueue(1));
        System.out.println("enQueue(2) -> " + queue.enQueue(2));
        System.out.println("enQueue(3) -> " + queue.enQueue(3));
        System.out.println("enQueue(4) -> " + queue.enQueue(4));
        System.out.println("Rear()     -> " + queue.Rear());
        System.out.println("isFull()   -> " + queue.isFull());
        System.out.println("deQueue()  -> " + queue.deQueue());
        System.out.println("enQueue(4) -> " + queue.enQueue(4));
        System.out.println("Rear()     -> " + queue.Rear());
        System.out.println("Front()    -> " + queue.Front());
    }
}
