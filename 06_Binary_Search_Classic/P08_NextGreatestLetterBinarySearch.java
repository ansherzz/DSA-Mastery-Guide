import java.util.*;

/*
Problem: Find Smallest Letter Greater Than Target
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
You are given an array of lowercase letters sorted in non-decreasing order and a target letter.
    Return the smallest letter in the array that is strictly greater than the target.
    If no such letter exists, return the first letter of the array (the letters wrap around).

Why this pattern fits:
This is an "upper bound" search: the first position whose value is strictly greater than the target.
    The wrap-around rule only affects what to return when that position is past the end.

Approach:
Binary search for the first index whose letter is greater than the target.
    Use the half-open range [left, right) so the result can equal letters.length.
    Return letters[left % letters.length] to handle the wrap-around case.

Worked Example:
Example:
    letters = ['c', 'f', 'j'], target = 'c' -> 'f'
    letters = ['c', 'f', 'j'], target = 'j' -> nothing is greater, so wrap around to 'c'

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P08_NextGreatestLetterBinarySearch {
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[left % letters.length];
    }

    private static void runDemo(char[] letters, char target) {
        System.out.println("Letters: " + Arrays.toString(letters) + ", target = " + target);
        System.out.println("Answer : " + nextGreatestLetter(letters, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new char[]{'c', 'f', 'j'}, 'a');
        runDemo(new char[]{'c', 'f', 'j'}, 'c');
        runDemo(new char[]{'c', 'f', 'j'}, 'j');
    }
}
