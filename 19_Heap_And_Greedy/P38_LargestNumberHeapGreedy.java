import java.util.*;

/*
Problem: Largest Number
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Arrange a list of non-negative integers so that they form the largest possible number, returned as a string.

Why this pattern fits:
No single digit-based rule works (compare 3, 30, and 34). The right local question is pairwise:
which of the two should come first? Comparing a + b against b + a answers it, and that relation is a consistent ordering.

Approach:
Convert the numbers to strings and sort with the comparator (b + a).compareTo(a + b).
Join them. If the result starts with '0', every number was zero, so return "0".

Worked Example:
nums = [3, 30, 34, 5, 9] -> 9, 5, 34, 3, 30 gives "9534330".

Time Complexity: O(n log n) comparisons of short strings
Space Complexity: O(n)
*/

public class P38_LargestNumberHeapGreedy {
    public static String largestNumber(int[] nums) {
        String[] parts = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            parts[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(parts, (a, b) -> (b + a).compareTo(a + b));
        if (parts[0].equals("0")) {
            return "0";
        }
        return String.join("", parts);
    }

    public static void main(String[] args) {
        System.out.println("[10, 2] -> " + largestNumber(new int[]{10, 2}));
        System.out.println("[3, 30, 34, 5, 9] -> " + largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println("[0, 0] -> " + largestNumber(new int[]{0, 0}));
    }
}
