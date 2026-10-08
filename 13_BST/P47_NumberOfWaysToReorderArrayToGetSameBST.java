import java.util.*;

/*
Problem: Number of Ways to Reorder Array to Get Same BST
Pattern: 13. Binary Search Tree
Difficulty: Hard

Interview Prompt:
Inserting the elements of nums (a permutation of 1..n) one by one builds a BST.
Return the number of other orderings of nums that build exactly the same BST, modulo 1e9 + 7.

Why this pattern fits:
The first element is always the root. The smaller values form the left subtree and the larger values the right subtree;
their relative orders must each be preserved, but the two groups may be interleaved freely.

Approach:
ways(list) = C(n - 1, leftSize) * ways(left) * ways(right), where left and right keep their original relative order.
Precompute Pascal's triangle modulo 1e9 + 7. The answer is ways(nums) - 1, excluding the original ordering.

Worked Example:
nums = [3, 4, 5, 1, 2] -> left [1, 2], right [4, 5]: C(4, 2) = 6 interleavings, minus the original = 5.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P47_NumberOfWaysToReorderArrayToGetSameBST {
    private static final long MOD = 1_000_000_007L;

    private static long ways(List<Integer> values, long[][] choose) {
        if (values.size() <= 2) {
            return 1;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) < values.get(0)) {
                left.add(values.get(i));
            } else {
                right.add(values.get(i));
            }
        }
        long interleavings = choose[values.size() - 1][left.size()];
        return interleavings * ways(left, choose) % MOD * ways(right, choose) % MOD;
    }

    public static int numOfWays(int[] nums) {
        int n = nums.length;
        long[][] choose = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            choose[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                choose[i][j] = (choose[i - 1][j - 1] + choose[i - 1][j]) % MOD;
            }
        }
        List<Integer> values = new ArrayList<>();
        for (int num : nums) {
            values.add(num);
        }
        return (int) ((ways(values, choose) - 1 + MOD) % MOD);
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 3] -> " + numOfWays(new int[]{2, 1, 3}));
        System.out.println("[3, 4, 5, 1, 2] -> " + numOfWays(new int[]{3, 4, 5, 1, 2}));
        System.out.println("[1, 2, 3] -> " + numOfWays(new int[]{1, 2, 3}));
    }
}
