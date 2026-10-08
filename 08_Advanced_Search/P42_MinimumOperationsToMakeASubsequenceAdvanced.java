import java.util.*;

/*
Problem: Minimum Operations to Make a Subsequence
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
target has distinct integers. In one operation you can insert any integer anywhere in arr.
Return the minimum number of operations needed to make target a subsequence of arr.

Why this pattern fits:
We want the longest common subsequence of target and arr, but O(n * m) is too slow.
Because target's values are distinct, replacing each value by its index in target turns a common subsequence
into an increasing sequence of indices - an LIS problem solvable in O(m log m).

Approach:
Build value -> index for target. Convert arr to indices (dropping values not in target),
compute the strict LIS with a tails array, and return target.length - LIS.

Worked Example:
target = [5, 1, 3], arr = [9, 4, 2, 3, 4] -> only 3 is shared (LIS = 1), so 2 insertions are needed.

Time Complexity: O((n + m) log m)
Space Complexity: O(n + m)
*/

public class P42_MinimumOperationsToMakeASubsequenceAdvanced {
    public static int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            indexOf.put(target[i], i);
        }
        List<Integer> tails = new ArrayList<>();
        for (int value : arr) {
            Integer index = indexOf.get(value);
            if (index == null) {
                continue;
            }
            int low = 0;
            int high = tails.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (tails.get(mid) < index) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (low == tails.size()) {
                tails.add(index);
            } else {
                tails.set(low, index);
            }
        }
        return target.length - tails.size();
    }

    public static void main(String[] args) {
        System.out.println("[5, 1, 3] / [9, 4, 2, 3, 4] -> " + minOperations(new int[]{5, 1, 3}, new int[]{9, 4, 2, 3, 4}));
        System.out.println("[6, 4, 8, 1, 3, 2] / [4, 7, 6, 2, 3, 8, 6, 1] -> "
                + minOperations(new int[]{6, 4, 8, 1, 3, 2}, new int[]{4, 7, 6, 2, 3, 8, 6, 1}));
    }
}
