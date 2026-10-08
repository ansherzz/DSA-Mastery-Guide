import java.util.*;

/*
Problem: Reverse String
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Write a function that reverses a string given as an array of characters.
    You must do this by modifying the input array in place with O(1) extra memory.

Why this pattern fits:
This is a basic in-place array manipulation exercise.
    It trains index arithmetic and swapping, which appear inside many larger array problems.

Approach:
The character at index i must end up at index n - 1 - i.
    Walk i over the first half of the array and swap s[i] with s[n - 1 - i].
    The middle element of an odd-length array stays where it is.

Worked Example:
Example:
    s = [h, e, l, l, o]
    Swap h <-> o, then e <-> l. Result: [o, l, l, e, h].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class ReverseStringArrayBasics {
    public static void reverseString(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        char[] word = "hello".toCharArray();
        System.out.println("Before: " + Arrays.toString(word));
        reverseString(word);
        System.out.println("After : " + Arrays.toString(word));

        char[] even = "Hannah".toCharArray();
        reverseString(even);
        System.out.println("After : " + Arrays.toString(even));
    }
}
