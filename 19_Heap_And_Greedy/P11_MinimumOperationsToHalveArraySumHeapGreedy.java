import java.util.*;

/*
Problem: Minimum Operations to Halve Array Sum
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
In one operation you choose any number and reduce it to exactly half (it may be halved again later).
Return the minimum number of operations to reduce the array's sum by at least half.

Why this pattern fits:
Each operation removes half of the chosen number, so the largest number gives the biggest reduction.
The halved value goes back into the pool and may again be the largest, which requires a heap.

Approach:
Push all values into a max-heap of doubles. While the removed amount is less than half of the original sum:
pop the largest, add half of it to the removed amount, and push the other half back.

Worked Example:
nums = [5, 19, 8, 1] -> halve 19, then 9.5, then 8: removed 9.5 + 4.75 + 4 = 18.25 >= 16.5. Answer = 3.

Time Complexity: O(n + k log n)
Space Complexity: O(n)
*/

public class P11_MinimumOperationsToHalveArraySumHeapGreedy {
    public static int halveArray(int[] nums) {
        PriorityQueue<Double> heap = new PriorityQueue<>(Collections.reverseOrder());
        double sum = 0;
        for (int value : nums) {
            heap.add((double) value);
            sum += value;
        }
        double removed = 0;
        int operations = 0;
        while (removed < sum / 2) {
            double half = heap.poll() / 2;
            removed += half;
            heap.add(half);
            operations++;
        }
        return operations;
    }

    public static void main(String[] args) {
        System.out.println("[5, 19, 8, 1] -> " + halveArray(new int[]{5, 19, 8, 1}));
        System.out.println("[3, 8, 20] -> " + halveArray(new int[]{3, 8, 20}));
    }
}
