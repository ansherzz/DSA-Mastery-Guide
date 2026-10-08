import java.util.*;

/*
Problem: N-th Tribonacci Number
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
The Tribonacci sequence is defined as T(0) = 0, T(1) = 1, T(2) = 1,
    and T(n + 3) = T(n) + T(n + 1) + T(n + 2) for n >= 0.
    Given n, return the value of T(n).

Why this pattern fits:
The naive recursion branches three ways and recomputes the same values exponentially many times.
    Each value depends only on the three before it, so computing them in increasing order solves each once.

Approach:
State: T(i), the i-th Tribonacci number.
    Transition: T(i) = T(i - 1) + T(i - 2) + T(i - 3).
    Only the last three values are needed, so keep three rolling variables instead of an array.

Worked Example:
Example:
    n = 4
    T(3) = 0 + 1 + 1 = 2. T(4) = 1 + 1 + 2 = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P08_NthTribonacciNumberDP {
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }

        int first = 0;
        int second = 1;
        int third = 1;
        for (int i = 3; i <= n; i++) {
            int next = first + second + third;
            first = second;
            second = third;
            third = next;
        }
        return third;
    }

    public static void main(String[] args) {
        System.out.println(tribonacci(4));
        System.out.println(tribonacci(25));
        System.out.println(tribonacci(0));
    }
}
