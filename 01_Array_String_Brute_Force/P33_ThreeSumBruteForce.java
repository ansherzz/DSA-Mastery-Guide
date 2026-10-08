import java.util.*;

/*
Problem: Three Sum with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an integer array, return all unique triplets [a, b, c] such that a + b + c = 0.
    The result must not contain duplicate triplets. Triplets may be returned in any order.

Why this pattern fits:
Before using sorting and two pointers, it is worth seeing the plain triple loop.
    It shows exactly where the cost comes from and why duplicate handling is the tricky part.

Approach:
Sort the array so each triplet is produced in non-decreasing order.
    Use three nested loops with i < j < k.
    Store every zero-sum triplet in a set of lists so duplicates collapse automatically.

Worked Example:
Example:
    nums = [-1, 0, 1, 2, -1, -4]
    Sorted: [-4, -1, -1, 0, 1, 2]
    Zero-sum triplets are [-1, -1, 2] and [-1, 0, 1].

Time Complexity: O(n^3)
Space Complexity: O(number of unique triplets)
*/

public class P33_ThreeSumBruteForce {
    public static List<List<Integer>> threeSum(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Set<List<Integer>> unique = new LinkedHashSet<>();
        for (int i = 0; i < sorted.length; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                for (int k = j + 1; k < sorted.length; k++) {
                    if (sorted[i] + sorted[j] + sorted[k] == 0) {
                        unique.add(Arrays.asList(sorted[i], sorted[j], sorted[k]));
                    }
                }
            }
        }
        return new ArrayList<>(unique);
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array    : " + Arrays.toString(nums));
        System.out.println("Triplets : " + threeSum(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-1, 0, 1, 2, -1, -4});
        runDemo(new int[]{0, 1, 1});
        runDemo(new int[]{0, 0, 0, 0});
    }
}
