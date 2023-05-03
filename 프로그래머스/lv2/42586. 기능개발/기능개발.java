import java.util.*;

/*
task {
   -progress
   -speed
   work : void
   nowProgress : int
   isDeployable : boolean
}
*/

class Task {
    private int progress;
    private int speed;

    public Task(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }
    
    public void doWork() {
        progress += speed;
    }
    
    public boolean isDeployable() {
        return progress >= 100;
    }
}


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Task> taskQueue = buildTaskQueue(progresses, speeds);
        List<Integer> answer = new ArrayList<>();
        int completedOfDay = 0;
        int totalTaskCount = progresses.length;
        
        while(totalTaskCount > 0) {
            for(Task t : taskQueue) {
                t.doWork();
            }
            
            while(taskQueue.peek().isDeployable()) {
                completedOfDay++;
                taskQueue.poll();
                if(taskQueue.isEmpty()) break;
            }
            answer.add(completedOfDay);
            totalTaskCount -= completedOfDay;
            completedOfDay = 0;
        }
        
        return answer.stream()
            .filter(v -> v > 0)
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private Queue<Task> buildTaskQueue(int[] progresses, int[] speeds) {
        Queue<Task> taskQueue = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++) {
            taskQueue.offer(new Task(progresses[i], speeds[i]));
        }
        return taskQueue;
    }
}