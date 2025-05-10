class Solution {
    private void calculate(long[] minSumZeroCount, int[] nums) {
       for (int ele : nums) {
           if (ele == 0) {
               minSumZeroCount[1]++;
               minSumZeroCount[0]++;
           } else {
               minSumZeroCount[0] += ele;
           }
       }
   }

   public long minSum(int[] nums1, int[] nums2) {
       long[] minSumZeroCount1 = new long[2]; // [min_sum, zero_count]
       calculate(minSumZeroCount1, nums1);

       long[] minSumZeroCount2 = new long[2];
       calculate(minSumZeroCount2, nums2);

       if ((minSumZeroCount1[0] < minSumZeroCount2[0] && minSumZeroCount1[1] == 0) ||
           (minSumZeroCount2[0] < minSumZeroCount1[0] && minSumZeroCount2[1] == 0)) {
           return -1;
       }
       return Math.max(minSumZeroCount1[0], minSumZeroCount2[0]);
   }
}
