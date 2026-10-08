import java.util.*;

/*
Problem: Count Primes (Sieve of Eratosthenes)
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Return the number of prime numbers strictly less than n.

Why this pattern fits:
Brute force tests each number for divisibility, O(n * sqrt(n)).
A boolean array lets each prime eliminate all of its multiples in bulk.

Approach:
Assume every number from 2 to n - 1 is prime.
For each i with i * i < n that is still prime, mark i*i, i*i + i, ... as composite.
Count the entries that remain unmarked.

Worked Example:
n = 10 -> the primes below 10 are 2, 3, 5, 7. Answer = 4.

Time Complexity: O(n log log n)
Space Complexity: O(n)
*/

public class P40_CountPrimesSieveArrayBasics {
    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] composite = new boolean[n];
        for (int i = 2; (long) i * i < n; i++) {
            if (!composite[i]) {
                for (int multiple = i * i; multiple < n; multiple += i) {
                    composite[multiple] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!composite[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        for (int n : new int[]{10, 100, 2}) {
            System.out.println("n = " + n + " -> " + countPrimes(n));
        }
    }
}
