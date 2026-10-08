import java.util.*;

/*
Problem: Check If N and Its Double Exist
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an array arr, return true if there are two different indices i and j with arr[i] == 2 * arr[j].

Why this pattern fits:
It is Two Sum with a different relation: the partner of x is 2x or x / 2.
A set of earlier values answers each partner lookup in O(1).

Approach:
Scan once. For value x, return true if the set already holds 2x, or x is even and the set holds x / 2.
Checking before inserting guarantees the two indices are different (important for zeros).

Worked Example:
arr = [10, 2, 5, 3]
When 5 arrives, its double 10 is already in the set. Answer = true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P05_CheckIfNAndItsDoubleExistHashing {
    public static boolean checkIfExist(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        for (int value : arr) {
            if (seen.contains(2 * value) || (value % 2 == 0 && seen.contains(value / 2))) {
                return true;
            }
            seen.add(value);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("[10, 2, 5, 3] -> " + checkIfExist(new int[]{10, 2, 5, 3}));
        System.out.println("[3, 1, 7, 11] -> " + checkIfExist(new int[]{3, 1, 7, 11}));
        System.out.println("[0, 0] -> " + checkIfExist(new int[]{0, 0}));
    }
}
