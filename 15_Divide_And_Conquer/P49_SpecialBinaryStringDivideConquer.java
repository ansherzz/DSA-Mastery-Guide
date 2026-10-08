import java.util.*;

/*
Problem: Special Binary String
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
A special binary string has equal numbers of 0s and 1s, and every prefix has at least as many 1s as 0s.
You may repeatedly swap two adjacent special substrings. Return the lexicographically largest result.

Why this pattern fits:
Think of 1 as "(" and 0 as ")". The string is a sequence of balanced blocks "1" + inner + "0",
where inner is again special. Blocks at the same level can be reordered freely, so each level is solved independently.

Approach:
Scan with a balance counter. Each time it returns to zero, a block has ended:
recursively maximize its interior and store "1" + result + "0". Sort the blocks in descending order and join them.

Worked Example:
"11011000" -> the interior "101100" has blocks "10" and "1100", reordered to "110010". Answer = "11100100".

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class P49_SpecialBinaryStringDivideConquer {
    public static String makeLargestSpecial(String s) {
        List<String> blocks = new ArrayList<>();
        int balance = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            balance += s.charAt(i) == '1' ? 1 : -1;
            if (balance == 0) {
                blocks.add("1" + makeLargestSpecial(s.substring(start + 1, i)) + "0");
                start = i + 1;
            }
        }
        blocks.sort(Collections.reverseOrder());
        return String.join("", blocks);
    }

    public static void main(String[] args) {
        System.out.println("11011000 -> " + makeLargestSpecial("11011000"));
        System.out.println("10 -> " + makeLargestSpecial("10"));
    }
}
