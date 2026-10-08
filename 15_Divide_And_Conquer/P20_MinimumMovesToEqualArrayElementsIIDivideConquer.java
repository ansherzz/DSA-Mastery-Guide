import java.util.*;

/*
Problem: Minimum Moves to Equal Array Elements II (Quickselect Median)
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
In one move you can increment or decrement an element by 1.
Return the minimum number of moves to make all elements equal.

Why this pattern fits:
The sum of absolute differences is minimized at the median. Sorting finds it in O(n log n),
but only one order statistic is needed, which quickselect finds by partitioning and discarding one side.

Approach:
Quickselect the element of rank n / 2: partition around a pivot and continue only in the side containing that rank.
Then sum |x - median| over the array.

Worked Example:
nums = [1, 10, 2, 9] -> a median is 9 (or 2): |1-9| + |10-9| + |2-9| + |9-9| = 16.

Time Complexity: O(n) average
Space Complexity: O(1) extra
*/

public class P20_MinimumMovesToEqualArrayElementsIIDivideConquer {
    private static int select(int[] nums, int rank) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = nums[low + (high - low) / 2];
            int i = low;
            int j = high;
            while (i <= j) {
                while (nums[i] < pivot) {
                    i++;
                }
                while (nums[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    int temp = nums[i];
                    nums[i++] = nums[j];
                    nums[j--] = temp;
                }
            }
            if (rank <= j) {
                high = j;
            } else if (rank >= i) {
                low = i;
            } else {
                break;
            }
        }
        return nums[rank];
    }

    public static int minMoves2(int[] nums) {
        int median = select(nums, nums.length / 2);
        int moves = 0;
        for (int value : nums) {
            moves += Math.abs(value - median);
        }
        return moves;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3] -> " + minMoves2(new int[]{1, 2, 3}));
        System.out.println("[1, 10, 2, 9] -> " + minMoves2(new int[]{1, 10, 2, 9}));
    }
}
