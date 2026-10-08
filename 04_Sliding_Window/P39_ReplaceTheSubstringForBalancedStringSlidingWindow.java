import java.util.*;

/*
Problem: Replace the Substring for Balanced String
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
s has length n (a multiple of 4) and contains only Q, W, E, R. It is balanced when each letter appears n / 4 times.
Return the minimum length of a substring that can be replaced (with any letters) to make s balanced.

Why this pattern fits:
The replaced window can become anything, so only the characters outside it matter:
none of them may exceed n / 4. Track the outside counts while a window slides.

Approach:
Count all letters. Moving right into the window decrements its letter's outside count.
While the outside counts are all <= n / 4, the window works: record its length and try shrinking from the left.

Worked Example:
s = "QQQW" -> outside counts must drop to at most 1 each, so two Q's must be inside the window. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P39_ReplaceTheSubstringForBalancedStringSlidingWindow {
    private static boolean balancedOutside(int[] count, int limit) {
        return count['Q'] <= limit && count['W'] <= limit && count['E'] <= limit && count['R'] <= limit;
    }

    public static int balancedString(String s) {
        int n = s.length();
        int limit = n / 4;
        int[] count = new int[128];
        for (char ch : s.toCharArray()) {
            count[ch]++;
        }
        if (balancedOutside(count, limit)) {
            return 0;
        }
        int best = n;
        int left = 0;
        for (int right = 0; right < n; right++) {
            count[s.charAt(right)]--;
            while (left <= right && balancedOutside(count, limit)) {
                best = Math.min(best, right - left + 1);
                count[s.charAt(left++)]++;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"QWER", "QQWE", "QQQW", "QQQQ"}) {
            System.out.println(s + " -> " + balancedString(s));
        }
    }
}
