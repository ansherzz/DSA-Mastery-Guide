import java.util.*;

/*
Problem: Letter Tile Possibilities
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
You have n tiles, where each tile has one letter tiles[i] printed on it.
    Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
    Each tile can be used at most once per sequence, and sequences of any length count.

Why this pattern fits:
The task is to count all distinct arrangements of all sub-multisets of the tiles.
    Choosing by letter (not by tile) at each step makes every recursion path a distinct sequence,
    so duplicates never need to be filtered out.

Approach:
Count how many tiles of each letter are available.
    At each step, try every letter that still has a tile: use one, count the sequence formed so far (+1),
    count all of its extensions recursively, then return the tile.
    Because each branch is labeled by a letter rather than a tile, two identical tiles never create two identical sequences.

Worked Example:
Example:
    tiles = "AAB"
    Sequences: A, B, AA, AB, BA, AAB, ABA, BAA. Answer = 8.

Time Complexity: O(number of distinct sequences)
Space Complexity: O(n) recursion depth
*/

public class LetterTilePossibilitiesBacktracking {
    public static int numTilePossibilities(String tiles) {
        int[] counts = new int[26];
        for (char tile : tiles.toCharArray()) {
            counts[tile - 'A']++;
        }
        return count(counts);
    }

    private static int count(int[] counts) {
        int total = 0;
        for (int letter = 0; letter < 26; letter++) {
            if (counts[letter] == 0) {
                continue;
            }
            counts[letter]--;
            total += 1 + count(counts);
            counts[letter]++;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
        System.out.println(numTilePossibilities("AAABBC"));
        System.out.println(numTilePossibilities("V"));
    }
}
