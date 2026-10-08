import java.util.*;

/*
Problem: Happy Number (Fast and Slow)
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Starting from n, repeatedly replace the number with the sum of the squares of its digits.
n is happy if this process reaches 1. Return true if n is happy. Use O(1) extra space.

Why this pattern fits:
The sequence either reaches 1 or falls into a cycle. That is a linked list with a possible loop,
so fast and slow pointers detect the cycle without a hash set.

Approach:
slow moves one step, fast moves two. Stop when fast reaches 1 (happy) or slow == fast (cycle, not happy).

Worked Example:
n = 19 -> 82 -> 68 -> 100 -> 1, so 19 is happy.
n = 2 enters the cycle 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4.

Time Complexity: O(log n) per step, with a bounded number of steps
Space Complexity: O(1)
*/

public class P20_HappyNumberTwoPointers {
    private static int next(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = next(n);
        while (fast != 1 && slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return fast == 1;
    }

    public static void main(String[] args) {
        System.out.println("19 -> " + isHappy(19));
        System.out.println("2 -> " + isHappy(2));
    }
}
