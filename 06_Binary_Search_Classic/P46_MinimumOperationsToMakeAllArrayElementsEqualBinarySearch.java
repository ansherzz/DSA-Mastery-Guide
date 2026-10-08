import java.util.*;

/*
Problem: Minimum Operations to Make All Array Elements Equal
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
For each query q, return the minimum number of +1 / -1 operations needed to make every element of nums equal to q.
Queries are independent.

Why this pattern fits:
The cost is the sum of |nums[i] - q|. In sorted order, the elements below q and the elements above q
are two contiguous blocks, located by binary search and summed by prefix sums.

Approach:
Sort nums and build prefix sums. For a query find k = number of elements < q.
Cost = q * k - prefix[k] + (prefix[n] - prefix[k]) - q * (n - k).

Worked Example:
nums = [3, 1, 6, 8], q = 5 -> raise 1 and 3 (cost 4 + 2), lower 6 and 8 (cost 1 + 3). Total 10.

Time Complexity: O((n + q) log n)
Space Complexity: O(n)
*/

public class P46_MinimumOperationsToMakeAllArrayElementsEqualBinarySearch {
    public static List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        List<Long> answer = new ArrayList<>();
        for (int q : queries) {
            int low = 0;
            int high = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] < q) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            long raise = (long) q * low - prefix[low];
            long lower = prefix[n] - prefix[low] - (long) q * (n - low);
            answer.add(raise + lower);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("[3, 1, 6, 8], queries [1, 5] -> " + minOperations(new int[]{3, 1, 6, 8}, new int[]{1, 5}));
        System.out.println("[2, 9, 6, 3], queries [10] -> " + minOperations(new int[]{2, 9, 6, 3}, new int[]{10}));
    }
}
