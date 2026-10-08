import java.util.*;

/*
Problem: Compare Version Numbers
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Compare two version strings such as "1.2.10" and "1.2.9".
Revisions are separated by dots and compared as integers (leading zeros are ignored).
Missing revisions count as 0. Return -1, 0, or 1.

Why this pattern fits:
This is tokenizing plus an element-wise comparison of two sequences of different lengths.

Approach:
Split both strings on '.'.
Loop up to the longer length, parsing each revision or using 0 when one side has run out.
Return at the first difference.

Worked Example:
"1.2.10" vs "1.2.9": the first two revisions tie, then 10 > 9, so the answer is 1.
"1.0" vs "1.0.0": every revision is equal, so the answer is 0.

Time Complexity: O(m + n)
Space Complexity: O(m + n)
*/

public class P36_CompareVersionNumbersArrayBasics {
    public static int compareVersion(String version1, String version2) {
        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        for (int i = 0; i < Math.max(a.length, b.length); i++) {
            int x = i < a.length ? Integer.parseInt(a[i]) : 0;
            int y = i < b.length ? Integer.parseInt(b[i]) : 0;
            if (x != y) {
                return x < y ? -1 : 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("1.01 vs 1.001 -> " + compareVersion("1.01", "1.001"));
        System.out.println("1.0 vs 1.0.0 -> " + compareVersion("1.0", "1.0.0"));
        System.out.println("0.1 vs 1.1 -> " + compareVersion("0.1", "1.1"));
        System.out.println("1.2.10 vs 1.2.9 -> " + compareVersion("1.2.10", "1.2.9"));
    }
}
