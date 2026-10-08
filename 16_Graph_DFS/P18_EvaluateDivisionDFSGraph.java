import java.util.*;

/*
Problem: Evaluate Division
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given equations of the form A / B = k, where A and B are variable names and k is a real number.
    You are then given queries of the form C / D. Return the answer to each query.
    If a query cannot be determined from the equations, return -1.0.
    Variables that do not appear in any equation are undefined, so any query using them is also -1.0.

Why this pattern fits:
The hidden structure is a weighted graph. Each variable is a node, and A / B = k is an edge
    from A to B with weight k, plus an edge from B to A with weight 1 / k.
    Then C / D is the product of the weights along any path from C to D, which a DFS can find.

Approach:
Build the weighted adjacency map with both directions for every equation.
    For each query, if either variable is unknown return -1.0.
    Otherwise DFS from the numerator, multiplying edge weights along the way and tracking visited variables.
    Return the product when the denominator is reached, or -1.0 if it is unreachable.

Worked Example:
Example:
    equations: a / b = 2.0, b / c = 3.0
    a / c = (a / b) * (b / c) = 6.0
    b / a = 1 / 2.0 = 0.5
    a / e = -1.0 because e is unknown
    x / x = -1.0 because x is unknown, while a / a = 1.0

Time Complexity: O(Q * (V + E))
Space Complexity: O(V + E)
*/

public class P18_EvaluateDivisionDFSGraph {
    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String numerator = equations[i][0];
            String denominator = equations[i][1];
            graph.computeIfAbsent(numerator, key -> new HashMap<>()).put(denominator, values[i]);
            graph.computeIfAbsent(denominator, key -> new HashMap<>()).put(numerator, 1.0 / values[i]);
        }

        double[] answers = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String from = queries[i][0];
            String to = queries[i][1];
            if (!graph.containsKey(from) || !graph.containsKey(to)) {
                answers[i] = -1.0;
            } else {
                answers[i] = dfs(graph, from, to, new HashSet<>());
            }
        }
        return answers;
    }

    private static double dfs(Map<String, Map<String, Double>> graph, String current, String target, Set<String> visited) {
        if (current.equals(target)) {
            return 1.0;
        }
        visited.add(current);

        for (Map.Entry<String, Double> edge : graph.get(current).entrySet()) {
            if (visited.contains(edge.getKey())) {
                continue;
            }
            double rest = dfs(graph, edge.getKey(), target, visited);
            if (rest != -1.0) {
                return edge.getValue() * rest;
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

        double[] answers = calcEquation(equations, values, queries);
        for (int i = 0; i < queries.length; i++) {
            System.out.println(queries[i][0] + " / " + queries[i][1] + " = " + answers[i]);
        }
    }
}
