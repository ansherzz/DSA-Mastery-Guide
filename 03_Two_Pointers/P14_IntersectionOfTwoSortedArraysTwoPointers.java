import java.util.*;

/*
Problem: Intersection of Two Sorted Arrays
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given two sorted integer arrays, return their distinct common elements in sorted order
without using extra data structures besides the output.

Why this pattern fits:
Both arrays are sorted, so the smaller of the two current values can never match anything later in the other array.

Approach:
Compare a[i] and b[j]. Advance the smaller side. On equality, add the value once
(skip it if it equals the last value added) and advance both.

Worked Example:
a = [1, 2, 2, 3, 4], b = [2, 2, 4, 6] -> common values are 2 and 4.

Time Complexity: O(m + n)
Space Complexity: O(1) extra
*/

public class P14_IntersectionOfTwoSortedArraysTwoPointers {
    public static List<Integer> intersection(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                if (result.isEmpty() || result.get(result.size() - 1) != a[i]) {
                    result.add(a[i]);
                }
                i++;
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 2, 3, 4] & [2, 2, 4, 6] -> " + intersection(new int[]{1, 2, 2, 3, 4}, new int[]{2, 2, 4, 6}));
        System.out.println("[1, 3] & [2, 4] -> " + intersection(new int[]{1, 3}, new int[]{2, 4}));
    }
}
