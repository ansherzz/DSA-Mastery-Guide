import java.util.*;

/*
Problem: Count Triplets That Can Form Two Arrays of Equal XOR
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Count triplets (i, j, k) with i < j <= k such that
a = arr[i] ^ ... ^ arr[j - 1] equals b = arr[j] ^ ... ^ arr[k].

Why this pattern fits:
a == b is the same as a ^ b == 0, i.e. the XOR of arr[i..k] is 0, i.e. prefix[i] == prefix[k + 1].
Once the range is fixed, every j in (i, k] works, giving k - i triplets.

Approach:
Build the prefix XOR array. For every pair i < k with prefix[i] == prefix[k + 1], add k - i.

Worked Example:
arr = [2, 3, 1, 6, 7] -> ranges [0..2] and [2..4] XOR to 0 and contribute 2 each. Answer = 4.

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class P26_CountTripletsThatCanFormTwoArraysOfEqualXORPrefixSum {
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (prefix[i] == prefix[k + 1]) {
                    count += k - i;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[2, 3, 1, 6, 7] -> " + countTriplets(new int[]{2, 3, 1, 6, 7}));
        System.out.println("[1, 1, 1, 1, 1] -> " + countTriplets(new int[]{1, 1, 1, 1, 1}));
    }
}
