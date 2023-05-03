import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for(int i : arr) {
            if(stack.isEmpty())
                stack.push(i);
            else {
                int top = stack.peek();
                if(top != i) stack.push(i);
            }
        }

        return Arrays.stream(stack.toArray(new Integer[0]))
            .mapToInt(Integer::intValue)
            .toArray();
    }
}