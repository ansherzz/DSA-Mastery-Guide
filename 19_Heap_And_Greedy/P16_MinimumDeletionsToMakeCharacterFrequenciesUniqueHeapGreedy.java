import java.util.*;

/*
Problem: Minimum Deletions to Make Character Frequencies Unique
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
A string is good if no two different characters have the same frequency.
Return the minimum number of characters to delete to make the string good.

Why this pattern fits:
Frequencies can only go down. Handling them from the largest, each one should keep the highest value
that is still free - any lower choice wastes deletions and never helps later letters.

Approach:
Count and sort the frequencies descending. Track `allowed`, the largest frequency the next letter may keep.
For each frequency f: keep min(f, allowed), add the difference to the answer, and set allowed to (kept - 1), never below 0.

Worked Example:
s = "aaabbbcc" -> frequencies 3, 3, 2 become 3, 2, 1. Deletions = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P16_MinimumDeletionsToMakeCharacterFrequenciesUniqueHeapGreedy {
    public static int minDeletions(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        Arrays.sort(count);
        int deletions = 0;
        int allowed = s.length();
        for (int i = 25; i >= 0 && count[i] > 0; i--) {
            int kept = Math.min(count[i], allowed);
            deletions += count[i] - kept;
            allowed = Math.max(0, kept - 1);
        }
        return deletions;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"aab", "aaabbbcc", "ceabaacb"}) {
            System.out.println(s + " -> " + minDeletions(s));
        }
    }
}
