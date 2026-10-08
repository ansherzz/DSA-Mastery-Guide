import java.util.*;

/*
Problem: Numbers With Same Consecutive Differences
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Return all integers of length n in which the absolute difference between every two consecutive digits is k.
Numbers may not have leading zeros.

Why this pattern fits:
The number is built position by position and the constraint only involves the last digit,
so each partial number has at most two valid extensions - a narrow decision tree.

Approach:
Start from each first digit 1..9. From a number ending in d, append d - k and d + k when they are valid digits
(only once when k == 0). Record the number when it has n digits.

Worked Example:
n = 3, k = 7 -> 181, 292, 707, 818, 929.

Time Complexity: O(2^n)
Space Complexity: O(n)
*/

public class P09_NumbersWithSameConsecutiveDifferencesBacktracking {
    private static void extend(int number, int remaining, int k, List<Integer> result) {
        if (remaining == 0) {
            result.add(number);
            return;
        }
        int last = number % 10;
        if (last - k >= 0) {
            extend(number * 10 + last - k, remaining - 1, k, result);
        }
        if (k != 0 && last + k <= 9) {
            extend(number * 10 + last + k, remaining - 1, k, result);
        }
    }

    public static List<Integer> numsSameConsecDiff(int n, int k) {
        List<Integer> result = new ArrayList<>();
        for (int first = 1; first <= 9; first++) {
            extend(first, n - 1, k, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 3, k = 7 -> " + numsSameConsecDiff(3, 7));
        System.out.println("n = 2, k = 1 -> " + numsSameConsecDiff(2, 1));
    }
}
