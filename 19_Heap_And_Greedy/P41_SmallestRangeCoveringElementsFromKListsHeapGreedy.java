import java.util.*;

/*
Problem: Smallest Range Covering Elements from K Lists
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
Given k sorted lists of integers, find the smallest range [a, b] that includes at least one number from each list.
A range is smaller if b - a is smaller, or if the widths are equal and a is smaller.

Why this pattern fits:
A candidate range is defined by one chosen element from each list.
To shrink it, the only useful move is to advance the list holding the minimum - the same step as in a k-way merge.

Approach:
Push the first element of every list and track the maximum among them.
Repeatedly: compare [min, max] with the best range, pop the minimum, and push the next element from its list
(updating the maximum). Stop when the list of the popped element is exhausted.

Worked Example:
lists = [[4, 10, 15, 24, 26], [0, 9, 12, 20], [5, 18, 22, 30]] -> the range [20, 24] contains 24, 20, and 22.

Time Complexity: O(N log k) for N total elements
Space Complexity: O(k)
*/

public class P41_SmallestRangeCoveringElementsFromKListsHeapGreedy {
    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            heap.add(new int[]{value, i, 0});
            max = Math.max(max, value);
        }
        int bestLow = heap.peek()[0];
        int bestHigh = max;
        while (true) {
            int[] smallest = heap.poll();
            if (max - smallest[0] < bestHigh - bestLow) {
                bestLow = smallest[0];
                bestHigh = max;
            }
            List<Integer> list = nums.get(smallest[1]);
            int nextIndex = smallest[2] + 1;
            if (nextIndex == list.size()) {
                break;
            }
            int value = list.get(nextIndex);
            heap.add(new int[]{value, smallest[1], nextIndex});
            max = Math.max(max, value);
        }
        return new int[]{bestLow, bestHigh};
    }

    public static void main(String[] args) {
        System.out.println("three lists -> " + Arrays.toString(smallestRange(Arrays.asList(
                Arrays.asList(4, 10, 15, 24, 26), Arrays.asList(0, 9, 12, 20), Arrays.asList(5, 18, 22, 30)))));
        System.out.println("identical lists -> " + Arrays.toString(smallestRange(Arrays.asList(
                Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)))));
    }
}
