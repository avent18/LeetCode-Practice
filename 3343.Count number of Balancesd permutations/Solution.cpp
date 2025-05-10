    class Solution {
public:
    int countBalancedPermutations(string num) {
        sort(num.begin(), num.end());
        int count=0;

        do{
            int evenSum = 0;
            int oddSum = 0;

            for(int i=0; i<num.length(); i++){
                int digit = num[i]-'0';

                if(i%2==0){
                    evenSum+=digit;
                } else {
                    oddSum+=digit;
                }
            }

            if(evenSum == oddSum){
                count++;
            }
        }while(next_permutation(num.begin(), num.end()));
        return count;
    }
}
