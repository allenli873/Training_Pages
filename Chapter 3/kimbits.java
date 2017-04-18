/*
ID: ***
LANG: JAVA
PROB: kimbits
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class kimbits {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		long in = Long.parseLong(st.nextToken());
		System.out.println(fact(5, 3));
		String answer = ans("", n, l, in);
		while(answer.length() < n) {
			answer += "0";
		}
		out.println(answer);
		out.close();
		
	}
	public static String ans(String str, int n, int l, long in) {
		if(n == 0 || l == 0)
			return "";
//		System.out.println(n + "," + l);
		long num = numStrings(n - 1, l);
		if(num < in) {//digit will be 1
//			System.out.println(num);
			return "1" + ans(str, n - 1, l - 1, in - num);
		}
		else
			return "0" + ans(str, n - 1, l, in);
	}
	
	public static long numStrings(int n, int l) {
		//this method uses combinations to find the total # of strings in a given n & l value.
		if(n == 0)
			return 1;
		System.out.println(n + "," + l);
		long ans = 0;
		for(int i = 0; i <= l; i++)
			ans += Long.parseLong(combinations(n, i).toString());
		return ans;
	}
	
	public static BigInteger combinations(int n, int k) {
	    BigInteger num = BigInteger.ONE;
	    for (int i = 0; i < k; i++) {
	        num = num.multiply(BigInteger.valueOf(n-i)).divide(BigInteger.valueOf(i+1));
	    }
	    return num;
	}
	
	public static long fact(int num) {
		if(num <= 0)
			return 1;
		return num * fact(num - 1);
	}
	public static long fact(int num, int dest) {
		if(num <= dest)
			return 1;
		return num * fact(num - 1, dest);
	}
}
