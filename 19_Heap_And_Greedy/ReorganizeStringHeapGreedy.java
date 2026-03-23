import java.util.*;

/*
Problem: Reorganize String
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given a string, rearrange its characters so that no two adjacent characters are the same.
    Return any valid rearrangement, or an empty string if it is impossible.

    A max-heap plus a one-step hold variable is the standard greedy pattern.

Why this pattern fits:
The greedy idea is to always place the currently most frequent character, but not the one used immediately before.
    The heap maintains the best next candidate dynamically.

Approach:
Count character frequencies and push them into a max-heap.
    Pop the best character, append it, and keep the previously used character aside until the next step.

Worked Example:
Example:
    s = "aab"
    A valid answer is "aba".

Time Complexity: O(n log alphabet), effectively O(n)
Space Complexity: O(alphabet)
*/

public class ReorganizeStringHeapGreedy {
    public static String reorganizeString(String s) {
        int[] frequency = new int[26];
        for (char c : s.toCharArray()) {
            frequency[c - 'a']++;
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (frequency[i] > 0) {
                maxHeap.offer(new int[]{i, frequency[i]});
            }
        }

        StringBuilder result = new StringBuilder();
        int[] previous = null;

        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            result.append((char) ('a' + current[0]));
            current[1]--;

            if (previous != null && previous[1] > 0) {
                maxHeap.offer(previous);
            }

            previous = current;
        }

        if (result.length() != s.length()) {
            return "";
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
    }
}
