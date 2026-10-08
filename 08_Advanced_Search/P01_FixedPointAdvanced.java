import java.util.*;

/*
Problem: Fixed Point
Pattern: 08. Advanced Search
Difficulty: Easy

Interview Prompt:
Given an array of distinct integers arr sorted in ascending order,
    return the smallest index i that satisfies arr[i] == i. If there is no such index, return -1.

Why this pattern fits:
The target is not a given value; it is a relationship between a value and its own index.
    Binary search still applies once you find the hidden monotonic quantity: arr[i] - i.

Approach:
Because the integers are distinct and sorted, arr[i + 1] >= arr[i] + 1, so arr[i] - i never decreases.
    The predicate "arr[i] >= i" is therefore false for a prefix and true for the rest.
    Binary search for the first index where arr[i] >= i, then check whether equality actually holds there.
    That index is automatically the smallest fixed point.

Worked Example:
Example:
    arr = [-10, -5, 0, 3, 7]
    arr[i] - i = -10, -6, -2, 0, 3. The first non-negative entry is at index 3, and arr[3] == 3. Answer = 3.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P01_FixedPointAdvanced {
    public static int fixedPoint(int[] arr) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left < arr.length && arr[left] == left ? left : -1;
    }

    public static void main(String[] args) {
        System.out.println(fixedPoint(new int[]{-10, -5, 0, 3, 7}));
        System.out.println(fixedPoint(new int[]{0, 2, 5, 8, 17}));
        System.out.println(fixedPoint(new int[]{-10, -5, 3, 4, 7, 9}));
    }
}
