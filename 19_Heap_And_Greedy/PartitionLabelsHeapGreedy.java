import java.util.*;

/*
Problem: Partition Labels
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given a string s of lowercase letters. Partition it into as many parts as possible
    so that each letter appears in at most one part.
    The parts, concatenated in order, must form s. Return a list of the sizes of the parts.

Why this pattern fits:
A part that contains a letter must extend at least to that letter's last occurrence.
    So while scanning, the earliest place the current part can end is the farthest last occurrence
    of any letter seen in it. Cutting as soon as the scan reaches that point is greedy and produces the most parts.

Approach:
Record the last index of every letter.
    Scan the string, extending the current part's required end to the last index of each letter encountered.
    When the scan index equals the required end, the part is complete: record its size and start a new part.

Worked Example:
Example:
    s = "ababcbacadefegdehijhklij"
    The first part must cover every a, b, and c, ending at index 8: "ababcbaca" (size 9).
    The second part is "defegde" (size 7) and the third is "hijhklij" (size 8).
    Answer = [9, 7, 8]

Time Complexity: O(n)
Space Complexity: O(1) because the alphabet has a fixed size of 26
*/

public class PartitionLabelsHeapGreedy {
    public static List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        List<Integer> sizes = new ArrayList<>();
        int partStart = 0;
        int partEnd = 0;

        for (int i = 0; i < s.length(); i++) {
            partEnd = Math.max(partEnd, lastIndex[s.charAt(i) - 'a']);
            if (i == partEnd) {
                sizes.add(partEnd - partStart + 1);
                partStart = i + 1;
            }
        }
        return sizes;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabels("eccbbbbdec"));
        System.out.println(partitionLabels("abc"));
    }
}
