import java.util.*;

/*
Problem: Find the Distance Value Between Two Arrays
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given two integer arrays arr1 and arr2 and an integer d, return the distance value between the two arrays.
    The distance value is the number of elements arr1[i] such that there is no element arr2[j]
    with |arr1[i] - arr2[j]| <= d.

Why this pattern fits:
For each value x of arr1, the question is whether arr2 has any element inside the interval [x - d, x + d].
    On a sorted arr2, the first element >= x - d is the only one that needs to be checked.

Approach:
Sort arr2. For each x in arr1, find the lower bound of x - d in arr2.
    If that position is past the end, or the element there is greater than x + d, no element of arr2 is within d of x,
    so x counts toward the answer.

Worked Example:
Example:
    arr1 = [4, 5, 8], arr2 = [10, 9, 1, 8], d = 2
    4 and 5 have no element of arr2 within distance 2. 8 is within distance 2 of 8, 9, and 10. Answer = 2.

Time Complexity: O((n + m) log m)
Space Complexity: O(1) besides the sort
*/

public class P25_FindTheDistanceValueBetweenTwoArraysBinarySearch {
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int x : arr1) {
            int index = lowerBound(arr2, x - d);
            if (index == arr2.length || arr2[index] > x + d) {
                count++;
            }
        }
        return count;
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(findTheDistanceValue(new int[]{4, 5, 8}, new int[]{10, 9, 1, 8}, 2));
        System.out.println(findTheDistanceValue(new int[]{1, 4, 2, 3}, new int[]{-4, -3, 6, 10, 20, 30}, 3));
        System.out.println(findTheDistanceValue(new int[]{2, 1, 100, 3}, new int[]{-5, -2, 10, -3, 7}, 6));
    }
}
