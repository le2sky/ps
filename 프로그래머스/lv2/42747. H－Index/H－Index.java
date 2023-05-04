import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Integer[] citationsInt = Arrays.stream(citations)
            .mapToObj(Integer::new)
            .toArray(Integer[]::new);
        Arrays.sort(citationsInt, Comparator.reverseOrder());
        for(int citation : citationsInt) if(citation > answer) answer++;
        return answer;
    }
}
