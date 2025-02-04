import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static long src, target;
    static int ans = Integer.MAX_VALUE;
    static Queue<Node> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        src = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        // SOLUTION
        q.offer(new Node(src, 0));
        while (!q.isEmpty()) {
            Node currNode = q.poll();

            long currNum = currNode.num;
            int currCnt = currNode.cnt;
//            System.out.println("currNum = " + currNum);
            if(currNum == target){
                ans = currCnt;
                break;
            }
            // #1 (*2)
            if(currNum * 2 <= target){
                q.offer(new Node(currNum * 2, currCnt + 1));
            }
            // #2 (_1)
            long testNum = currNum * 10 + 1;
            if(testNum <= target){
                q.offer(new Node(testNum, currCnt + 1));
            }

        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else{
            System.out.println(ans + 1);
        }

    }
    static class Node {
        long num;
        int cnt;
        public Node(long num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }
}
