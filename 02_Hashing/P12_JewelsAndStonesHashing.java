import java.util.*;

/*
Problem: Jewels and Stones
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
You are given a string jewels where each character is a type of stone that is a jewel,
    and a string stones representing the stones you have.
    Return how many of your stones are also jewels. Letters are case sensitive, so "a" and "A" are different types.

Why this pattern fits:
Every stone needs a membership test against the jewel types.
    Loading the jewel types into a HashSet turns each test into an O(1) lookup.

Approach:
Insert every character of jewels into a set.
    Scan stones and count the characters that are present in the set.

Worked Example:
Example:
    jewels = "aA", stones = "aAAbbbb"
    The stones a, A, A are jewels, so the answer is 3.

Time Complexity: O(j + s)
Space Complexity: O(j)
*/

public class P12_JewelsAndStonesHashing {
    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelTypes = new HashSet<>();
        for (char jewel : jewels.toCharArray()) {
            jewelTypes.add(jewel);
        }

        int count = 0;
        for (char stone : stones.toCharArray()) {
            if (jewelTypes.contains(stone)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(numJewelsInStones("z", "ZZ"));
    }
}
