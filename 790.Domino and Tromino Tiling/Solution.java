class Solution {
    public int numTilings(int n) {
       int MODULO = 1_000_000_007;
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
      

        for (int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % MODULO;
            
        }

        return (int) dp[n]; 
    }
}

// n = 0 1 way
//n=1 1 way  2*1
//n=2 2 way 2*2
//n=3  5 ways = 2*2+1
//n=4 11 ways = 2*5+1
//n=5 23 ways = 2*11+2
//a[n] = 2*a[n-1]+a[n-3]
