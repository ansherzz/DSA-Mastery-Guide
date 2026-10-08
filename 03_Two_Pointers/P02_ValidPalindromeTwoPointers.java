import java.util.*;

/*
Problem: Valid Palindrome
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given a string, return true if it reads the same forward and backward after ignoring non-alphanumeric
    characters and differences in letter case.

    This is the classic mirror-check two-pointer problem.

Why this pattern fits:
The string is validated from both ends toward the center.
    Every comparison tells you which pointer can safely move next.

Approach:
Use one pointer at the start and one at the end.
    Skip characters that are not letters or digits.
    Compare lowercase versions of the remaining characters.

Worked Example:
Example:
    s = "A man, a plan, a canal: Panama"
    After cleaning, it becomes a palindrome.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P02_ValidPalindromeTwoPointers {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private static void runDemo(String s) {
        System.out.println("Input      : " + s);
        System.out.println("Palindrome?: " + isPalindrome(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("A man, a plan, a canal: Panama");
        runDemo("race a car");
    }
}
