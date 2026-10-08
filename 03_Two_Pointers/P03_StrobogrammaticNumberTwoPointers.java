import java.util.*;

/*
Problem: Strobogrammatic Number
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
A strobogrammatic number looks the same when rotated 180 degrees.
Given a number as a string, return true if it is strobogrammatic.
Valid rotations: 0-0, 1-1, 8-8, 6-9, 9-6.

Why this pattern fits:
It is the palindrome check with a custom "matches" rule: the left character must rotate into the right one.

Approach:
Move left and right pointers inward (including the middle character).
For each pair, check that rotating the left digit gives the right digit.

Worked Example:
"69": rotating 6 gives 9, which matches the right end. Answer = true.
"962": 9 would need 6 on the right but finds 2. Answer = false.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P03_StrobogrammaticNumberTwoPointers {
    private static char rotate(char digit) {
        switch (digit) {
            case '0': return '0';
            case '1': return '1';
            case '8': return '8';
            case '6': return '9';
            case '9': return '6';
            default: return '?';
        }
    }

    public static boolean isStrobogrammatic(String num) {
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (rotate(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        for (String num : new String[]{"69", "88", "962"}) {
            System.out.println(num + " -> " + isStrobogrammatic(num));
        }
    }
}
