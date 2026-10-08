import java.util.*;

/*
Problem: Seat Reservation Manager
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Design a system for n seats numbered 1..n with reserve(), which returns the smallest unreserved seat,
and unreserve(seatNumber), which frees that seat.

Why this pattern fits:
"Smallest available resource" with resources coming and going is the standard min-heap of free items.
A counter for never-used seats avoids loading all n seats into the heap up front.

Approach:
reserve: if the heap of freed seats is not empty, pop its minimum; otherwise return the next fresh number.
unreserve: push the seat number into the heap.

Worked Example:
reserve() = 1, reserve() = 2, unreserve(2), reserve() = 2, reserve() = 3, reserve() = 4, reserve() = 5.

Time Complexity: O(log n) per operation
Space Complexity: O(number of freed seats)
*/

public class P13_SeatReservationManagerHeapGreedy {
    static class SeatManager {
        private final PriorityQueue<Integer> freed = new PriorityQueue<>();
        private int nextFresh = 1;

        SeatManager(int n) {
        }

        public int reserve() {
            return freed.isEmpty() ? nextFresh++ : freed.poll();
        }

        public void unreserve(int seatNumber) {
            freed.add(seatNumber);
        }
    }

    public static void main(String[] args) {
        SeatManager manager = new SeatManager(5);
        System.out.println("reserve() -> " + manager.reserve());
        System.out.println("reserve() -> " + manager.reserve());
        manager.unreserve(2);
        for (int i = 0; i < 4; i++) {
            System.out.println("reserve() -> " + manager.reserve());
        }
    }
}
