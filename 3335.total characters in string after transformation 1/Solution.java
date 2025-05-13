public class Solution {
    static final int MOD = 1_000_000_007;
    public int lengthAfterTransformations(String s, int t) {
         long[] count = new long[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        for (int step = 0; step < t; step++) {
            long[] newCount = new long[26];

            for (int i = 0; i < 25; i++) {
                newCount[i + 1] = (newCount[i + 1] + count[i]) % MOD;
            }
            newCount[0] = (newCount[0] + count[25]) % MOD;
            newCount[1] = (newCount[1] + count[25]) % MOD;
            count = newCount;
        }
        
        long result = 0;
        for (long c : count) {
            result = (result + c) % MOD;
        }

        return (int) result;
    }
}
