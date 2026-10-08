import java.util.*;

/*
Problem: Top K Frequent Elements (Quickselect)
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an integer array and k, return the k most frequent elements in any order,
in better than O(n log n) average time.

Why this pattern fits:
Only the boundary between the top k and the rest matters, not the full order.
Quickselect partitions around a pivot frequency and recurses into just one side.

Approach:
Count frequencies and list the distinct values. Partition so that values with higher frequency than the pivot
come first. If the pivot lands at index k - 1 we are done; otherwise continue on the side containing index k - 1.

Worked Example:
nums = [1, 1, 1, 2, 2, 3], k = 2 -> frequencies 3, 2, 1: the top two are 1 and 2.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P39_TopKFrequentElementsQuickSelectDivideConquer {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int value : nums) {
            count.merge(value, 1, Integer::sum);
        }
        int[] values = new int[count.size()];
        int size = 0;
        for (int value : count.keySet()) {
            values[size++] = value;
        }
        int low = 0;
        int high = size - 1;
        while (low < high) {
            int pivotFrequency = count.get(values[low + (high - low) / 2]);
            int i = low;
            int j = high;
            while (i <= j) {
                while (count.get(values[i]) > pivotFrequency) {
                    i++;
                }
                while (count.get(values[j]) < pivotFrequency) {
                    j--;
                }
                if (i <= j) {
                    int temp = values[i];
                    values[i++] = values[j];
                    values[j--] = temp;
                }
            }
            if (k - 1 <= j) {
                high = j;
            } else if (k - 1 >= i) {
                low = i;
            } else {
                break;
            }
        }
        return Arrays.copyOf(values, k);
    }

    private static void runDemo(int[] nums, int k) {
        int[] result = topKFrequent(nums, k);
        Arrays.sort(result);
        System.out.println(Arrays.toString(nums) + ", k = " + k + " -> " + Arrays.toString(result));
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 1, 1, 2, 2, 3}, 2);
        runDemo(new int[]{4, 4, 5, 5, 5, 6}, 1);
    }
}
