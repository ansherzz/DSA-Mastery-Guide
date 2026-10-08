import java.util.*;

/*
Problem: Maximum Population Year
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
logs[i] = [birth, death] describes a person alive during the years [birth, death - 1].
All years are between 1950 and 2050. Return the earliest year with the maximum population.

Why this pattern fits:
Each person adds 1 to a whole range of years. Recording only the two endpoints
and summing afterwards avoids touching every year of every lifetime.

Approach:
Use an array indexed by year - 1950. Increment at birth and decrement at death.
Sweep with a running sum and keep the first year that reaches the maximum.

Worked Example:
logs = [[1950, 1961], [1960, 1971], [1970, 1981]] -> population is 2 in 1960 and in 1970. Earliest = 1960.

Time Complexity: O(n + number of years)
Space Complexity: O(number of years)
*/

public class P12_MaximumPopulationYearPrefixSum {
    public static int maximumPopulation(int[][] logs) {
        int[] change = new int[101];
        for (int[] log : logs) {
            change[log[0] - 1950]++;
            change[log[1] - 1950]--;
        }
        int population = 0;
        int best = 0;
        int bestYear = 1950;
        for (int i = 0; i < change.length; i++) {
            population += change[i];
            if (population > best) {
                best = population;
                bestYear = 1950 + i;
            }
        }
        return bestYear;
    }

    public static void main(String[] args) {
        System.out.println("[[1993, 1999], [2000, 2010]] -> " + maximumPopulation(new int[][]{{1993, 1999}, {2000, 2010}}));
        System.out.println("[[1950, 1961], [1960, 1971], [1970, 1981]] -> "
                + maximumPopulation(new int[][]{{1950, 1961}, {1960, 1971}, {1970, 1981}}));
    }
}
