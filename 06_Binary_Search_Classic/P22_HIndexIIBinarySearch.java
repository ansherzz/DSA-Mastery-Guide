import java.util.*;

/*
Problem: H-Index II
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given an array citations sorted in ascending order, where citations[i] is the number of citations
    of a researcher's ith paper. Return the researcher's h-index:
    the maximum value h such that the researcher has at least h papers with at least h citations each.

    The solution must run in logarithmic time.

Why this pattern fits:
At index i there are n - i papers with at least citations[i] citations.
    The condition citations[i] >= n - i is false for a while and then true for the rest of the array.
    Finding the first true index is a boundary binary search.

Approach:
Binary search for the first index i where citations[i] >= n - i.
    At that index, n - i papers each have at least n - i citations, so the h-index is n - i.
    If no index satisfies the condition, the search ends at n and the h-index is 0.

Worked Example:
Example:
    citations = [0, 1, 3, 5, 6], n = 5
    Index 2: citations = 3 and n - i = 3, so the condition holds. It fails at index 1 (1 < 4).
    Answer = 5 - 2 = 3.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P22_HIndexIIBinarySearch {
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }

    private static void runDemo(int[] citations) {
        System.out.println("Citations: " + Arrays.toString(citations));
        System.out.println("h-index  : " + hIndex(citations));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{0, 1, 3, 5, 6});
        runDemo(new int[]{1, 2, 100});
        runDemo(new int[]{0, 0});
    }
}
