import java.util.*;

/*
Problem: Total Cost to Hire K Workers
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Hire k workers in k sessions. In each session choose the cheapest worker among the first `candidates`
and the last `candidates` remaining workers, breaking ties by the smaller index. Return the total cost.

Why this pattern fits:
Each session needs the minimum of two moving windows at the two ends of the array.
Two heaps hold those windows, and two pointers feed in the next unseen workers as hires are made.

Approach:
Fill the left heap from the front and the right heap from the back, never letting the pointers cross.
For each session take the smaller top (the left heap wins ties), add its cost,
and refill that heap from its side if unseen workers remain.

Worked Example:
costs = [17, 12, 10, 2, 7, 2, 11, 20, 8], k = 3, candidates = 4 -> hire 2, 2, and 7. Total = 11.

Time Complexity: O((k + candidates) log candidates)
Space Complexity: O(candidates)
*/

public class P39_TotalCostToHireKWorkersHeapGreedy {
    public static long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();
        int i = 0;
        int j = costs.length - 1;
        long total = 0;
        for (int session = 0; session < k; session++) {
            while (left.size() < candidates && i <= j) {
                left.add(costs[i++]);
            }
            while (right.size() < candidates && i <= j) {
                right.add(costs[j--]);
            }
            int leftBest = left.isEmpty() ? Integer.MAX_VALUE : left.peek();
            int rightBest = right.isEmpty() ? Integer.MAX_VALUE : right.peek();
            if (leftBest <= rightBest) {
                total += left.poll();
            } else {
                total += right.poll();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("k = 3, candidates = 4 -> " + totalCost(new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8}, 3, 4));
        System.out.println("k = 3, candidates = 3 -> " + totalCost(new int[]{1, 2, 4, 1}, 3, 3));
    }
}
