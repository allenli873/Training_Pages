/*
ID: ***
LANG: JAVA
PROB: agrinet
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class agrinet { //minimum spanning tree
	private static int INF = Integer.MAX_VALUE / 2;
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] conMat = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			int j = 0;
			while(j < n) {
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					conMat[i][j] = Integer.parseInt(st.nextToken());
					j++;
				}
			}
		}
		int[] dist = new int[n];
		boolean[] inTree = new boolean[n];
		int treeSize = 1;
		int treeCost = 0;
		inTree[0] = true;
		Arrays.fill(dist, INF);
		for(int i = 1; i < n; i++)
			dist[i] = conMat[0][i];
		while(treeSize < n) {
			int minNode = -1;
			int curr = INF;
			for(int i = 0; i < n; i++) {
				if(dist[i] < curr && !inTree[i]) {
					curr = dist[i];
					minNode = i;
				}
			}
			inTree[minNode] = true;
			treeSize++;
			treeCost += dist[minNode];
			for(int j = 0; j < n; j++) 
				if(dist[j] > conMat[minNode][j]) 
					dist[j] = conMat[minNode][j];
		}
		out.println(treeCost);
		out.close();
	}
}
