import java.util.*;

/*
Problem: Maximum Number of Removable Characters
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given two strings s and p, where p is a subsequence of s.
    You are also given a distinct array removable containing indices of s.
    Choose an integer k such that after removing the characters of s at the first k indices in removable,
    p is still a subsequence of s. Return the maximum k you can choose.

Why this pattern fits:
Removing more characters can only make it harder for p to remain a subsequence.
    So if k removals work, every smaller k works too, and the answer is the boundary of a monotonic predicate.

Approach:
Record for each index of s the step at which it gets removed (or "never").
    To test a candidate k, run the standard two-pointer subsequence check,
    skipping every index whose removal step is below k.
    Binary search for the largest k that passes.

Worked Example:
Example:
    s = "abcacb", p = "ab", removable = [3, 1, 0]
    k = 2 removes indices 3 and 1, leaving "a_c_cb", which still contains "ab".
    k = 3 also removes index 0, leaving "__c_cb", which does not. Answer = 2.

Time Complexity: O((n + m) log r), where r is the length of removable
Space Complexity: O(n)
*/

public class P18_MaximumNumberOfRemovableCharactersBinarySearchAnswer {
    public static int maximumRemovals(String s, String p, int[] removable) {
        int[] removedAt = new int[s.length()];
        Arrays.fill(removedAt, Integer.MAX_VALUE);
        for (int step = 0; step < removable.length; step++) {
            removedAt[removable[step]] = step;
        }

        int low = 0;
        int high = removable.length;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (isSubsequence(s, p, removedAt, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static boolean isSubsequence(String s, String p, int[] removedAt, int k) {
        int j = 0;
        for (int i = 0; i < s.length() && j < p.length(); i++) {
            if (removedAt[i] >= k && s.charAt(i) == p.charAt(j)) {
                j++;
            }
        }
        return j == p.length();
    }

    public static void main(String[] args) {
        System.out.println(maximumRemovals("abcacb", "ab", new int[]{3, 1, 0}));
        System.out.println(maximumRemovals("abcbddddd", "abcd", new int[]{3, 2, 1, 4, 5, 6}));
        System.out.println(maximumRemovals("abcab", "abc", new int[]{0, 1, 2, 3, 4}));
    }
}
