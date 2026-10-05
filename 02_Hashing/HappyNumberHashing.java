import java.util.*;

/*
Problem: Happy Number
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
A happy number is defined by this process: starting with a positive integer,
    replace the number by the sum of the squares of its digits, and repeat.
    If the process reaches 1, the number is happy. If it loops forever in a cycle that never includes 1, it is not.

    Return true if n is a happy number.

Why this pattern fits:
The process either reaches 1 or revisits a number it has produced before.
    A hash set of seen values is the simplest way to detect that revisit.

Approach:
Keep generating the next number.
    Stop with true if the number becomes 1.
    Stop with false if the number is already in the seen set, because the sequence is now cycling.

Worked Example:
Example:
    n = 19
    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1, so 19 is happy.

Time Complexity: O(log n) per step, with a small bounded number of steps
Space Complexity: O(number of values visited)
*/

public class HappyNumberHashing {
    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1) {
            if (!seen.add(n)) {
                return false;
            }
            n = sumOfDigitSquares(n);
        }
        return true;
    }

    private static int sumOfDigitSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    private static void runDemo(int n) {
        System.out.println("n = " + n + " -> happy? " + isHappy(n));
    }

    public static void main(String[] args) {
        runDemo(19);
        runDemo(2);
        runDemo(7);
    }
}
