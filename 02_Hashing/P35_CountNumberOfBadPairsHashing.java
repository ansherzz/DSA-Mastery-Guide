import java.util.*;

/*
Problem: Count Number of Bad Pairs
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
A pair (i, j) with i < j is bad if j - i != nums[j] - nums[i].
Return the total number of bad pairs.

Why this pattern fits:
The condition mixes i and j. Rearranging the good case gives nums[i] - i == nums[j] - j,
which is "two elements share a key" - exactly what a hash map counts.

Approach:
Count good pairs by key nums[i] - i using a running frequency map.
Bad pairs = all pairs - good pairs. Use long for the totals.

Worked Example:
nums = [4, 1, 3, 3]
Keys are 4, 0, 1, 0. One good pair (indices 1 and 3). Total pairs = 6, so bad pairs = 5.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P35_CountNumberOfBadPairsHashing {
    public static long countBadPairs(int[] nums) {
        Map<Integer, Integer> seen = new HashMap<>();
        long good = 0;
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i] - i;
            int earlier = seen.getOrDefault(key, 0);
            good += earlier;
            seen.put(key, earlier + 1);
        }
        long n = nums.length;
        return n * (n - 1) / 2 - good;
    }

    public static void main(String[] args) {
        System.out.println("[4, 1, 3, 3] -> " + countBadPairs(new int[]{4, 1, 3, 3}));
        System.out.println("[1, 2, 3, 4, 5] -> " + countBadPairs(new int[]{1, 2, 3, 4, 5}));
    }
}
