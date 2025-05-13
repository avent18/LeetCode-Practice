class Solution {
    public int countBalancedPermutations(String num) {
        char[] arr = num.toCharArray();
        Arrays.sort(arr);
        int count = 0;

        do {
            int evenSum = 0;
            int oddSum = 0;

            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] - '0';
                if (i % 2 == 0) {
                    evenSum += digit;
                } else {
                    oddSum += digit;
                }
            }

            if (evenSum == oddSum) {
                count++;
            }
        } while (nextPermutation(arr));

        return count;
    }

    private boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) return false;

        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }

        swap(arr, i, j);
        reverse(arr, i + 1, arr.length - 1);
        return true;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}



//Another beeter solution
// Step 1: check sum of digits, if odd , don't proceed
// Step 2: find factorial, inverse, inverseFactorial
// Step 3: initialize halflen of whole length, halfSum of total sum of digits, dp[Sum][len] kitna length ke liye kitna sum 
//base case of dp is 0 length ke liye 0 sum ke liye 1 way hai dp[0][0] = 1
// Step 4: store values in dp
// Step 5: remove duplicates



// Anther better solution
class Solution {
    private static final int MOD = (int)1e9 + 7;
    private int totWaysToPermute;
    private int[] fact;
    private int[] inverseFact;
    private int[] freq;
    private int[][][] mem;

    private int modMul(int a, int b) {
        return (int)(((long)(a % MOD) * (b % MOD)) % MOD);
    }

    private void computeFactorial(int n) {
        fact[0] = 1;
        for (int i = 1; i <= n; ++i) {
            fact[i] = modMul(fact[i - 1], i);
        }
    }

    private int binaryExponentiation(int a, int b) {
        int res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = modMul(res, a);
            }
            a = modMul(a, a);
            b >>= 1;
        }
        return res;
    }

    private void computeInverseFactorial(int n) {
        for (int i = 0; i <= n; ++i) {
            inverseFact[i] = binaryExponentiation(fact[i], MOD - 2);
        }
    }

    private int countPermutation(int digit, int leftover, int target) {
        if (digit == 10) {
            return (leftover == 0 && target == 0) ? totWaysToPermute : 0;
        }
        if (mem[digit][leftover][target] != -1) {
            return mem[digit][leftover][target];
        }

        int includeCount = Math.min(leftover, freq[digit]);
        if (digit > 0) {
            includeCount = Math.min(includeCount, target / digit);
        }

        long ans = 0;
        for (int i = 0; i <= includeCount; ++i) {
            long waysForCurrentDigit = modMul(inverseFact[i], inverseFact[freq[digit] - i]);
            ans += (waysForCurrentDigit * countPermutation(digit + 1, leftover - i, target - digit * i)) % MOD;
            ans %= MOD;
        }
        return mem[digit][leftover][target] = (int)ans;
    }

    public int countBalancedPermutations(String num) {
        int n = num.length();
        int sum = 0;
        freq = new int[10];
        for (int i = 0; i < n; ++i) {
            int digit = num.charAt(i) - '0';
            sum += digit;
            freq[digit]++;
        }

        if ((sum & 1) == 1) {
            return 0;
        }

        int target = sum / 2;
        fact = new int[n + 1];
        computeFactorial(n);

        inverseFact = new int[n + 1];
        computeInverseFactorial(n);

        int halfLen = n / 2;
        totWaysToPermute = modMul(fact[halfLen], fact[n - halfLen]);
        
        mem = new int[10][halfLen + 1][42 * 9 + 1];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j <= halfLen; ++j) {
                Arrays.fill(mem[i][j], -1);
            }
        }

        return countPermutation(0, halfLen, target);
    }
}


