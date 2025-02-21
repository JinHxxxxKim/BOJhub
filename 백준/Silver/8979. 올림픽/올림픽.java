import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K;

    static class Node implements Comparable<Node> {
        int num;
        int g, s, b;

        public Node(int num, int g, int s, int b) {
            this.num = num;
            this.g = g;
            this.s = s;
            this.b = b;
        }

        @Override
        public int compareTo(Node node) {
            if (this.g == node.g) {
                if (this.s == node.s) {
                    return node.b - this.b;
                } else {
                    return node.s - this.s;
                }
            } else {
                return node.g - this.g;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", g=" + g +
                    ", s=" + s +
                    ", b=" + b +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Node[] arr = new Node[N];
        for (int idx = 0; idx < N; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            arr[idx] = new Node(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);
        int realRank = 0;
        int rank = 0;
        int beforeGold = Integer.MAX_VALUE;
        int beforeSilver = Integer.MAX_VALUE;
        int beforeBronze = Integer.MAX_VALUE;

        for(int idx = 0; idx < N; ++idx) {
            int currGold = arr[idx].g;
            int currSilver = arr[idx].s;
            int currBronze = arr[idx].b;
            if (currGold == beforeGold && currSilver == beforeSilver && currBronze == beforeBronze) {
                realRank++;
            }else{
                realRank++;
                rank = realRank;
            }

            beforeGold = currGold;
            beforeSilver = currSilver;
            beforeBronze = currBronze;

            if (arr[idx].num == K) {
                System.out.println(rank);
                break;
            }
        }
    }
}
