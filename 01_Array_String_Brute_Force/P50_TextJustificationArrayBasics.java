import java.util.*;

/*
Problem: Text Justification
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Hard

Interview Prompt:
Given an array of words and a width maxWidth, format the text so each line has exactly maxWidth characters
and is fully justified. Pack as many words as possible per line. Extra spaces go between words as evenly as
possible, with left gaps receiving more. The last line and single-word lines are left-justified.

Why this pattern fits:
There is no hidden trick; it is a careful simulation with several edge cases, which is exactly what it tests.

Approach:
Greedily extend the current line while the words plus single spaces still fit.
For a normal line, divide the spare spaces by the number of gaps and give the remainder to the leftmost gaps.
For the last line or a one-word line, join with single spaces and pad on the right.

Worked Example:
words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Lines: "This    is    an", "example  of text", "justification.  ".

Time Complexity: O(total characters)
Space Complexity: O(maxWidth) besides the output
*/

public class P50_TextJustificationArrayBasics {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int start = 0;
        while (start < words.length) {
            int end = start;
            int letters = 0;
            while (end < words.length && letters + words[end].length() + (end - start) <= maxWidth) {
                letters += words[end].length();
                end++;
            }
            StringBuilder line = new StringBuilder();
            int gaps = end - start - 1;
            if (end == words.length || gaps == 0) {
                for (int i = start; i < end; i++) {
                    if (i > start) {
                        line.append(' ');
                    }
                    line.append(words[i]);
                }
                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {
                int spaces = (maxWidth - letters) / gaps;
                int extra = (maxWidth - letters) % gaps;
                for (int i = start; i < end; i++) {
                    line.append(words[i]);
                    if (i < end - 1) {
                        line.append(" ".repeat(spaces + (i - start < extra ? 1 : 0)));
                    }
                }
            }
            lines.add(line.toString());
            start = end;
        }
        return lines;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println("width 16 -> " + fullJustify(words, 16));
    }
}
