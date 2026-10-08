import java.util.*;

/*
Problem: Group Shifted Strings
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
A string can be shifted by moving every letter forward the same number of positions (wrapping z to a).
Group the given strings so that strings in the same group can be shifted into one another.

Why this pattern fits:
Grouping problems need a canonical key that is identical for every member of a group.
Shifting keeps the differences between letters unchanged, so those differences are the key.

Approach:
For each string, compute (ch - first + 26) % 26 for every character and join them into a key.
Collect strings in a map from key to list.

Worked Example:
strings = ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
"abc", "bcd", "xyz" share key 0,1,2. "az" and "ba" share key 0,25. "a" and "z" share key 0.

Time Complexity: O(total characters)
Space Complexity: O(total characters)
*/

public class P26_GroupShiftedStringsHashing {
    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groups = new LinkedHashMap<>();
        for (String s : strings) {
            StringBuilder key = new StringBuilder();
            for (char ch : s.toCharArray()) {
                key.append((ch - s.charAt(0) + 26) % 26).append(',');
            }
            groups.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(Arrays.toString(strings) + " -> " + groupStrings(strings));
    }
}
