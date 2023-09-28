import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

public class Main{
	private static int V, E, K, startVertex;
	private static ArrayList<Integer> ans;
	private static ArrayList<Node>[] map;
	private static boolean[] visited;
	private static int[] d;

	
	public static void main(String[] agrs) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		startVertex = Integer.parseInt(st.nextToken());
		ans = new ArrayList<Integer>();
		map = new ArrayList[V+1];
		visited = new boolean[V+1];
		d = new int[V+1];
		for(int i=1;i<=V;i++)
			map[i] = new ArrayList<Main.Node>();
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), 1));
		}
		Arrays.fill(d, Integer.MAX_VALUE);
		dijkstra(startVertex);
		Collections.sort(ans);
		if(ans.size()==0) {
			System.out.println(-1);
			return;
		}else {
			for(int i=0;i<ans.size();i++)
				System.out.println(ans.get(i));
		}
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(start, 0));
		d[start] = 0;
		while(!q.isEmpty()) {
			Node curr = q.poll();
			if(visited[curr.nodeNum])
				continue;
			visited[curr.nodeNum] = true;
			if(curr.dist==K)
				ans.add(curr.nodeNum);
			for(Node n:map[curr.nodeNum]) {
				int dist = curr.dist + n.dist;
				if(dist<d[n.nodeNum]) {
					d[n.nodeNum] = dist;
					q.add(new Node(n.nodeNum, dist));
				}
			}
		}
	}
	
	private static class Node implements Comparable<Node>{
		int nodeNum;
		int dist;
		public Node(int n, int w) {
			this.nodeNum = n;
			this.dist = w;
		}
		@Override
		public int compareTo(Main.Node o) {
			return dist - o.dist;
		}
		
	}
}