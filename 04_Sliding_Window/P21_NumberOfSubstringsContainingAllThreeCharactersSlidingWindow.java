import java.util.*;

/*
Problem: Number of Substrings Containing All Three Characters
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a string s consisting only of 'a', 'b', and 'c',
return the number of substrings containing at least one of each character.

Why this pattern fits:
"At least" constraints stay true when the window grows leftward.
So for a fixed right end the valid starts are a prefix of indices, and only its boundary must be tracked.

Approach:
Keep the last index of each letter. After reading s[right], the smallest of the three last indices
is the latest valid start; all starts 0..thatIndex work, so add (minLast + 1).

Worked Example:
s = "abcabc" -> right ends 2, 3, 4, 5 contribute 1, 2, 3, 4. Answer = 10.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P21_NumberOfSubstringsContainingAllThreeCharactersSlidingWindow {
    public static int numberOfSubstrings(String s) {
        int[] last = {-1, -1, -1};
        int count = 0;
        for (int right = 0; right < s.length(); right++) {
            last[s.charAt(right) - 'a'] = right;
            count += 1 + Math.min(last[0], Math.min(last[1], last[2]));
        }
        return count;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"abcabc", "aaacb", "abc"}) {
            System.out.println(s + " -> " + numberOfSubstrings(s));
        }
    }
}
