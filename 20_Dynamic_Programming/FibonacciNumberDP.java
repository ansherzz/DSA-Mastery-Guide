import java.util.*;

/*
Problem: Fibonacci Number
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
The Fibonacci numbers are defined by F(0) = 0, F(1) = 1, and F(n) = F(n - 1) + F(n - 2) for n > 1.
    Given n, return F(n).

Why this pattern fits:
The plain recursive definition recomputes the same values an exponential number of times.
    Each value depends only on the two before it, so computing the values once in increasing order
    is the simplest possible example of dynamic programming.

Approach:
State: dp[i] is the ith Fibonacci number.
    Transition: dp[i] = dp[i - 1] + dp[i - 2].
    Only the last two values are ever needed, so keep them in two variables instead of an array.

Worked Example:
Example:
    n = 6
    Sequence: 0, 1, 1, 2, 3, 5, 8. Answer = 8.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class FibonacciNumberDP {
    public static int fib(int n) {
        if (n < 2) {
            return n;
        }

        int previous = 0;
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    public static void main(String[] args) {
        for (int n : new int[]{0, 1, 2, 6, 10, 30}) {
            System.out.println("F(" + n + ") = " + fib(n));
        }
    }
}
