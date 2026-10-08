import java.util.*;

/*
Problem: First Bad Version
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
You have n versions of a product, numbered 1 to n. One version introduced a bug,
    so that version and every version after it are bad.
    You are given an API isBadVersion(version) that tells you whether a version is bad.

    Find the first bad version while calling the API as few times as possible.

Why this pattern fits:
The versions form a pattern of good, good, ..., good, bad, bad, ..., bad.
    Whenever answers go from false to true exactly once, binary search can find the boundary.

Approach:
Search the range [1, n]. If mid is bad, the first bad version is mid or something earlier, so keep mid.
    If mid is good, the first bad version must be after mid.
    When the range shrinks to one version, that version is the answer.

Worked Example:
Example:
    n = 5, first bad version = 4
    mid = 3 is good -> search [4, 5]. mid = 4 is bad -> search [4, 4]. Answer = 4.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P05_FirstBadVersionBinarySearch {
    private static int hiddenFirstBad;

    private static boolean isBadVersion(int version) {
        return version >= hiddenFirstBad;
    }

    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int n, int firstBad) {
        hiddenFirstBad = firstBad;
        System.out.println("n = " + n + ", hidden first bad = " + firstBad);
        System.out.println("Found: " + firstBadVersion(n));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(5, 4);
        runDemo(1, 1);
        runDemo(2126753390, 1702766719);
    }
}
