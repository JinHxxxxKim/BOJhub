import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int n, m, k;
    static List<Edge>[] adList;
    static List<Integer>[] dist;
    
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        adList = new ArrayList[n + 1];
        dist = new ArrayList[n + 1];
        for(int idx = 1; idx <= n; ++idx){
            adList[idx] = new ArrayList<>();
            dist[idx] = new ArrayList<>();
        }
        
        for(int cnt = 0; cnt < m; ++cnt){
            st = new StringTokenizer(br.readLine().trim());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adList[src].add(new Edge(dest, cost));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int currNum = curr.num;
            int currCost = curr.cost;
            
            if(dist[currNum].size() >= k){
                continue;
            }
            
            dist[currNum].add(currCost);
            
            for(Edge next : adList[currNum]){
                if(dist[next.num].size() >= k){
                    continue;
                }
                pq.offer(new Edge(next.num, currCost + next.cost));    
            }
        }
        for(int idx = 1; idx <= n; ++idx){
            if(dist[idx].size() < k){
                sb.append(-1).append("\n");
            }else{
                sb.append(dist[idx].get(k - 1)).append("\n");    
            }
        }
        System.out.println(sb);
    }
    
    static class Edge implements Comparable<Edge> {
        int num, cost;
        public Edge(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
}