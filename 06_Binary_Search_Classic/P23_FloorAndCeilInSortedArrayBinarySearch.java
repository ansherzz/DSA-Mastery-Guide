import java.util.*;

/*
Problem: Floor and Ceil in a Sorted Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a sorted array of distinct positive integers and a value x, return the floor and the ceil of x.
    The floor is the largest element less than or equal to x.
    The ceil is the smallest element greater than or equal to x.
    Use -1 for a floor or ceil that does not exist.

Why this pattern fits:
Floor and ceil are the two neighbors of the position where x would be inserted.
    Many interview problems (closest element, predecessor, successor) reduce to exactly this boundary search.

Approach:
Run a standard binary search for x. If it is found, both floor and ceil are x.
    Otherwise, every time the search moves right past nums[mid], that value is a floor candidate,
    and every time it moves left past nums[mid], that value is a ceil candidate.
    The last candidates recorded are the tightest ones.

Worked Example:
Example:
    nums = [1, 2, 8, 10, 12, 19], x = 5
    Floor is 2 and ceil is 8.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P23_FloorAndCeilInSortedArrayBinarySearch {
    public static int[] floorAndCeil(int[] nums, int x) {
        int floor = -1;
        int ceil = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == x) {
                return new int[]{x, x};
            }
            if (nums[mid] < x) {
                floor = nums[mid];
                left = mid + 1;
            } else {
                ceil = nums[mid];
                right = mid - 1;
            }
        }
        return new int[]{floor, ceil};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 8, 10, 12, 19};
        System.out.println("x = 5  -> " + Arrays.toString(floorAndCeil(nums, 5)));
        System.out.println("x = 10 -> " + Arrays.toString(floorAndCeil(nums, 10)));
        System.out.println("x = 0  -> " + Arrays.toString(floorAndCeil(nums, 0)));
        System.out.println("x = 20 -> " + Arrays.toString(floorAndCeil(nums, 20)));
    }
}
