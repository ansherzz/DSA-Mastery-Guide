import java.util.*;

/*
Problem: Longest Consecutive Sequence
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given an unsorted integer array, return the length of the longest sequence of consecutive integers.
    The values in the sequence do not need to appear next to each other in the input.

    Example: [100, 4, 200, 1, 3, 2] contains the consecutive sequence [1, 2, 3, 4], so the answer is 4.

Why this pattern fits:
This problem becomes easy once you realize hashing gives constant-time membership checks.
    Then you only start counting from numbers that are true sequence starts.

Approach:
Put all numbers into a HashSet.
    A number starts a sequence only if num - 1 is not in the set.
    From each start, walk upward while consecutive numbers exist.

Worked Example:
Example:
    nums = [100, 4, 200, 1, 3, 2]
    The longest consecutive sequence has length 4.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P19_LongestConsecutiveSequenceHashing {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int best = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int current = num;
                int length = 1;
                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }
                best = Math.max(best, length);
            }
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array  : " + Arrays.toString(nums));
        System.out.println("Longest consecutive length: " + longestConsecutive(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{100, 4, 200, 1, 3, 2});
        runDemo(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
    }
}
