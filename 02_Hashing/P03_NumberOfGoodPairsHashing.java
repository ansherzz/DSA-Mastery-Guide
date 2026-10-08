import java.util.*;

/*
Problem: Number of Good Pairs
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an array nums, return the number of pairs (i, j) with i < j and nums[i] == nums[j].

Why this pattern fits:
Brute force checks every pair in O(n^2).
A frequency map tells how many earlier equal values exist at the moment each element arrives.

Approach:
Scan left to right. Before incrementing the count of the current value,
add that count to the answer, because the current element forms a pair with each earlier copy.

Worked Example:
nums = [1, 2, 3, 1, 1, 3]
The second 1 adds 1, the third 1 adds 2, the second 3 adds 1. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P03_NumberOfGoodPairsHashing {
    public static int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> seen = new HashMap<>();
        int pairs = 0;
        for (int value : nums) {
            int earlier = seen.getOrDefault(value, 0);
            pairs += earlier;
            seen.put(value, earlier + 1);
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 1, 1, 3] -> " + numIdenticalPairs(new int[]{1, 2, 3, 1, 1, 3}));
        System.out.println("[1, 1, 1, 1] -> " + numIdenticalPairs(new int[]{1, 1, 1, 1}));
        System.out.println("[1, 2, 3] -> " + numIdenticalPairs(new int[]{1, 2, 3}));
    }
}
