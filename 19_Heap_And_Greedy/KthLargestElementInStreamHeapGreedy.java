import java.util.*;

/*
Problem: Kth Largest Element in a Stream
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
Design a class that finds the kth largest element in a stream of numbers.
    - KthLargest(k, nums) initializes the object with k and an initial list of numbers
    - add(val) appends val to the stream and returns the kth largest element seen so far

    This is the kth largest in sorted order, not the kth distinct element.

Why this pattern fits:
You never need the full sorted order, only the k largest values, and specifically the smallest of those.
    A min-heap capped at size k keeps exactly the k largest values, and its top is the kth largest.

Approach:
Keep a min-heap of at most k values.
    On add, push the new value, and if the heap grows beyond k, remove the smallest value.
    The heap's top is then the kth largest element. Initialization simply adds each starting value the same way.

Worked Example:
Example:
    k = 3, initial numbers [4, 5, 8, 2] -> heap holds 4, 5, 8
    add(3)  -> 3 is too small to stay, heap holds 4, 5, 8  -> returns 4
    add(5)  -> heap holds 5, 5, 8  -> returns 5
    add(10) -> heap holds 5, 8, 10 -> returns 5
    add(9)  -> heap holds 8, 9, 10 -> returns 8

Time Complexity: O(log k) per add
Space Complexity: O(k)
*/

public class KthLargestElementInStreamHeapGreedy {
    static class KthLargest {
        private final int k;
        private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }

    public static void main(String[] args) {
        KthLargest tracker = new KthLargest(3, new int[]{4, 5, 8, 2});
        for (int value : new int[]{3, 5, 10, 9, 4}) {
            System.out.println("add(" + value + ") -> " + tracker.add(value));
        }
    }
}
