import java.util.*;

/*
Problem: Find Target Indices After Sorting Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Sort nums in non-decreasing order and return all indices i with nums[i] == target, in increasing order.

Why this pattern fits:
Equal values are contiguous in a sorted array, so two boundary searches describe the whole answer.

Approach:
Sort. Find the first index >= target and the first index > target; output every index between them.

Worked Example:
nums = [1, 2, 5, 2, 3], target = 2 -> sorted [1, 2, 2, 3, 5]. Answer = [1, 2].

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P16_FindTargetIndicesAfterSortingArrayBinarySearch {
    private static int firstAtLeast(int[] nums, int value) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        for (int i = firstAtLeast(nums, target); i < firstAtLeast(nums, target + 1); i++) {
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        for (int target : new int[]{2, 5, 4}) {
            System.out.println("[1, 2, 5, 2, 3], target = " + target + " -> " + targetIndices(new int[]{1, 2, 5, 2, 3}, target));
        }
    }
}
