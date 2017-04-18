/*
ID: ***
LANG: JAVA
PROB: humble
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class humble {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] primes = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++)
			primes[i] = Integer.parseInt(st.nextToken());
		int[] humbles = new int[n + 1];
		humbles[0] = 1;
		int[] primeIndex = new int[k];
		int nextNum = Integer.MAX_VALUE;
		boolean[] visited = new boolean[k];
		int currIndex = 1;
		while(currIndex < humbles.length) {
			nextNum = Integer.MAX_VALUE;
			for(int i = 0; i < k; i++) {
				if(primes[i] * humbles[primeIndex[i]] < nextNum) {
					Arrays.fill(visited, false);
					visited[i] = true;
					nextNum = primes[i] * humbles[primeIndex[i]];
				} else if(primes[i] * humbles[primeIndex[i]] == nextNum) {
					visited[i] = true;
				}
			}
			for(int i = 0; i < k; i++) {
				if(visited[i]) {
					primeIndex[i]++;
				}
			}
			humbles[currIndex] = nextNum;
			currIndex++;
		}
		out.println(humbles[n]);
		out.close();
	}
}
