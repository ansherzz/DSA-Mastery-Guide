import java.util.*;

/*
Problem: Longest Common Prefix (Divide and Conquer)
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Return the longest common prefix shared by all strings in an array, or "" if there is none.

Why this pattern fits:
"Common prefix" is associative: LCP(all) = LCP(LCP(left half), LCP(right half)).
Any associative combine step fits the split, solve, and merge template.

Approach:
solve(low, high): a single string is its own answer. Otherwise solve both halves
and return the shared prefix of the two results.

Worked Example:
["flower", "flow", "flight"] -> left half gives "flow", right half gives "flight", combined "fl".

Time Complexity: O(total characters)
Space Complexity: O(log n) recursion depth
*/

public class P06_LongestCommonPrefixDivideConquer {
    private static String commonPrefix(String a, String b) {
        int length = 0;
        while (length < a.length() && length < b.length() && a.charAt(length) == b.charAt(length)) {
            length++;
        }
        return a.substring(0, length);
    }

    private static String solve(String[] strs, int low, int high) {
        if (low == high) {
            return strs[low];
        }
        int mid = (low + high) / 2;
        return commonPrefix(solve(strs, low, mid), solve(strs, mid + 1, high));
    }

    public static String longestCommonPrefix(String[] strs) {
        return solve(strs, 0, strs.length - 1);
    }

    public static void main(String[] args) {
        System.out.println("[flower, flow, flight] -> \"" + longestCommonPrefix(new String[]{"flower", "flow", "flight"}) + "\"");
        System.out.println("[dog, racecar, car] -> \"" + longestCommonPrefix(new String[]{"dog", "racecar", "car"}) + "\"");
    }
}
