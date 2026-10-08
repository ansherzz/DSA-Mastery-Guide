import java.util.*;

/*
Problem: Design Circular Deque
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Design a circular double-ended queue with a fixed capacity k. Implement:
    insertFront, insertLast, deleteFront, deleteLast (each returns true if the operation succeeds),
    getFront, getRear (each returns -1 if the deque is empty), isEmpty, and isFull.

Why this pattern fits:
A fixed array used as a ring gives O(1) insertion and removal at both ends without shifting elements.
    This is how ArrayDeque works internally, so building it explains the data structure every BFS relies on.

Approach:
Store the elements in an array of length k, with front as the index of the first element and size as the count.
    The last element is at (front + size - 1) % k.
    Inserting at the front moves front one step backward: (front - 1 + k) % k.
    Inserting at the back writes at (front + size) % k.
    Deleting from the front moves front forward; deleting from the back only decreases size.
    Tracking size avoids the classic ambiguity between a full ring and an empty ring.

Worked Example:
Example:
    capacity 3: insertLast(1), insertLast(2), insertFront(3) -> deque is [3, 1, 2]
    insertFront(4) fails because the deque is full. getRear() = 2.
    deleteLast() -> [3, 1], insertFront(4) -> [4, 3, 1], getFront() = 4.

Time Complexity: O(1) per operation
Space Complexity: O(k)
*/

public class P38_DesignCircularDequeQueue {
    static class MyCircularDeque {
        private final int[] data;
        private int front = 0;
        private int size = 0;

        MyCircularDeque(int k) {
            data = new int[k];
        }

        boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            front = (front - 1 + data.length) % data.length;
            data[front] = value;
            size++;
            return true;
        }

        boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            data[(front + size) % data.length] = value;
            size++;
            return true;
        }

        boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % data.length;
            size--;
            return true;
        }

        boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            size--;
            return true;
        }

        int getFront() {
            return isEmpty() ? -1 : data[front];
        }

        int getRear() {
            return isEmpty() ? -1 : data[(front + size - 1) % data.length];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == data.length;
        }
    }

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);
        System.out.println(deque.insertLast(1));
        System.out.println(deque.insertLast(2));
        System.out.println(deque.insertFront(3));
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getRear());
        System.out.println(deque.isFull());
        System.out.println(deque.deleteLast());
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getFront());
    }
}
