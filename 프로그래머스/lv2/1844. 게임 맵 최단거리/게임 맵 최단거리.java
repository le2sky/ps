import java.util.*;

class Solution {
    
    private int[] dx = new int[] {-1, 1, 0, 0};
    private int[] dy = new int[] {0, 0, -1, 1};
    private int[][] maps;
    
    public int solution(int[][] maps) {
        int answer = 0;
        this.maps = maps;
        
        return bfs();
    }
    
    private int bfs() {
        int row = maps.length;
        int col = maps[0].length;
        boolean[][] visited = new boolean[row][col];
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0, 1));
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            Position p = queue.poll();
            int x = p.row;
            int y = p.col;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= row || ny >= col) {
                    continue;
                }
                
                if(maps[nx][ny] == 0 || visited[nx][ny])
                    continue;

                if(nx == row - 1 && ny == col - 1)
                    return p.count + 1;
                
                queue.offer(new Position(nx, ny, p.count + 1));
                visited[nx][ny] = true;
            }
        }
        
        return -1;
    }
    
}


class Position {
    public int row;
    public int col;
    public int count;
    
    public Position(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }
}