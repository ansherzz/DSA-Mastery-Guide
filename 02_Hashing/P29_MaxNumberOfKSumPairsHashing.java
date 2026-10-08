import java.util.*;

/*
Problem: Max Number of K-Sum Pairs
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
In one operation you pick two numbers from the array whose sum equals k and remove them.
Return the maximum number of operations you can perform.

Why this pattern fits:
It is Two Sum where each element may be used once, so the map stores how many unmatched copies are waiting.

Approach:
For each value, if its complement k - value has a positive waiting count, use one and count an operation.
Otherwise add the value to the waiting counts.

Worked Example:
nums = [3, 1, 3, 4, 3], k = 6
The second 3 pairs with the first 3. The third 3 has no partner left. Answer = 1.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P29_MaxNumberOfKSumPairsHashing {
    public static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> waiting = new HashMap<>();
        int operations = 0;
        for (int value : nums) {
            int available = waiting.getOrDefault(k - value, 0);
            if (available > 0) {
                waiting.put(k - value, available - 1);
                operations++;
            } else {
                waiting.merge(value, 1, Integer::sum);
            }
        }
        return operations;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 4], k = 5 -> " + maxOperations(new int[]{1, 2, 3, 4}, 5));
        System.out.println("[3, 1, 3, 4, 3], k = 6 -> " + maxOperations(new int[]{3, 1, 3, 4, 3}, 6));
    }
}
