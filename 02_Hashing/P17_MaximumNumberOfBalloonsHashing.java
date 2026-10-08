import java.util.*;

/*
Problem: Maximum Number of Balloons
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given a string text, return the maximum number of times the word "balloon" can be formed
using each character of text at most once.

Why this pattern fits:
"How many copies can I build" is a frequency problem: the scarcest required letter decides.

Approach:
Count the letters of text. The word needs b, a, n once and l, o twice,
so the answer is min(b, a, l / 2, o / 2, n).

Worked Example:
text = "loonbalxballpoon"
b = 2, a = 2, l = 4, o = 4, n = 2, so two copies can be formed.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P17_MaximumNumberOfBalloonsHashing {
    public static int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (char ch : text.toCharArray()) {
            count[ch - 'a']++;
        }
        int singles = Math.min(count['b' - 'a'], Math.min(count[0], count['n' - 'a']));
        int doubles = Math.min(count['l' - 'a'], count['o' - 'a']) / 2;
        return Math.min(singles, doubles);
    }

    public static void main(String[] args) {
        for (String text : new String[]{"nlaebolko", "loonbalxballpoon", "leetcode"}) {
            System.out.println(text + " -> " + maxNumberOfBalloons(text));
        }
    }
}
