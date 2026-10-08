import java.util.*;

/*
Problem: Valid Perfect Square
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a positive integer num, return true if num is a perfect square and false otherwise.
    Do not use any built-in square root function.

Why this pattern fits:
The function x * x is strictly increasing for positive x.
    So you can binary search the integer x whose square equals num, exactly like searching a sorted array.

Approach:
Search x in the range [1, num].
    Compare mid * mid with num using long arithmetic to avoid overflow.
    Return true on an exact match; otherwise move toward the side where the match could be.

Worked Example:
Example:
    num = 16 -> mid = 8 (64 too big), mid = 4 (16 matches). Answer = true.
    num = 14 -> the search narrows between 3 (9) and 4 (16) without a match. Answer = false.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P06_ValidPerfectSquareBinarySearch {
    public static boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square == num) {
                return true;
            }
            if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    private static void runDemo(int num) {
        System.out.println("num = " + num + " -> perfect square? " + isPerfectSquare(num));
    }

    public static void main(String[] args) {
        runDemo(16);
        runDemo(14);
        runDemo(1);
        runDemo(2147395600);
    }
}
