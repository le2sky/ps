import java.util.*;
/*
*/

class Process {
    public int priority;
    public int originIndex;
    public int proceedWhen;
    
    public Process(int priority, int originIndex) {
        this.priority = priority;
        this.originIndex = originIndex;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int turn = 1;
        Queue<Process> queue = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            queue.offer(new Process(priorities[i], i));
        }
        
        int pointer = priorities.length - 1;
        Arrays.sort(priorities);
        
        while(!queue.isEmpty()) {
            int max = priorities[pointer];
            Process now = queue.peek();
            if(now.priority == max) {
                queue.poll();
            } else {
                while(queue.peek().priority != max) {
                    queue.offer(queue.poll());
                }
                now = queue.poll();
            }
            now.proceedWhen = turn++;
            if(now.originIndex == location)
                return now.proceedWhen;
            
            priorities[pointer--] = 0;
        }
        
        return -1;
    }
}