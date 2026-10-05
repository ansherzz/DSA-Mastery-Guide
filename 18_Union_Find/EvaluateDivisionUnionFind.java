import java.util.*;

/*
Problem: Evaluate Division using Weighted Union Find
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given equations of the form A / B = k, where A and B are variable names and k is a real number.
    Answer queries of the form C / D. If a query cannot be determined, or uses an unknown variable, return -1.0.

    The DFS version is in folder 16. Solve it here with a weighted disjoint set union.

Why this pattern fits:
Variables related by equations form a group in which every ratio is determined.
    A weighted Union Find stores, with each variable, its ratio to its parent.
    After path compression each variable knows its ratio to the group's root,
    so any query inside a group becomes a single division.

Approach:
Keep parent[x] and weight[x], where weight[x] = x / parent[x].
    find(x) compresses the path and multiplies the weights so that weight[x] becomes x / root.
    To add A / B = k, attach A's root under B's root with weight k * weight[B] / weight[A],
    which keeps every stored ratio consistent.
    To answer C / D, if both have the same root return weight[C] / weight[D]; otherwise -1.0.

Worked Example:
Example:
    a / b = 2.0 and b / c = 3.0
    After the unions, relative to the root c: a = 6, b = 3, c = 1.
    a / c = 6 / 1 = 6.0, b / a = 3 / 6 = 0.5.

Time Complexity: O((E + Q) * alpha(V))
Space Complexity: O(V)
*/

public class EvaluateDivisionUnionFind {
    private static final Map<String, String> parent = new HashMap<>();
    private static final Map<String, Double> weight = new HashMap<>();

    private static String find(String x) {
        String currentParent = parent.get(x);
        if (!currentParent.equals(x)) {
            String root = find(currentParent);
            weight.put(x, weight.get(x) * weight.get(currentParent));
            parent.put(x, root);
        }
        return parent.get(x);
    }

    private static void union(String a, String b, double ratio) {
        parent.putIfAbsent(a, a);
        weight.putIfAbsent(a, 1.0);
        parent.putIfAbsent(b, b);
        weight.putIfAbsent(b, 1.0);

        String rootA = find(a);
        String rootB = find(b);
        if (rootA.equals(rootB)) {
            return;
        }
        parent.put(rootA, rootB);
        weight.put(rootA, ratio * weight.get(b) / weight.get(a));
    }

    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        parent.clear();
        weight.clear();
        for (int i = 0; i < equations.length; i++) {
            union(equations[i][0], equations[i][1], values[i]);
        }

        double[] answers = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String numerator = queries[i][0];
            String denominator = queries[i][1];

            if (!parent.containsKey(numerator) || !parent.containsKey(denominator)
                    || !find(numerator).equals(find(denominator))) {
                answers[i] = -1.0;
            } else {
                answers[i] = weight.get(numerator) / weight.get(denominator);
            }
        }
        return answers;
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
