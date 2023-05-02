import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    
    private int bfs(String word, String targetWord, String[] words) {
        Queue<ChangableWord> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        queue.offer(new ChangableWord(word, 0));
        
        while(!queue.isEmpty()) {
            ChangableWord source = queue.poll();
            for(int i = 0; i < words.length; i++) {
                String changeTarget = words[i];
                if(isChangable(source.value, changeTarget)) {
                    if(changeTarget.equals(targetWord)) return source.count + 1;
                    if(!visited[i]) {
                        queue.offer(new ChangableWord(changeTarget, source.count + 1));
                        visited[i] = true;
                    }
                }
            }
        }
        
        return 0;
    }
    
    private boolean isChangable(String source, String changeTarget) {
        int count = 0;
        String[] sourceArr = source.split("");
        String[] targetArr = changeTarget.split("");
        for(int i = 0; i < source.length(); i++) {
            if(sourceArr[i].equals(targetArr[i])) count++;            
        }
        
        return count == (source.length() - 1);
    }
    
    class ChangableWord {
        
        public String value;
        public int count;
        
        public ChangableWord(String value, int count) {
            this.value = value;
            this.count = count;
        }    
    }
}