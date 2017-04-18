/*
ID: ***
LANG: JAVA
PROB: butter
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class butter {
	public final static int INF = Integer.MAX_VALUE / 2;
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		@SuppressWarnings("unchecked")
		List<Pair> adjList[] = new ArrayList[p + 1];
		for(int i = 0; i <= p; i++)
			adjList[i] = new ArrayList<Pair>();
		
		int[] cowLoc = new int[n + 1];
		for(int i = 1; i <= n; i++)
			cowLoc[i] = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			adjList[a].add(new Pair(b, dist));
			adjList[b].add(new Pair(a, dist));
		}
		//do dijkstra's n times
		int[][] dist = new int[n][p + 1];
		boolean[][] visited = new boolean[n][p + 1];
		for(int[] i: dist)
			Arrays.fill(i, INF);
		for(int i = 0; i < n; i++)
			dist[i][cowLoc[i + 1]] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		for(int i = 0; i < n; i++) {
			pq.add(new Pair(cowLoc[i + 1], 0));
			while(!pq.isEmpty()) {
				int vertex = pq.poll().vertex;
				if(visited[i][vertex])
					continue;
				visited[i][vertex] = true;
				for(Pair pair: adjList[vertex]) {
					int v = pair.vertex;
					int w = pair.dist;
					if(dist[i][v] > dist[i][vertex] + w) {
						dist[i][v] = dist[i][vertex] + w;
						pq.add(new Pair(v, dist[i][v]));
					}
				}
			}
		}
		int ans = INF;
		for(int i = 1; i <= p; i++) {
			int num = 0;
			for(int j = 0; j < n; j++) {
				num += dist[j][i];
			}
			ans = Math.min(ans, num);
		}
		out.println(ans);
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		public int vertex, dist;
		public Pair(int vertex, int dist) {
			this.vertex = vertex;
			this.dist = dist;
		}
		public int compareTo(Pair p) {
			return Integer.compare(dist, p.dist);
		}
		public String toString() {
			return vertex + "," + dist;
		}
	}
}
