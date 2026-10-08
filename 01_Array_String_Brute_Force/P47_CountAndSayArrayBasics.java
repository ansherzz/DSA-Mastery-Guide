import java.util.*;

/*
Problem: Count and Say
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
The count-and-say sequence is defined as follows:
    countAndSay(1) = "1", and countAndSay(n) is the run-length description of countAndSay(n - 1).
    To describe a string, read it left to right and replace each run of identical digits
    with the run length followed by the digit.

    Given a positive integer n, return the n-th term of the sequence.

Why this pattern fits:
There is no shortcut formula; the sequence is defined by simulation.
    Each step is a simple scan that groups consecutive equal characters.

Approach:
Start with "1" and repeat n - 1 times:
    scan the current string, measure each run of equal characters, and append "count + digit" to a builder.
    The builder becomes the next term.

Worked Example:
Example:
    1 -> "1"
    2 -> "11"      (one 1)
    3 -> "21"      (two 1s)
    4 -> "1211"    (one 2, one 1)
    5 -> "111221"  (one 1, one 2, two 1s)

Time Complexity: O(total length of all generated terms)
Space Complexity: O(length of the longest term)
*/

public class P47_CountAndSayArrayBasics {
    public static String countAndSay(int n) {
        String current = "1";

        for (int step = 1; step < n; step++) {
            StringBuilder next = new StringBuilder();
            int i = 0;
            while (i < current.length()) {
                int runStart = i;
                while (i < current.length() && current.charAt(i) == current.charAt(runStart)) {
                    i++;
                }
                next.append(i - runStart).append(current.charAt(runStart));
            }
            current = next.toString();
        }
        return current;
    }

    public static void main(String[] args) {
        for (int n = 1; n <= 6; n++) {
            System.out.println(n + " -> " + countAndSay(n));
        }
    }
}
