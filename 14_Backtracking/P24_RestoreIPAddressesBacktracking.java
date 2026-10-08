import java.util.*;

/*
Problem: Restore IP Addresses
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
A valid IP address consists of exactly four integers separated by dots.
    Each integer is between 0 and 255 inclusive and cannot have leading zeros (so "0" is fine but "01" is not).

    Given a string containing only digits, return all valid IP addresses that can be formed by inserting dots.
    You may not reorder or remove any digits.

Why this pattern fits:
The task is to choose three cut positions, but each piece must satisfy local rules.
    Backtracking picks the length of the next piece (1 to 3 digits), validates it immediately,
    and gives up on a branch as soon as a piece is invalid or the leftover digits cannot fit.

Approach:
Recurse with the current index and the list of parts chosen so far.
    If four parts are chosen, record the address only when all digits are consumed.
    Otherwise try pieces of length 1, 2, and 3: reject pieces with a leading zero or a value above 255.
    Prune when the remaining digits are too many or too few for the remaining parts.

Worked Example:
Example:
    s = "25525511135"
    Valid addresses: 255.255.11.135 and 255.255.111.35

Time Complexity: O(1), because at most 3^4 splits are examined
Space Complexity: O(1) excluding the output
*/

public class P24_RestoreIPAddressesBacktracking {
    public static List<String> restoreIpAddresses(String s) {
        List<String> answer = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int index, String s, List<String> parts, List<String> answer) {
        int partsLeft = 4 - parts.size();
        int digitsLeft = s.length() - index;
        if (digitsLeft < partsLeft || digitsLeft > 3 * partsLeft) {
            return;
        }
        if (partsLeft == 0) {
            answer.add(String.join(".", parts));
            return;
        }

        for (int length = 1; length <= 3 && index + length <= s.length(); length++) {
            String part = s.substring(index, index + length);
            if (length > 1 && part.charAt(0) == '0') {
                break;
            }
            if (Integer.parseInt(part) > 255) {
                break;
            }
            parts.add(part);
            backtrack(index + length, s, parts, answer);
            parts.remove(parts.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("101023"));
    }
}
