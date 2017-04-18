/*
ID: ***
LANG: JAVA
PROB: inflate
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class inflate {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] p = new int[n];
		int[] min = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			p[i] = Integer.parseInt(st.nextToken());
			min[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[2][m + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(min[i - 1] > j)
					dp[1][j] = dp[0][j];
				else
					dp[1][j] = Math.max(dp[0][j], dp[1][j - min[i - 1]] + p[i - 1]);
			}
			for(int j = 0; j <= m; j++) {
				dp[0][j] = dp[1][j];
			}
		}
		out.println(dp[1][m]);
		out.close();
	}
}
