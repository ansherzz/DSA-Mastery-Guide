import java.util.*;

/*
Problem: Kth Smallest Number in Multiplication Table
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
Consider the m x n multiplication table where the entry in row i and column j (both 1-indexed) is i * j.
    Given m, n, and k, return the kth smallest number in the table.

Why this pattern fits:
The table can have up to 9 * 10^8 entries, so it cannot be built or sorted.
    But counting how many entries are <= x is cheap: row i contributes min(n, x / i) entries.
    That count is monotonic in x, so you can binary search for the smallest x with count >= k.

Approach:
Search x between 1 and m * n.
    For each candidate, sum min(n, x / i) over all rows i from 1 to m.
    If the count is at least k, the answer is x or smaller; otherwise it is larger.

Worked Example:
Example:
    m = 3, n = 3, k = 5
    Table: [1, 2, 3], [2, 4, 6], [3, 6, 9]. Sorted: 1, 2, 2, 3, 3, 4, 6, 6, 9.
    Count of entries <= 2 is 3 (too few). Count of entries <= 3 is 5. Answer = 3.

Time Complexity: O(m log(m * n))
Space Complexity: O(1)
*/

public class P40_KthSmallestInMultiplicationTableBinarySearchAnswer {
    private static int countAtMost(int m, int n, int value) {
        int count = 0;
        for (int row = 1; row <= m; row++) {
            count += Math.min(n, value / row);
        }
        return count;
    }

    public static int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countAtMost(m, n, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int m, int n, int k) {
        System.out.println("m = " + m + ", n = " + n + ", k = " + k);
        System.out.println("Answer : " + findKthNumber(m, n, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(3, 3, 5);
        runDemo(2, 3, 6);
        runDemo(30000, 30000, 450000000);
    }
}
