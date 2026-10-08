import java.util.*;

/*
Problem: Pascal's Triangle
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
Given an integer numRows, return the first numRows rows of Pascal's triangle.
    In Pascal's triangle, each number is the sum of the two numbers directly above it,
    and the edges of every row are 1.

Why this pattern fits:
Each row is built entirely from the previous row.
    Storing earlier results and combining them to get later ones is tabulation,
    the bottom-up form of dynamic programming.

Approach:
Build the rows one at a time. Every row starts and ends with 1.
    Each interior value at position j is previousRow[j - 1] + previousRow[j].

Worked Example:
Example:
    numRows = 5
        1
       1 1
      1 2 1
     1 3 3 1
    1 4 6 4 1

Time Complexity: O(numRows^2)
Space Complexity: O(numRows^2) for the output
*/

public class P04_PascalsTriangleDP {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j <= row; j++) {
                if (j == 0 || j == row) {
                    current.add(1);
                } else {
                    List<Integer> previous = triangle.get(row - 1);
                    current.add(previous.get(j - 1) + previous.get(j));
                }
            }
            triangle.add(current);
        }
        return triangle;
    }

    public static void main(String[] args) {
        for (List<Integer> row : generate(5)) {
            System.out.println(row);
        }
        System.out.println(generate(1));
    }
}
