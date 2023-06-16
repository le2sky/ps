import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int r, c, t;
    private static final String[] clockWise = {"plus", "plus", "minus", "minus"};
    private static final String[] counterClockwise = {"plus", "minus", "minus", "plus"};
    private static int[][] map;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        setRct(bf);
        setMap(bf);
        solution();
    }

    private static void solution() {
        List<List<List<Integer>>> pathPos = new ArrayList<>();
        List<List<Integer>> machinePos = new ArrayList<>();
        Stack<List<Integer>> finedust = new Stack<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) finedust.add(List.of(i, j));
                else if (map[i][j] == -1) machinePos.add(List.of(i, j));
            }
        }

        pathPos.add(getPath(machinePos.get(0), counterClockwise, true));
        pathPos.add(getPath(machinePos.get(1), clockWise, false));

        for (int i = 0; i < t; i++) {
            diffusion(finedust);
            cycle(pathPos);
            finedust = getFinedust();
        }

        int cnt = 2;
        for (int[] ints : map) {
            for (int anInt : ints) {
                cnt += anInt;
            }
        }

        System.out.println(cnt);
    }

    private static Stack<List<Integer>> getFinedust() {
        Stack<List<Integer>> fineDust = new Stack<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) fineDust.add(List.of(i, j));
            }
        }

        return fineDust;
    }

    // 공기 청정기 동작
    private static void cycle(List<List<List<Integer>>> targetPos) {
        for (List<List<Integer>> targets : targetPos) {
            int prev = 0;
            for (List<Integer> xy : targets) {
                int x = xy.get(0);
                int y = xy.get(1);
                if (map[x][y] == -1) break;
                int temp = map[x][y];
                map[x][y] = prev;
                prev = temp;
            }
        }
    }

    //미세 먼지를 확산
    private static void diffusion(Stack<List<Integer>> finedust) {
        List<List<Number>> commits = new ArrayList<>();
        while (finedust.size() != 0) {
            List<Integer> xy = finedust.pop();
            int x = xy.get(0);
            int y = xy.get(1);
            List<List<Integer>> target = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                if (map[nx][ny] == -1) continue;

                target.add(List.of(nx, ny));
            }

            for (List<Integer> ij : target) {
                int i = ij.get(0);
                int j = ij.get(1);

                commits.add(List.of(i, j, map[x][y] / 5, 0));
            }

            commits.add(List.of(x, y, (map[x][y] / 5) * target.size(), 1));
        }

        for (List<Number> values : commits) {
            int tempX = (int) values.get(0);
            int tempY = (int) values.get(1);
            int newValue = (int) values.get(2);
            int op = (int) values.get(3);

            if (op == 1) {
                map[tempX][tempY] -= newValue;
            } else map[tempX][tempY] += newValue;
        }
    }

    //시계, 반시계 방향 명령어 셋을 넣어서 지정된 경로를 반환
    private static List<List<Integer>> getPath(List<Integer> xy, String[] directions, boolean isFirst) {
        int x = xy.get(0);
        int y = xy.get(1);

        Map<String, Integer> alpha = new HashMap<>();
        alpha.put("plus", 1);
        alpha.put("minus", -1);

        int min, row, col;
        if (isFirst) {
            row = x;
            min = 0;
        } else {
            min = x;
            row = r - 1;
        }
        col = c - 1;
        y += 1;

        boolean isVertical = false;
        int index = 0;
        String nowDir = directions[index];
        List<List<Integer>> returnValue = new ArrayList<>();
        while (true) {
            returnValue.add(List.of(x, y));
            if (map[x][y] == -1) break;

            if ((isVertical && (x == min || x == row)) || (!isVertical && (y == 0 || y == col))) {

                nowDir = directions[++index];
                isVertical = !isVertical;

                if (isVertical)
                    x += alpha.get(nowDir);
                else
                    y += alpha.get(nowDir);

                continue;
            }

            if (isVertical)
                x += alpha.get(nowDir);
            else
                y += alpha.get(nowDir);
        }

        return returnValue;
    }

    private static void setMap(BufferedReader bf) throws IOException {
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            int[] row = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            map[i] = row;
        }
    }

    private static void setRct(BufferedReader bf) throws IOException {
        int[] rct = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        r = rct[0];
        c = rct[1];
        t = rct[2];
    }
}