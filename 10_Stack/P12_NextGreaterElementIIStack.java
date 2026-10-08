import java.util.*;

/*
Problem: Next Greater Element II
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given a circular integer array (the element after the last one is the first one),
    return the next greater number for every element.
    The next greater number of x is the first larger number found when traversing forward in the circular array.
    If it does not exist, use -1.

Why this pattern fits:
This is the monotonic-stack "next greater" problem with a twist: the search may wrap around.
    Walking the array twice simulates the wrap-around without physically duplicating the array.

Approach:
Keep a stack of indices whose next greater value is still unknown.
    Iterate i from 0 to 2n - 1 and use i % n as the real index.
    Pop and resolve every stacked index whose value is smaller than the current value.
    Push indices only during the first pass, because each index needs just one answer.

Worked Example:
Example:
    nums = [1, 2, 1]
    The first 1 sees 2. The 2 has nothing larger anywhere. The last 1 wraps around and sees 2.
    Answer = [2, -1, 2]

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P12_NextGreaterElementIIStack {
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int value = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < value) {
                answer[stack.pop()] = value;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return answer;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Answer : " + Arrays.toString(nextGreaterElements(nums)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 1});
        runDemo(new int[]{1, 2, 3, 4, 3});
        runDemo(new int[]{5, 4, 3, 2, 1});
    }
}
