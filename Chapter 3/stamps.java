/*
ID: ***
LANG: JAVA
PROB: stamps
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stamps {
	private final static int INF = Integer.MAX_VALUE / 2;
	public static void main(String[] args) throws IOException{
		double startTime = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] vals = new int[n];
		int index = 0;
		while(index < n) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 15; i++) {
				if(index == n)
					break;
				vals[index] = Integer.parseInt(st.nextToken());
				index++;
			}
		}
		Arrays.sort(vals);
//		boolean[] dp = new boolean[700000];
		
//		dp[0] = true;
//		for(int i = 1; i <= k; i++) {
//			for(int j = dp.length - 1; j >= 0; j--) {
//				if(dp[j] == true)
//					for(int l: vals) {
//						if(j + l < dp.length)
//							dp[j + l] = true;
//					}
//			}
//		}
//		for(int i = 0; i < dp.length; i++) {
//			if(dp[i] == false) {
//				out.println(i - 1);
//				break;
//			}
//		}
		int[] dp = new int[2000000];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for(int i = 0; i < dp.length; i++)
			for(int j = 0; j < vals.length; j++)
				if(i >= vals[j])
					dp[i] = Math.min(1 + dp[i - vals[j]], dp[i]);
		int ans = 0;
		for(int i = 0; i < dp.length; i++) {
			if(dp[i] > k) {
				ans = i - 1;
				break;
			}
		}
		out.println(ans);
		out.close();

	}

}
