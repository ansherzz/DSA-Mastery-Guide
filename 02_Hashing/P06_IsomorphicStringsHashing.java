import java.util.*;

/*
Problem: Isomorphic Strings
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
    Every occurrence of a character must be replaced with the same character, the order must be preserved,
    and no two different characters may map to the same character. A character may map to itself.

    Return true if s and t are isomorphic.

Why this pattern fits:
The requirement is a consistent one-to-one mapping between characters.
    One map checks consistency in the s -> t direction, and a second map prevents two sources sharing a target.

Approach:
Walk both strings together.
    For each pair (a, b), check that a has not already been mapped to something other than b,
    and that b has not already been claimed by something other than a.
    Record both directions when the pair is new.

Worked Example:
Example:
    s = "paper", t = "title"
    p -> t, a -> i, p -> t (consistent), e -> l, r -> e. Every mapping is consistent and one-to-one.
    s = "badc", t = "baba" fails because both b and d would need to map to b.

Time Complexity: O(n)
Space Complexity: O(alphabet size)
*/

public class P06_IsomorphicStringsHashing {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> forward = new HashMap<>();
        Map<Character, Character> backward = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if (forward.containsKey(a) && forward.get(a) != b) {
                return false;
            }
            if (backward.containsKey(b) && backward.get(b) != a) {
                return false;
            }
            forward.put(a, b);
            backward.put(b, a);
        }
        return true;
    }

    private static void runDemo(String s, String t) {
        System.out.println("s = " + s + ", t = " + t);
        System.out.println("Isomorphic? " + isIsomorphic(s, t));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("egg", "add");
        runDemo("foo", "bar");
        runDemo("paper", "title");
        runDemo("badc", "baba");
    }
}
