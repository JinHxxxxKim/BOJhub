import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Stock implements Comparable<Stock>{
        int day, price;

        public Stock(int day, int price) {
            this.day = day;
            this.price = price;
        }

        @Override
        public int compareTo(Stock stock) {
            return (-1) * (this.price - stock.price);
        }
    }

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        int N;
        int[] prices;

        for (int testCase = 0; testCase < TC; ++testCase) {
            N = Integer.parseInt(br.readLine().trim());
            prices = new int[N];
            long sum = 0;
            int minDay = -1;
            st = new StringTokenizer(br.readLine().trim());
            PriorityQueue<Stock> pq = new PriorityQueue<>();
            for (int idx = 0; idx < N; ++idx) {
                prices[idx] = Integer.parseInt(st.nextToken());
                pq.offer(new Stock(idx, prices[idx]));
            }

            while (!pq.isEmpty()) {
                Stock stock = pq.poll();
                for (int idx = minDay + 1; idx < stock.day; ++idx) {
                    sum += stock.price - prices[idx];
                }
                if (minDay < stock.day) {
                    minDay = stock.day;
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }

}

