import java.util.*;

/*
Problem: Last Stone Weight
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
You have a collection of stones with positive integer weights.
    On each turn, take the two heaviest stones and smash them together.
    If they weigh the same, both are destroyed. Otherwise the lighter one is destroyed
    and the heavier one's weight is reduced by the lighter one's weight.

    Return the weight of the last remaining stone, or 0 if no stones remain.

Why this pattern fits:
Every turn needs the two largest values of a collection that keeps changing.
    A max-heap provides the largest value in O(log n) even as values are removed and added back.

Approach:
Put all stones into a max-heap.
    While at least two stones remain, pop the two heaviest. If they differ, push the difference back.
    When the loop ends, return the remaining stone's weight or 0 if the heap is empty.

Worked Example:
Example:
    stones = [2, 7, 4, 1, 8, 1]
    Smash 8 and 7 -> 1. Stones: [2, 4, 1, 1, 1]
    Smash 4 and 2 -> 2. Stones: [2, 1, 1, 1]
    Smash 2 and 1 -> 1. Stones: [1, 1, 1]
    Smash 1 and 1 -> both destroyed. Stones: [1]
    Answer = 1.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P02_LastStoneWeightHeapGreedy {
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int heaviest = maxHeap.poll();
            int second = maxHeap.poll();
            if (heaviest != second) {
                maxHeap.offer(heaviest - second);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeight(new int[]{1}));
        System.out.println(lastStoneWeight(new int[]{3, 3}));
    }
}
