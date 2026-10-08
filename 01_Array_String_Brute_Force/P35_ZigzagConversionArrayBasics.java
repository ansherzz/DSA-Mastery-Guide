import java.util.*;

/*
Problem: Zigzag Conversion
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
The string s is written in a zigzag pattern on numRows rows (down the rows, then diagonally back up, repeating)
and then read row by row. Return the string produced by that row-by-row reading.

Why this pattern fits:
No clever formula is needed: simulate where each character lands.

Approach:
Keep one StringBuilder per row and a direction.
Append each character to the current row, flipping direction at the top and bottom rows.
Concatenate all rows. With one row the string is unchanged.

Worked Example:
s = "PAYPALISHIRING", numRows = 3
Rows: "PAHN", "APLSIIG", "YIR" -> "PAHNAPLSIIGYIR".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P35_ZigzagConversionArrayBasics {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int row = 0;
        int step = 1;
        for (char ch : s.toCharArray()) {
            rows[row].append(ch);
            if (row == 0) {
                step = 1;
            } else if (row == numRows - 1) {
                step = -1;
            }
            row += step;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder builder : rows) {
            result.append(builder);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("PAYPALISHIRING, 3 -> " + convert("PAYPALISHIRING", 3));
        System.out.println("PAYPALISHIRING, 4 -> " + convert("PAYPALISHIRING", 4));
        System.out.println("AB, 1 -> " + convert("AB", 1));
    }
}
