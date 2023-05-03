/*
stack = [1(1), 2(2), 4(2), 5(3)]

처음부터 살아있으면 numbers - index가 성립
1(1) = 4(numbers - index);
2(2) = 3(numbers - index);
4(2) = 1(numbers - index);
5(3) = 0(numbers - index);
*/
import java.util.*;

class Event {
    public int sec;
    public int price;
    
    public Event(int sec, int price) {
        this.sec = sec;
        this.price = price;
    }
    
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Event> stack = new Stack<>();
        for(int i = 0; i < prices.length; i++) {
            int price = prices[i];
            while(!stack.isEmpty() && stack.peek().price > price) {
                Event top = stack.pop();
                answer[top.sec] = i - top.sec;
            }
            stack.push(new Event(i, price));
        }
        
        for(Event e : stack) {
            answer[e.sec] = prices.length - (e.sec + 1);
        }
        return answer;
    }
}