class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int[] d : dominoes) {
            int a = d[0];
            int b = d[1];
            // Use a unique key: 10 * min + max because 1 <= a, b <= 9
            int key = Math.min(a, b) * 10 + Math.max(a, b);
            int val = map.getOrDefault(key, 0);
            count += val;
            map.put(key, val + 1);
        }

        return count;
    }
}