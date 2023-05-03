import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> ready = new LinkedList<>();
        
        for(int truck : truck_weights) {
            ready.offer(truck);    
        }
        
        for(int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        
        int answer = 0;
        int nowWeight = 0;
        int nowCount = 0;
        int rest = truck_weights.length;
        
        while(rest != 0) {
           answer++;
           int now = bridge.poll();
           if(now > 0) {
               nowWeight -= now;
               nowCount--;
               if(--rest == 0) break;
           }
            
           Integer truck = ready.peek();
           if(truck != null && nowWeight + truck <= weight && nowCount + 1 <= bridge_length) {
              bridge.offer(ready.poll());
              nowWeight += truck;
              nowCount++;
           } else {
              bridge.offer(0);
           }
        }
        return answer;
    }
}