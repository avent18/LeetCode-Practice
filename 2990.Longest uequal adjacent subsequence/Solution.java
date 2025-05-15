class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        ArrayList<String>  result = new ArrayList<String>();

        if(words.length==0) return result;
         result.add(words[0]);
         int previousGroup = groups[0];

         for(int i =1;i < words.length; i++){
            if(groups[i]!=previousGroup){
                result.add(words[i]);
                previousGroup = groups[i];
            }
         }

         return result;
    }
}
