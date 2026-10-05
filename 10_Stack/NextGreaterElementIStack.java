import java.util.*;

/*
Problem: Next Greater Element I
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
The next greater element of a value x in an array is the first value to the right of x that is larger than x.
    You are given two arrays of distinct integers, nums1 and nums2, where nums1 is a subset of nums2.
    For each value in nums1, find its next greater element in nums2. Use -1 when there is none.

Why this pattern fits:
"First larger value to the right" is the defining use case of a monotonic stack.
    Values wait on the stack until a larger value arrives and resolves them all at once.

Approach:
Scan nums2 from left to right with a stack of values that have not found their next greater element yet.
    When the current value is larger than the top of the stack, pop it and record the current value as its answer.
    Store the answers in a map, then look up each value of nums1.

Worked Example:
Example:
    nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]
    In nums2: next greater of 1 is 3, of 3 is 4, and 4 and 2 have none.
    Answer = [-1, 3, -1]

Time Complexity: O(n + m)
Space Complexity: O(n)
*/

public class NextGreaterElementIStack {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int value : nums2) {
            while (!stack.isEmpty() && value > stack.peek()) {
                nextGreater.put(stack.pop(), value);
            }
            stack.push(value);
        }

        int[] answer = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            answer[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return answer;
    }

    private static void runDemo(int[] nums1, int[] nums2) {
        System.out.println("nums1  : " + Arrays.toString(nums1));
        System.out.println("nums2  : " + Arrays.toString(nums2));
        System.out.println("Answer : " + Arrays.toString(nextGreaterElement(nums1, nums2)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        runDemo(new int[]{2, 4}, new int[]{1, 2, 3, 4});
    }
}
