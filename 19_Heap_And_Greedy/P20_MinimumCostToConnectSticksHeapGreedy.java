import java.util.*;

/*
Problem: Minimum Cost to Connect Sticks
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Connecting two sticks of lengths x and y costs x + y and produces one stick of length x + y.
Return the minimum total cost to connect all sticks into one.

Why this pattern fits:
A stick's length is paid again every time it takes part in a merge, so short sticks should be merged early
and long sticks late. That is the Huffman coding argument, and it needs the two smallest items repeatedly.

Approach:
Put all lengths in a min-heap. While more than one stick remains: pop two, add their sum to the cost, push the sum.

Worked Example:
sticks = [1, 8, 3, 5] -> 1 + 3 = 4, then 4 + 5 = 9, then 9 + 8 = 17. Total = 30.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P20_MinimumCostToConnectSticksHeapGreedy {
    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int stick : sticks) {
            heap.add(stick);
        }
        int cost = 0;
        while (heap.size() > 1) {
            int merged = heap.poll() + heap.poll();
            cost += merged;
            heap.add(merged);
        }
        return cost;
    }

    public static void main(String[] args) {
        System.out.println("[2, 4, 3] -> " + connectSticks(new int[]{2, 4, 3}));
        System.out.println("[1, 8, 3, 5] -> " + connectSticks(new int[]{1, 8, 3, 5}));
        System.out.println("[5] -> " + connectSticks(new int[]{5}));
    }
}
