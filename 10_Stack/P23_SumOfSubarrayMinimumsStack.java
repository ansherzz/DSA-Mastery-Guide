import java.util.*;

/*
Problem: Sum of Subarray Minimums
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given an array of integers arr, find the sum of min(b), where b ranges over every contiguous subarray of arr.
    Since the answer may be large, return it modulo 10^9 + 7.

Why this pattern fits:
Instead of listing subarrays, count for each element how many subarrays have it as their minimum.
    That count depends on the nearest smaller element on each side, which a monotonic stack finds in linear time.

Approach:
For index i, let left be the distance to the previous element that is strictly smaller,
    and right be the distance to the next element that is smaller or equal.
    Then arr[i] is the minimum of left * right subarrays.
    Using "strictly smaller" on one side and "smaller or equal" on the other assigns each subarray with tied minimums
    to exactly one index, so nothing is double counted.
    A single increasing stack resolves both sides: an index is popped when its next smaller-or-equal element arrives,
    and the index beneath it on the stack is its previous strictly smaller element.

Worked Example:
Example:
    arr = [3, 1, 2, 4]
    3 is the minimum of 1 subarray, 1 of 6 subarrays, 2 of 2 subarrays, 4 of 1 subarray.
    3 * 1 + 1 * 6 + 2 * 2 + 4 * 1 = 17.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P23_SumOfSubarrayMinimumsStack {
    public static int sumSubarrayMins(int[] arr) {
        final long mod = 1_000_000_007L;
        int n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        long total = 0;

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || arr[stack.peek()] >= arr[i])) {
                int middle = stack.pop();
                int previousSmaller = stack.isEmpty() ? -1 : stack.peek();
                long left = middle - previousSmaller;
                long right = i - middle;
                total = (total + arr[middle] * left % mod * right) % mod;
            }
            stack.push(i);
        }
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4}));
        System.out.println(sumSubarrayMins(new int[]{11, 81, 94, 43, 3}));
        System.out.println(sumSubarrayMins(new int[]{2, 2, 2}));
    }
}
