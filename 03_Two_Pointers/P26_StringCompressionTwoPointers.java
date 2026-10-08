import java.util.*;

/*
Problem: String Compression
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an array of characters chars, compress it in place using the following algorithm:
    for each group of consecutive repeating characters, write the character,
    followed by the group's length if the length is greater than 1.
    Group lengths of 10 or more are written as multiple digit characters.
    Return the new length of the array. The compressed result must be stored in the first part of chars.

Why this pattern fits:
The compressed form of a group is never longer than the group itself,
    so a write pointer can safely trail a read pointer in the same array.

Approach:
The read pointer finds the end of each group of equal characters.
    The write pointer stores the group's character, and if the group length is above 1,
    stores each digit of the length.
    The write pointer's final position is the compressed length.

Worked Example:
Example:
    chars = [a, a, b, b, c, c, c]
    Groups: a x 2, b x 2, c x 3. The array starts with [a, 2, b, 2, c, 3] and the returned length is 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P26_StringCompressionTwoPointers {
    public static int compress(char[] chars) {
        int write = 0;
        int read = 0;

        while (read < chars.length) {
            char current = chars[read];
            int groupStart = read;
            while (read < chars.length && chars[read] == current) {
                read++;
            }

            chars[write++] = current;
            int groupLength = read - groupStart;
            if (groupLength > 1) {
                for (char digit : String.valueOf(groupLength).toCharArray()) {
                    chars[write++] = digit;
                }
            }
        }
        return write;
    }

    private static void runDemo(String text) {
        char[] chars = text.toCharArray();
        int length = compress(chars);
        System.out.println(text + " -> " + new String(chars, 0, length) + " (length " + length + ")");
    }

    public static void main(String[] args) {
        runDemo("aabbccc");
        runDemo("a");
        runDemo("abbbbbbbbbbbb");
    }
}
