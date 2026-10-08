import java.util.*;

/*
Problem: Roman to Integer
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Convert a valid Roman numeral string (1 to 3999) into an integer.
Symbols: I=1, V=5, X=10, L=50, C=100, D=500, M=1000.
A smaller symbol placed before a larger one is subtracted (IV = 4, IX = 9, XL = 40, ...).

Why this pattern fits:
This is a character-by-character parsing problem.
The only decision per character depends on its right neighbor.

Approach:
Walk left to right.
If the current symbol's value is smaller than the next symbol's value, subtract it; otherwise add it.

Worked Example:
s = "MCMXCIV"
M(+1000) C(-100) M(+1000) X(-10) C(+100) I(-1) V(+5) = 1994.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P10_RomanToIntegerArrayBasics {
    private static int valueOf(char symbol) {
        switch (symbol) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            default: return 1000;
        }
    }

    public static int romanToInt(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int current = valueOf(s.charAt(i));
            if (i + 1 < s.length() && current < valueOf(s.charAt(i + 1))) {
                total -= current;
            } else {
                total += current;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("III -> " + romanToInt("III"));
        System.out.println("LVIII -> " + romanToInt("LVIII"));
        System.out.println("MCMXCIV -> " + romanToInt("MCMXCIV"));
    }
}
