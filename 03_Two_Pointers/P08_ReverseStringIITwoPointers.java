import java.util.*;

/*
Problem: Reverse String II
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given a string s and an integer k, reverse the first k characters for every 2k characters
counting from the start. If fewer than k characters remain, reverse all of them.

Why this pattern fits:
Reversing a range in place is the basic converging-pointers swap. The only extra work is choosing the ranges.

Approach:
For start = 0, 2k, 4k, ...: set left = start and right = min(start + k - 1, n - 1), then swap inward.

Worked Example:
s = "abcdefg", k = 2
Reverse "ab" -> "ba", skip "cd", reverse "ef" -> "fe", skip "g". Answer = "bacdfeg".

Time Complexity: O(n)
Space Complexity: O(n) for the char array
*/

public class P08_ReverseStringIITwoPointers {
    public static String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int start = 0; start < chars.length; start += 2 * k) {
            int left = start;
            int right = Math.min(start + k - 1, chars.length - 1);
            while (left < right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println("abcdefg, k = 2 -> " + reverseStr("abcdefg", 2));
        System.out.println("abcd, k = 2 -> " + reverseStr("abcd", 2));
    }
}
