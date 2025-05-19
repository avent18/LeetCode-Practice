class Solution {
    
    static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        List<int[]> validCols = new ArrayList<>();
        generateValidColumns(m, new int[m], 0, validCols);

        // Mapping each column index to the list of indices that can follow it
        Map<Integer, List<Integer>> transitions = new HashMap<>();
        int size = validCols.size();

        for (int i = 0; i < size; i++) {
            transitions.put(i, new ArrayList<>());
            for (int j = 0; j < size; j++) {
                if (isCompatible(validCols.get(i), validCols.get(j))) {
                    transitions.get(i).add(j);
                }
            }
        }

        // DP: dp[colIndex][columnNumber]
        int[][] dp = new int[size][n];
        for (int i = 0; i < size; i++) dp[i][0] = 1;

        for (int col = 1; col < n; col++) {
            for (int curr = 0; curr < size; curr++) {
                for (int prev : transitions.get(curr)) {
                    dp[curr][col] = (dp[curr][col] + dp[prev][col - 1]) % MOD;
                }
            }
        }

        int totalWays = 0;
        for (int i = 0; i < size; i++) {
            totalWays = (totalWays + dp[i][n - 1]) % MOD;
        }
        return totalWays; 
    }

    private void generateValidColumns(int m, int[] curr, int index, List<int[]> result) {
        if (index == m) {
            result.add(Arrays.copyOf(curr, m));
            return;
        }
        for (int color = 0; color < 3; color++) {
            if (index == 0 || curr[index - 1] != color) {
                curr[index] = color;
                generateValidColumns(m, curr, index + 1, result);
            }
        }
    }

    private boolean isCompatible(int[] col1, int[] col2) {
        for (int i = 0; i < col1.length; i++) {
            if (col1[i] == col2[i]) return false;
        }
        return true;
    }
}
