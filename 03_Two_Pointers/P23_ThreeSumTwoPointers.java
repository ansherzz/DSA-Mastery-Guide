import java.util.*;

/*
Problem: 3Sum
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array, return all unique triplets [a, b, c] such that a + b + c = 0.
    The triplets themselves can be returned in any order, but duplicates are not allowed.

    This is a high-value interview problem because it combines sorting and two pointers.

Why this pattern fits:
After sorting, you can fix one value and solve the remaining two-sum problem with left and right pointers.
    Duplicate handling is the real interview challenge.

Approach:
Sort the array.
    For each index i, skip duplicates. Then place left = i + 1 and right = end.
    Move pointers according to the current sum and record triplets when the sum is zero.

Worked Example:
Example:
    nums = [-1, 0, 1, 2, -1, -4]
    One valid answer is [[-1, -1, 2], [-1, 0, 1]].

Time Complexity: O(n^2)
Space Complexity: O(1) extra space beyond the output
*/

public class P23_ThreeSumTwoPointers {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return answer;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array    : " + Arrays.toString(nums));
        System.out.println("Triplets : " + threeSum(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-1, 0, 1, 2, -1, -4});
        runDemo(new int[]{0, 0, 0, 0});
    }
}
