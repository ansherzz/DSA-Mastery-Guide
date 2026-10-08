import java.util.*;

/*
Problem: Minimum Genetic Mutation
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
A gene string has length 8 and uses only A, C, G, and T.
    Each mutation changes exactly one character, and every intermediate gene must appear in the bank.
    Return the minimum number of mutations to change startGene into endGene, or -1 if impossible.

    This is a smaller cousin of Word Ladder.

Why this pattern fits:
The state graph is implicit: each valid one-character mutation is a neighbor.
    BFS is the right choice because each mutation costs 1 step.

Approach:
Store the bank in a set.
    BFS from the start gene, generating all one-character mutations using the alphabet {A, C, G, T}.

Worked Example:
Example:
    start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
    The answer is 1.

Time Complexity: O(B * 8 * 4) roughly
Space Complexity: O(B)
*/

public class P05_MinimumGeneticMutationBFSGraph {
    private static final char[] GENES = {'A', 'C', 'G', 'T'};

    public static int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> valid = new HashSet<>(Arrays.asList(bank));
        if (!valid.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(startGene);
        visited.add(startGene);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String gene = queue.poll();
                if (gene.equals(endGene)) {
                    return steps;
                }

                char[] chars = gene.toCharArray();
                for (int pos = 0; pos < chars.length; pos++) {
                    char original = chars[pos];
                    for (char g : GENES) {
                        chars[pos] = g;
                        String next = new String(chars);
                        if (valid.contains(next) && visited.add(next)) {
                            queue.offer(next);
                        }
                    }
                    chars[pos] = original;
                }
            }
            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        System.out.println(minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
    }
}
