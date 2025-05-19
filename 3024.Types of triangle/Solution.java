class Solution {
    public String triangleType(int[] nums) {
      int a = nums[0], b = nums[1], c = nums[2];

        // Step 1: Check triangle validity
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "none";
        }

        // Step 2: Determine triangle type
        if (a == b && b == c) {
            return "equilateral";
        } else if (a == b || b == c || a == c) {
            return "isosceles";
        } else {
            return "scalene";
        }  
    }
}