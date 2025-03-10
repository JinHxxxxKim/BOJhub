import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Node implements Comparable<Node> {

        int x, h;

        public Node(int x, int h) {
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo(Node o) {
            return this.x - o.x;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", h=" + h +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        Node[] nodes = new Node[N];
        for (int idx = 0; idx < N; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            nodes[idx] = new Node(x, h);
        }

        Arrays.sort(nodes);
//        System.out.println(Arrays.toString(nodes));
        int maxHeightX = -1;
        int maxHeight = -1;
        for (Node node : nodes) {
            if (maxHeight < node.h) {
                maxHeight = node.h;
                maxHeightX = node.x;
            }
        }

        int sum = 0;
        int idx = 0;
        int currX = nodes[0].x;
        int currH = nodes[0].h;
        // 왼쪽에서부터 증가
        while (currX < maxHeightX) {
            ++idx;
            int tempX = nodes[idx].x;
            int tempH = nodes[idx].h;
            if (tempX == maxHeightX) {
                sum += (tempX - currX) * currH;
                break;
            }
            if (tempH < currH) {
                continue;
            }
            sum += (tempX - currX) * currH;
            currX = tempX;
            currH = tempH;
        }

        idx = N - 1;
        currX = nodes[N - 1].x;
        currH = nodes[N - 1].h;
        // 오른쪽에서부터 증가
        while (currX > maxHeightX) {
            --idx;
            int tempX = nodes[idx].x;
            int tempH = nodes[idx].h;
            if (tempX == maxHeightX) {
                sum += (currX - tempX) * currH;
                break;
            }
            if (tempH < currH) {
                continue;
            }
            sum += (currX - tempX) * currH;
            currX = tempX;
            currH = tempH;
        }

        System.out.println(sum + maxHeight);
    }
}