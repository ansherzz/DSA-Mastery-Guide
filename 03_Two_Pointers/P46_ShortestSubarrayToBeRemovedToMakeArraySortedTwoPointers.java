import java.util.*;

/*
Problem: Shortest Subarray to be Removed to Make Array Sorted
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Remove one contiguous subarray (possibly empty) from arr so the remaining elements are non-decreasing.
Return the length of the shortest subarray to remove.

Why this pattern fits:
What remains is a prefix plus a suffix, each already sorted.
The only question is which prefix end and suffix start can be joined, and that is a two-pointer merge scan.

Approach:
Find the longest sorted prefix [0..left] and the longest sorted suffix [right..n-1].
Start with removing everything after the prefix or everything before the suffix.
Then with i in the prefix and j in the suffix: if arr[i] <= arr[j], removing (i, j) works, so record it and advance i;
otherwise advance j.

Worked Example:
arr = [1, 2, 3, 10, 4, 2, 3, 5]
Prefix [1, 2, 3, 10], suffix [2, 3, 5]. Joining 3 with 3 removes [10, 4, 2]. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P46_ShortestSubarrayToBeRemovedToMakeArraySortedTwoPointers {
    public static int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {
            left++;
        }
        if (left == n - 1) {
            return 0;
        }
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }
        int best = Math.min(n - left - 1, right);
        int i = 0;
        int j = right;
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                best = Math.min(best, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 10, 4, 2, 3, 5] -> " + findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5}));
        System.out.println("[5, 4, 3, 2, 1] -> " + findLengthOfShortestSubarray(new int[]{5, 4, 3, 2, 1}));
        System.out.println("[1, 2, 3] -> " + findLengthOfShortestSubarray(new int[]{1, 2, 3}));
    }
}
