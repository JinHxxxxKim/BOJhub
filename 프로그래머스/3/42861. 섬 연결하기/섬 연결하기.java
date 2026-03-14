import java.util.*;

class Solution {
    
    private int[] root; // 각 노드가 속한 트리의 rootNode 번호
    
    public void union(int node1, int node2) {
        int node1Root = find(node1);
        int node2Root = find(node2);
        if(node1Root == node2Root) return;
        else{
            root[node1Root] = node2Root;
        }
    }
    
    public int find(int node) {
        if(root[node] == node) return node;
        else return find(root[node]);
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        root = new int[n];
        for(int idx = 0; idx < n; ++idx) {
            root[idx] = idx;
        }
        
        for(int[] cost : costs) {
            pq.offer(new Edge(cost[0], cost[1], cost[2]));
        }
        
        while(!pq.isEmpty()){
            Edge currEdge = pq.poll();
            int node1Root = find(currEdge.src);
            int node2Root = find(currEdge.dest);
            if(node1Root != node2Root){
                union(currEdge.src, currEdge.dest);    
                answer += currEdge.cost;
            }
        }
        
                                            
        return answer;
    }
    
    public static class Edge implements Comparable<Edge>{
        int src, dest;
        int cost;
        
        public Edge(int s, int d, int c) {
            this.src = s;
            this.dest = d;
            this.cost = c;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}