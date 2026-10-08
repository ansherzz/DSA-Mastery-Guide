import java.util.*;

/*
Problem: Find K Pairs with Smallest Sums
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order, and an integer k.
    A pair (u, v) consists of one element from the first array and one element from the second array.
    Return the k pairs with the smallest sums.

Why this pattern fits:
There are n * m pairs, far too many to generate when only k are needed.
    For a fixed element of nums1, its pairs with nums2[0], nums2[1], ... are already in increasing order.
    So the problem is a k-way merge of sorted lists, and a min-heap always exposes the smallest unseen head.

Approach:
Seed a min-heap with the pair (i, 0) for the first min(k, n) indices i of nums1.
    Repeat k times (or until the heap is empty): pop the smallest pair (i, j) and record it,
    then push its successor in the same list, (i, j + 1), if it exists.
    Each list contributes at most one pair to the heap at a time.

Worked Example:
Example:
    nums1 = [1, 7, 11], nums2 = [2, 4, 6], k = 3
    Heap starts with (1, 2), (7, 2), (11, 2). Pop (1, 2), push (1, 4). Pop (1, 4), push (1, 6). Pop (1, 6).
    Result: [[1, 2], [1, 4], [1, 6]].

Time Complexity: O(k log(min(k, n)))
Space Complexity: O(min(k, n))
*/

public class P23_FindKPairsWithSmallestSumsHeapGreedy {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return result;
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->
            Long.compare((long) nums1[a[0]] + nums2[a[1]], (long) nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            heap.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !heap.isEmpty()) {
            int[] pair = heap.poll();
            result.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));
            if (pair[1] + 1 < nums2.length) {
                heap.offer(new int[]{pair[0], pair[1] + 1});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
        System.out.println(kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
    }
}
