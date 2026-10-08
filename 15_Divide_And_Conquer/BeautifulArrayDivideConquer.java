import java.util.*;

/*
Problem: Beautiful Array
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
An array nums of length n is beautiful if it is a permutation of the integers 1..n and,
    for every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
    In other words, no element is the average of two elements on opposite sides of it.
    Given the integer n, return any beautiful array of length n.

Why this pattern fits:
The sum of an odd and an even number is odd, so it can never equal 2 * nums[k].
    If all odd values are placed before all even values, no forbidden triple can use one value from each group.
    That leaves two independent subproblems: arrange the odds beautifully and arrange the evens beautifully.

Approach:
The beautiful property survives the mappings x -> 2x - 1 and x -> 2x, because both preserve averages.
    So a beautiful array of size ceil(n / 2) mapped through 2x - 1 gives the odd numbers in a beautiful order,
    and a beautiful array of size floor(n / 2) mapped through 2x gives the even numbers in a beautiful order.
    Concatenate odds then evens. The base case is [1].

Worked Example:
Example:
    n = 5
    size 3 -> [1, 3, 2], size 2 -> [1, 2].
    Odds: [1, 5, 3]. Evens: [2, 4]. Result: [1, 5, 3, 2, 4].

Time Complexity: O(n log n)
Space Complexity: O(n log n) without memoization
*/

public class BeautifulArrayDivideConquer {
    public static int[] beautifulArray(int n) {
        if (n == 1) {
            return new int[]{1};
        }

        int[] odds = beautifulArray((n + 1) / 2);
        int[] evens = beautifulArray(n / 2);
        int[] result = new int[n];
        int index = 0;
        for (int value : odds) {
            result[index++] = 2 * value - 1;
        }
        for (int value : evens) {
            result[index++] = 2 * value;
        }
        return result;
    }

    private static boolean isBeautiful(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 2; j < nums.length; j++) {
                for (int k = i + 1; k < j; k++) {
                    if (2 * nums[k] == nums[i] + nums[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int n : new int[]{4, 5, 10}) {
            int[] result = beautifulArray(n);
            System.out.println(Arrays.toString(result) + " beautiful? " + isBeautiful(result));
        }
    }
}
