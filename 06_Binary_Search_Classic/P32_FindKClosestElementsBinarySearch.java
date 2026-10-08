import java.util.*;

/*
Problem: Find K Closest Elements
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given a sorted integer array arr and two integers k and x, return the k integers in the array closest to x.
    The result must also be sorted in ascending order.
    An integer a is closer to x than b if |a - x| < |b - x|, or if the distances are equal and a < b.

Why this pattern fits:
The answer is always a contiguous window of length k, so the only unknown is where the window starts.
    Comparing the two values just outside the edges of a candidate window tells you which way to shift it,
    which makes the start index binary-searchable.

Approach:
Search the start index in the range [0, n - k].
    For a candidate start mid, compare x - arr[mid] with arr[mid + k] - x.
    If the left edge is farther from x than the element just past the right edge, the window should move right.
    Otherwise the window should stay or move left.

Worked Example:
Example:
    arr = [1, 2, 3, 4, 5], k = 4, x = 3
    Start 0: x - arr[0] = 2 and arr[4] - x = 2. Not farther on the left, so keep start 0.
    Answer = [1, 2, 3, 4].

Time Complexity: O(log(n - k) + k)
Space Complexity: O(1) extra, excluding the output
*/

public class P32_FindKClosestElementsBinarySearch {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    private static void runDemo(int[] arr, int k, int x) {
        System.out.println("Array  : " + Arrays.toString(arr) + ", k = " + k + ", x = " + x);
        System.out.println("Closest: " + findClosestElements(arr, k, x));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5}, 4, 3);
        runDemo(new int[]{1, 2, 3, 4, 5}, 4, -1);
        runDemo(new int[]{1, 1, 2, 2, 2, 2, 2, 3, 3}, 3, 3);
    }
}
