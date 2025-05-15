class Solution {
    final int md = 1000000007;
    
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int n = s.length();
        long[] fq = new long[26];
        for (int i = 0; i < n; i++) {
            fq[s.charAt(i) - 'a']++;
        }

        // T(i,j) => while expanding i j times (i+j)%26
        int[][] T = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= nums.get(i); j++) {
                T[i][(i + j) % 26]++;
            }
        }

        int[][] Tt = binaryPower(T, t);
        long[] upfq = new long[26];
        for (int i = 0; i < 26; i++) {
            if (fq[i] == 0) continue;
            for (int j = 0; j < 26; j++) {
                upfq[i] = (upfq[i] + (Tt[i][j] * fq[i]) % md) % md;
            }
        }

        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + upfq[i]) % md;
        }
        return (int) ans;
    }

    private int[][] binaryPower(int[][] T, long exp) {
        int[][] res = new int[26][26];
        for (int i = 0; i < 26; i++) {
            res[i][i] = 1;
        }
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = matmul(res, T);
            }
            T = matmul(T, T);
            exp >>= 1;
        }
        return res;
    }

    private int[][] matmul(int[][] A, int[][] B) {
        int[][] res = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                long Aij = A[i][j];
                if (Aij == 0) continue;
                for (int k = 0; k < 26; k++) {
                    res[i][k] = (int) ((res[i][k] + (Aij * B[j][k]) % md) % md);
                }
            }
        }
        return res;
    }
}
