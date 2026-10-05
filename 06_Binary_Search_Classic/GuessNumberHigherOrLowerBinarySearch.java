import java.util.*;

/*
Problem: Guess Number Higher or Lower
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
A number between 1 and n has been picked. You must find it using the API guess(num), which returns:
    -1 if your guess is higher than the picked number,
     1 if your guess is lower than the picked number,
     0 if your guess is correct.

    Return the picked number.

Why this pattern fits:
Every response tells you which half of the remaining range still contains the answer.
    That is the definition of binary search, just with an API instead of an array.

Approach:
Keep a range [left, right] that must contain the picked number.
    Guess the middle. On 0 return it, on -1 discard the upper half, and on 1 discard the lower half.

Worked Example:
Example:
    n = 10, picked = 6
    Guess 5 -> too low. Guess 8 -> too high. Guess 6 -> correct.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class GuessNumberHigherOrLowerBinarySearch {
    private static int picked;

    private static int guess(int num) {
        return Integer.compare(picked, num);
    }

    public static int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int response = guess(mid);
            if (response == 0) {
                return mid;
            }
            if (response < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static void runDemo(int n, int pick) {
        picked = pick;
        System.out.println("n = " + n + ", picked = " + pick + " -> guessed " + guessNumber(n));
    }

    public static void main(String[] args) {
        runDemo(10, 6);
        runDemo(1, 1);
        runDemo(2147483647, 2147483647);
    }
}
