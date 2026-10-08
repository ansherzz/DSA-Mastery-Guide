import java.util.*;

/*
Problem: Minimize the Maximum of Two Arrays
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Build two arrays of distinct positive integers: arr1 has uniqueCnt1 numbers, none divisible by divisor1;
arr2 has uniqueCnt2 numbers, none divisible by divisor2; no number appears in both.
Return the minimum possible maximum value across both arrays.

Why this pattern fits:
With a limit x, the usable numbers are counted by division: x - x / d are not divisible by d.
More room never hurts, so the smallest sufficient x is found by bisection.

Approach:
x is enough when: numbers not divisible by divisor1 >= uniqueCnt1, numbers not divisible by divisor2 >= uniqueCnt2,
and numbers not divisible by both (x - x / lcm) >= uniqueCnt1 + uniqueCnt2.

Worked Example:
divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3 -> arr1 = [1], arr2 = [2, 3, 4]. Answer = 4.

Time Complexity: O(log(answer))
Space Complexity: O(1)
*/

public class P26_MinimizeTheMaximumOfTwoArraysBinarySearchAnswer {
    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long lcm = (long) divisor1 / gcd(divisor1, divisor2) * divisor2;
        long low = 1;
        long high = 4_000_000_000L;
        while (low < high) {
            long mid = low + (high - low) / 2;
            boolean enough = mid - mid / divisor1 >= uniqueCnt1
                    && mid - mid / divisor2 >= uniqueCnt2
                    && mid - mid / lcm >= (long) uniqueCnt1 + uniqueCnt2;
            if (enough) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int) low;
    }

    public static void main(String[] args) {
        System.out.println("2, 7, 1, 3 -> " + minimizeSet(2, 7, 1, 3));
        System.out.println("3, 5, 2, 1 -> " + minimizeSet(3, 5, 2, 1));
        System.out.println("2, 4, 8, 2 -> " + minimizeSet(2, 4, 8, 2));
    }
}
