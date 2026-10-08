import java.util.*;

/*
Problem: Longest Palindrome
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given a string s consisting of lowercase and uppercase letters,
    return the length of the longest palindrome that can be built with those letters.
    Letters are case sensitive, and you may use each letter occurrence at most once.

Why this pattern fits:
The order of the letters does not matter, only how many of each you have.
    That is a frequency-counting question.

Approach:
A palindrome uses letters in mirrored pairs, plus at most one unpaired letter in the center.
    Count each character. Every character contributes count / 2 pairs, which is (count / 2) * 2 letters.
    If any character has an odd count, one leftover letter can sit in the middle, adding 1.

Worked Example:
Example:
    s = "abccccdd"
    Pairs: cc, cc, dd -> 6 letters. One of a or b can be the center -> 7. For instance "dccaccd".

Time Complexity: O(n)
Space Complexity: O(1), bounded by the alphabet size
*/

public class P19_LongestPalindromeHashing {
    public static int longestPalindrome(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char ch : s.toCharArray()) {
            counts.merge(ch, 1, Integer::sum);
        }

        int length = 0;
        boolean hasOdd = false;
        for (int count : counts.values()) {
            length += (count / 2) * 2;
            if (count % 2 == 1) {
                hasOdd = true;
            }
        }
        return hasOdd ? length + 1 : length;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("Aa"));
    }
}
