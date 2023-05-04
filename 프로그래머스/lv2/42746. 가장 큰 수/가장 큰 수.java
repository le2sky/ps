import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] target = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);
        
        long size = Arrays.stream(target).filter(v -> !v.equals("0")).count();
        if(size == 0) return "0";
        
        Arrays.sort(target, (o1, o2) -> {
            if(o1.length() == o2.length()) return o1.compareTo(o2);
            return Integer.parseInt(o1 + o2) - Integer.parseInt(o2 + o1);
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i = target.length - 1; i >= 0; i--) {
            sb.append(target[i]);
        }
        
        return sb.toString();
    }
}