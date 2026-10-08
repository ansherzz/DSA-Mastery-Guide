import java.util.*;

/*
Problem: Remove All Adjacent Duplicates in String II
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
You are given a string s and an integer k. A k-duplicate removal consists of choosing k adjacent and equal letters
    and removing them, causing the left and the right side of the deleted substring to concatenate together.
    Repeatedly make k-duplicate removals until no more can be made, and return the final string.

Why this pattern fits:
Removing a group can make its neighbors adjacent and form a new group, so the process cascades backward.
    A stack remembers the most recent surviving run, which is the only thing a new character can interact with.

Approach:
Keep a stack of (character, run length) pairs.
    If the new character matches the top's character, increase its run length; otherwise push a new pair with length 1.
    When a run length reaches k, pop the pair. The pair beneath it becomes the top,
    so later characters can extend it, which handles the cascade automatically.
    Rebuild the string from the remaining pairs.

Worked Example:
Example:
    s = "deeedbbcccbdaa", k = 3
    "eee" is removed -> "ddbbcccbdaa", "ccc" -> "ddbbbdaa", "bbb" -> "dddaa", "ddd" -> "aa".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class RemoveAllAdjacentDuplicatesIIStack {
    public static String removeDuplicates(String s, int k) {
        Deque<int[]> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == ch) {
                if (++stack.peek()[1] == k) {
                    stack.pop();
                }
            } else {
                stack.push(new int[]{ch, 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        Iterator<int[]> bottomUp = stack.descendingIterator();
        while (bottomUp.hasNext()) {
            int[] run = bottomUp.next();
            for (int i = 0; i < run[1]; i++) {
                sb.append((char) run[0]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abcd", 2));
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2));
    }
}
