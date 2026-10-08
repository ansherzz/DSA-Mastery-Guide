import java.util.*;

/*
Problem: Top K Frequent Elements (Bucket Sort)
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given an integer array and an integer k, return the k most frequent elements.
The answer is guaranteed to be unique. Aim for better than O(n log n).

Why this pattern fits:
A frequency is at most n, so frequencies can be used directly as array indices.
That turns "sort by count" into a linear bucket pass.

Approach:
Count each value. Create n + 1 buckets where bucket f holds all values that occur f times.
Walk the buckets from highest frequency down until k values are collected.

Worked Example:
nums = [1, 1, 1, 2, 2, 3], k = 2
bucket[3] = [1], bucket[2] = [2], bucket[1] = [3]. Reading from the top gives [1, 2].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P32_TopKFrequentElementsBucketHashing {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int value : nums) {
            count.merge(value, 1, Integer::sum);
        }
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        List<Integer> result = new ArrayList<>();
        for (int f = nums.length; f >= 1 && result.size() < k; f--) {
            for (int value : buckets.get(f)) {
                if (result.size() < k) {
                    result.add(value);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, 1, 1, 2, 2, 3], k = 2 -> " + topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println("[1], k = 1 -> " + topKFrequent(new int[]{1}, 1));
    }
}
