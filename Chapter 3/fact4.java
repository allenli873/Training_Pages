/*
ID: ***
LANG: JAVA
PROB: fact4 
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class fact4 {
	final static int MOD = 1000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		int n = Integer.parseInt(br.readLine());
		int currNum = 1;
		int mul = 2;
		while(mul <= n) {
			currNum *= mul;
			mul++;
			currNum %= MOD;
			while(currNum % 10 == 0) {
				currNum /= 10;
			}
		}
		out.println(currNum % 10);
		out.close();
	}
}
