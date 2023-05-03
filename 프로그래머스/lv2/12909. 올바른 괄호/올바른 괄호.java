class Solution {
    boolean solution(String s) {
        int stack = 0;
        for(char target : s.toCharArray()) {
            if(target == ')' && stack == 0) return false;
            if(target == '('){
                stack--;
            } 
            else stack++;
        }    
        return stack == 0;
    }
}