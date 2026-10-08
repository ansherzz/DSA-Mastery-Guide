import java.util.*;

/*
Problem: Top K Frequent Elements
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given an integer array and an integer k, return the k most frequent elements.
    The answer can be returned in any order.

    A heap is a natural way to keep the best k candidates by frequency.

Why this pattern fits:
You first count frequencies, then repeatedly need the highest-frequency items.
    That dynamic 'best available' retrieval is a heap signal.

Approach:
Count frequencies with a hashmap.
    Push [value, frequency] pairs into a max-heap ordered by frequency.
    Pop k times.

Worked Example:
Example:
    nums = [1,1,1,2,2,3], k = 2
    The answer is [1,2].

Time Complexity: O(n log n) with a max-heap
Space Complexity: O(n)
*/

public class P07_TopKFrequentElementsHeapGreedy {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            maxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            answer[i] = maxHeap.poll()[0];
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }
}
