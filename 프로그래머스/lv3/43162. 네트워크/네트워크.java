import java.util.*;

class Solution {
    
    private ArrayList<Integer>[] graph;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        graph = new ArrayList[n];
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int now = 0;
        for(int[] edge : computers) {
            for(int targetNode = 0; targetNode < edge.length; targetNode++) {
                if(edge[targetNode] == 1 && targetNode != now) {
                    graph[now].add(targetNode);
                }
            }
            now++;
        }

        boolean[] visited = new boolean[n];       
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) {
                bfs(i, visited);
                answer++;
            }
        }
            
        return answer;
    }
    
    private void bfs(int node, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visited[node] = true;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            System.out.println(now);
            for(int next : graph[now]) {
                if(!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        
    }
}
