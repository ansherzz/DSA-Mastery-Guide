import java.util.*;

/*
Problem: Maximize Sum Of Array After K Negations
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
You must choose an index and replace nums[i] with -nums[i] exactly k times (the same index may be chosen repeatedly).
Return the largest possible sum of the array afterwards.

Why this pattern fits:
Flipping the smallest value gains the most (or loses the least). After a flip the smallest value may change,
so the structure must keep returning the current minimum: a min-heap.

Approach:
Put all numbers in a min-heap. Repeat k times: pop the minimum, negate it, push it back. Sum the heap.

Worked Example:
nums = [3, -1, 0, 2], k = 3 -> flip -1 to 1, then flip 0 twice. Sum = 6.

Time Complexity: O(n + k log n)
Space Complexity: O(n)
*/

public class P03_MaximizeSumOfArrayAfterKNegationsHeapGreedy {
    public static int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int value : nums) {
            heap.add(value);
        }
        for (int i = 0; i < k; i++) {
            heap.add(-heap.poll());
        }
        int sum = 0;
        for (int value : heap) {
            sum += value;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("[4, 2, 3], k = 1 -> " + largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        System.out.println("[3, -1, 0, 2], k = 3 -> " + largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        System.out.println("[2, -3, -1, 5, -4], k = 2 -> " + largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
    }
}
