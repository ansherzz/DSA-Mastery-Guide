import java.util.*;

/*
Problem: Kth Largest Element in an Array using a Heap
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given an integer array nums and an integer k, return the kth largest element in the array.
    This is the kth largest in sorted order, not the kth distinct element.

    The quickselect version is in folder 15. Solve it here with a heap.

Why this pattern fits:
The kth largest element is the smallest member of the k largest elements.
    A min-heap that never holds more than k values keeps exactly that group,
    and its top is the answer. This uses O(k) memory and works even when the data arrives as a stream.

Approach:
Push each number into a min-heap.
    Whenever the heap holds more than k values, remove the smallest one.
    After all numbers are processed, the top of the heap is the kth largest.

Worked Example:
Example:
    nums = [3, 2, 1, 5, 6, 4], k = 2
    The heap ends up holding the two largest values, 5 and 6. Its top is 5.

Time Complexity: O(n log k)
Space Complexity: O(k)
*/

public class KthLargestElementInArrayHeapGreedy {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums = " + Arrays.toString(nums) + ", k = " + k + " -> " + findKthLargest(nums, k));
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 2, 1, 5, 6, 4}, 2);
        runDemo(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
    }
}
