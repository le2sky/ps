//1차 풀이 combination으로 minus가 가능한 위치를 추출하고 targetnumber를 만듬
//2차 풀이
/*
dfs 방식으로 dfs + dfs로 해결
*/

class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, target, 0);
    }
    
    private int dfs(int[] numbers, int depth, int target, int sum) {
        if(depth == numbers.length) {
            if(target == sum)
                return 1;
            return 0;
        }
        int s1 = sum + numbers[depth];
        int s2 = sum - numbers[depth];
        
        return dfs(numbers, depth + 1, target, s1) + dfs(numbers, depth + 1, target, s2);
    }
}