class Solution {
    private int hammingDistance(String a, String b) {
        if (a.length() != b.length()) return Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count > 1) return count; // early break
            }
        }
        return count;
    }

    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;

        // Build DAG: edges from i to j (i < j), if valid transition
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (groups[i] != groups[j] &&
                    words[i].length() == words[j].length() &&
                    hammingDistance(words[i], words[j]) == 1) {
                    graph[i].add(j);
                }
            }
        }

        // DP array: stores the longest path ending at each node
        List<Integer>[] dp = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
            dp[i].add(i);
        }

        // Process all nodes in input order (since edges only go forward)
        for (int i = 0; i < n; i++) {
            for (int nei : graph[i]) {
                if (dp[i].size() + 1 > dp[nei].size()) {
                    dp[nei] = new ArrayList<>(dp[i]);
                    dp[nei].add(nei);
                }
            }
        }

        // Find the longest path
        List<Integer> longest = new ArrayList<>();
        for (List<Integer> path : dp) {
            if (path.size() > longest.size()) {
                longest = path;
            }
        }

        // Convert index path to word list
        List<String> res = new ArrayList<>();
        for (int idx : longest) {
            res.add(words[idx]);
        }

        return res;
    }
}
