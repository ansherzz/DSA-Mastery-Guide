import java.util.*;

/*
Problem: Reverse Vowels of a String
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given a string s, reverse only the vowels of the string and return the result.
    The vowels are a, e, i, o, u in both lowercase and uppercase. All other characters stay in place.

Why this pattern fits:
Reversing means swapping the first with the last, the second with the second-last, and so on.
    Here the swap partners are the vowels, so each pointer skips non-vowels until it finds one.

Approach:
Convert the string to a char array and place pointers at both ends.
    Advance the left pointer until it sits on a vowel and retreat the right pointer until it sits on a vowel.
    Swap the two vowels, then move both pointers inward.

Worked Example:
Example:
    s = "leetcode"
    Vowels in order: e, e, o, e. Reversed: e, o, e, e.
    Answer = "leotcede"

Time Complexity: O(n)
Space Complexity: O(n) for the char array
*/

public class P07_ReverseVowelsTwoPointers {
    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            while (left < right && !isVowel(chars[left])) {
                left++;
            }
            while (left < right && !isVowel(chars[right])) {
                right--;
            }

            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    private static boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    private static void runDemo(String s) {
        System.out.println("Input  : " + s);
        System.out.println("Output : " + reverseVowels(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("hello");
        runDemo("leetcode");
        runDemo("IceCreAm");
    }
}
