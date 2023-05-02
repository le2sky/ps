import java.util.*;

class Solution {
    HashMap<String, Integer> visited = new HashMap<>();
    HashMap<String, ArrayList<String>> graph = new HashMap<>();
    ArrayList<ArrayList<String>> list = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        //from+to : count
        for(String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            graph.put(from, graph.getOrDefault(from, new ArrayList<>()));
            graph.get(from).add(to);
            String key = from + to;
            visited.put(key, visited.getOrDefault(key, 0) + 1);
        }
        
        for(Map.Entry<String, ArrayList<String>> entry : graph.entrySet()) {
            ArrayList<String> list = entry.getValue();
            Collections.sort(list);
        }
        
        dfs("ICN", new ArrayList<>(List.of("ICN")), 1, tickets.length + 1);
        
        return list.get(0).toArray(new String[0]);
    }
    
    private void dfs(String from, ArrayList<String> paths, int depth, int targetSize) {
        //탈출 조건 1. targetSize랑 같아야함 
        if(depth == targetSize) {
            list.add(paths);
            return;
        }
        ArrayList<String> nodes = graph.get(from);
        //nodes가 null일 수도 있음
        if(nodes == null) return;
        for(String to : nodes) {
            if(visited.get(from + to) <= 0) continue;
            visited.put(from + to, visited.get(from + to) - 1);
            //경로를 추가
            ArrayList<String> temp = new ArrayList<>(paths);
            temp.add(to);
            dfs(to, temp, depth + 1, targetSize);
            visited.put(from + to, visited.get(from + to) + 1);
        }
    } 
    
}
