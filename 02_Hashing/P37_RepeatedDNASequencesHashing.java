import java.util.*;

/*
Problem: Repeated DNA Sequences
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
A DNA sequence is a string over A, C, G, T.
Return all 10-letter-long substrings that occur more than once in the sequence.

Why this pattern fits:
Storing every substring in a set works but hashes 10 characters each time.
With 4 letters, a window fits in 20 bits, and the code of the next window is derived from the previous one in O(1).

Approach:
Map each letter to 2 bits. Slide over the string: shift the code left by 2, add the new letter,
and mask to 20 bits. Once 10 letters are in, check the code against a `seen` set;
codes seen before go to the answer (a second set avoids reporting them twice).

Worked Example:
s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
"AAAAACCCCC" and "CCCCCAAAAA" both appear twice.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P37_RepeatedDNASequencesHashing {
    public static List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new LinkedHashSet<>();
        int code = 0;
        int mask = (1 << 20) - 1;
        for (int i = 0; i < s.length(); i++) {
            code = ((code << 2) | "ACGT".indexOf(s.charAt(i))) & mask;
            if (i >= 9 && !seen.add(code)) {
                repeated.add(s.substring(i - 9, i + 1));
            }
        }
        return new ArrayList<>(repeated);
    }

    public static void main(String[] args) {
        System.out.println("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT -> "
                + findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println("AAAAAAAAAAAAA -> " + findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }
}
