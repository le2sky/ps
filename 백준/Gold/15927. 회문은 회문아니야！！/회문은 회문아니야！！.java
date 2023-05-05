import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        System.out.println(solution(str));
    }

    private static int solution(String str) {
        if (isAllSameWord(str)) return -1;
        if (isPalindrome(str)) return str.length() - 1;
        return str.length();
    }

    private static boolean isPalindrome(String target) {
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList(target.split("")));
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return target.equals(sb.toString());
    }

    private static boolean isAllSameWord(String target) {
        Set<String> set = new HashSet<>(Arrays.asList(target.split("")));
        return set.size() == 1;
    }
}