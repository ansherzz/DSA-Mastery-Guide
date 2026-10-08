import java.util.*;

/*
Problem: Contains Duplicate III
Pattern: 13. Binary Search Tree
Difficulty: Hard

Interview Prompt:
Return true if there are two distinct indices i and j with |i - j| <= indexDiff
and |nums[i] - nums[j]| <= valueDiff.

Why this pattern fits:
The index constraint is a sliding window. Inside the window we need "is there a value in [x - d, x + d]",
which a balanced BST answers in O(log k): the smallest value >= x - d either fits or nothing does.

Approach:
For each x: query ceiling(x - valueDiff); if it exists and is <= x + valueDiff return true.
Add x, and remove nums[i - indexDiff] once the window is full. Use long to avoid overflow.

Worked Example:
nums = [1, 2, 3, 1], indexDiff = 3, valueDiff = 0 -> the two 1s are 3 apart. Answer = true.

Time Complexity: O(n log k)
Space Complexity: O(k)
*/

public class P49_ContainsDuplicateIIIBST {
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> window = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long value = nums[i];
            Long neighbor = window.ceiling(value - valueDiff);
            if (neighbor != null && neighbor <= value + valueDiff) {
                return true;
            }
            window.add(value);
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 1], 3, 0 -> " + containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println("[1, 5, 9, 1, 5, 9], 2, 3 -> " + containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
