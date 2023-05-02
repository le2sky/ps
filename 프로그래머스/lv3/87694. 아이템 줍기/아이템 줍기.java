import java.util.*;

/*
설계
1. 사각형을 그리고, 색칠까지 해야한다.
2. 모든 사각형을 그리면서, 덧칠해야 한다.
3. 탐색을 돌면서, 양갈래인 경우, cost가 더 낮은 방향(직사각형 내부로 들어가면 x)

예외
입출력 예제 1번에서
(y5,x3)에서 (y6,x3)을 보면, 바로 갈 수 없는 길인데도 불구하고,
동서남북으로 돌면 문제가 생김

-> 좌표를 2배!

변수
직사각형을 나타내는 모든 좌표값은 1 이상 50 이하인 자연수
*/

class Solution {
    private int[][] map = new int[102][102];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        for(int[] rect : rectangle) {
            int lx = rect[0] * 2;
            int ly = 102 - (rect[1] * 2);
            int rx = rect[2] * 2;
            int ry = 102 - (rect[3] * 2);
            
            for(int i = lx; i <= rx; i++) {
                map[ly][i]++;
            }
            for(int i = ly - 1; i >= ry; i--) {
                map[i][rx]++;
            }
            for(int i = rx - 1; i >= lx; i--) {
                map[ry][i]++;
            }
            for(int i = ry + 1; i < ly; i++) {
                map[i][lx]++;
                for(int j = lx + 1; j < rx; j++) {
                    map[i][j] += 100;
                }
            }
        }
        
        return bfs(new int[]{102 - (characterY * 2), characterX * 2}, 
                   new int[]{102 - (itemY * 2), itemX * 2}) / 2;
    }
    
    private int bfs(int[] start, int[] target) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start[0], start[1], 0));
        boolean[][] visited = new boolean[102][102];
        
        visited[start[0]][start[1]] = true;
        
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for(int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if(ny == target[0] && nx == target[1]) {
                    return node.count + 1;
                }
                if(ny < 0 || nx < 0 || ny >= 102 || nx >= 102) continue;
                if(visited[ny][nx] || map[ny][nx] == 0) continue;
                if(map[ny][nx] < 100) {
                    queue.offer(new Node(ny, nx, node.count + 1));
                    visited[ny][nx] = true;
                }
            }
        }
        
        return -1;
    }
    
    class Node {
        public int y;
        public int x;
        public int count;
        public Node(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
}