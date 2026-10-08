import java.util.*;

/*
Problem: First Non-Repeating Character in a Stream
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Characters arrive one at a time. After each arrival, report the first character
that has appeared exactly once so far, or '#' if there is none.

Why this pattern fits:
The answer is always the oldest character that is still unique.
A queue keeps candidates in arrival order, and characters that become repeated are dropped lazily from the front.

Approach:
Count each character and append it to the queue. Then pop from the front while the front's count exceeds 1.
The front (if any) is the answer for this prefix.

Worked Example:
stream "aabc" -> after a: a; after aa: none (#); after aab: b; after aabc: b. Answer = "a#bb".

Time Complexity: O(n)
Space Complexity: O(alphabet)
*/

public class P15_FirstNonRepeatingCharacterInAStreamQueue {
    public static String firstNonRepeating(String stream) {
        int[] count = new int[26];
        Deque<Character> candidates = new ArrayDeque<>();
        StringBuilder answer = new StringBuilder();
        for (char ch : stream.toCharArray()) {
            count[ch - 'a']++;
            candidates.add(ch);
            while (!candidates.isEmpty() && count[candidates.peek() - 'a'] > 1) {
                candidates.poll();
            }
            answer.append(candidates.isEmpty() ? '#' : candidates.peek());
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        for (String stream : new String[]{"aabc", "zz", "abcabc"}) {
            System.out.println(stream + " -> " + firstNonRepeating(stream));
        }
    }
}
