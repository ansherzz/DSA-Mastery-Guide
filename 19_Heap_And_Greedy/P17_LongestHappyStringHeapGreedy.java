import java.util.*;

/*
Problem: Longest Happy String
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Build the longest string using at most a 'a's, b 'b's, and c 'c's that never contains "aaa", "bbb", or "ccc".
Return any such longest string.

Why this pattern fits:
The letter with the largest remaining count is the one most at risk of being left over, so it should be used first.
When it is blocked by the "no triple" rule, the next most frequent letter acts as a separator.

Approach:
Max-heap of (count, letter). Pop the top; if the result already ends with two of that letter, pop the second
entry instead (stop if there is none), append it, and push both back with updated counts.

Worked Example:
a = 1, b = 1, c = 7 -> "ccaccbcc" has length 8; the seventh 'c' cannot be placed without creating "ccc".

Time Complexity: O((a + b + c) * log 3)
Space Complexity: O(1) besides the output
*/

public class P17_LongestHappyStringHeapGreedy {
    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        int[] counts = {a, b, c};
        for (int i = 0; i < 3; i++) {
            if (counts[i] > 0) {
                heap.add(new int[]{counts[i], i});
            }
        }
        StringBuilder result = new StringBuilder();
        while (!heap.isEmpty()) {
            int[] first = heap.poll();
            char letter = (char) ('a' + first[1]);
            int length = result.length();
            if (length >= 2 && result.charAt(length - 1) == letter && result.charAt(length - 2) == letter) {
                if (heap.isEmpty()) {
                    break;
                }
                int[] second = heap.poll();
                result.append((char) ('a' + second[1]));
                if (--second[0] > 0) {
                    heap.add(second);
                }
                heap.add(first);
            } else {
                result.append(letter);
                if (--first[0] > 0) {
                    heap.add(first);
                }
            }
        }
        return result.toString();
    }

    private static void runDemo(int a, int b, int c) {
        String s = longestDiverseString(a, b, c);
        boolean valid = !s.contains("aaa") && !s.contains("bbb") && !s.contains("ccc");
        System.out.println("a = " + a + ", b = " + b + ", c = " + c + " gives " + s);
        System.out.println("length -> " + s.length());
        System.out.println("no letter three times in a row -> " + valid);
    }

    public static void main(String[] args) {
        runDemo(1, 1, 7);
        runDemo(7, 1, 0);
    }
}
