/*
ID: ***
LANG: JAVA
PROB: ratios
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ratios {
	final static int INF = Integer.MAX_VALUE / 2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		State[] arr = new State[4]; /*0 is required state
									, 1, 2, and 3 
									are the mixtures*/
		for(int i = 0; i <= 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new State(0, 0, 0);
			arr[i].x = Integer.parseInt(st.nextToken());
			arr[i].y = Integer.parseInt(st.nextToken());
			arr[i].z = Integer.parseInt(st.nextToken());
		}
		int bestSum = INF;
		String str = "";
		//O(n^3) complete search
		State s = new State(0, 0, 0);
		for(int i = 0; i <= 100; i++) {
//			s.x = arr[1].x * i;
//			s.y = arr[1].y * i;
//			s.z = arr[1].z * i;
			for(int j = 0; j <= 100; j++) {
//				s.x += arr[2].x * j;
//				s.y += arr[2].y * j;
//				s.z += arr[2].z * j;
				for(int k = 0; k <= 100; k++) {
//					s.x += arr[3].x * k;
//					s.y += arr[3].y * k;
//					s.z += arr[3].z * k;
					
					s = arr[1].multiplies(i);
					s = s.add(arr[2].multiplies(j));
					s = s.add(arr[3].multiplies(k));
					
					if(s.f(arr[0]) > 0 && s.x + s.y + s.z < bestSum) {
						bestSum = s.x + s.y + s.z;
						str = i + " " + j + " " + k + " " + s.f(arr[0]);
					}
					
				}
			}
		}
		if(str.length() == 0)
			out.println("NONE");
		else
			out.println(str);
		out.close();
		
	}
	
	// return a value of c such that a = c*b
	// or 0 otherwise
	public static int f(int[] a, int[] b) {
		for (int i = 0; i < 3; i++)
			if (b[i] != 0 && a[i] % b[i] != 0)
				return 0;
		
		int c = 0;
		for (int i = 0; i < 3; i++)
			if (b[i] != 0)
				c = a[i] / b[i];
		
		for (int i = 0; i < 3; i++)
			if (a[i] != c*b[i])
				return 0;
		
		return c;
	}
	
	static class State {
		int x, y, z;
		public State(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		public int f(State s) {
			if(s.x != 0 && x % s.x != 0)
				return 0;
			if(s.y != 0 && y % s.y != 0)
				return 0;
			if(s.z != 0 && z % s.z != 0)
				return 0;
			int c = 0;
			if(s.x != 0)
				c = x / s.x;
			if(s.y != 0)
				c = y / s.y;
			if(s.z != 0)
				c = z / s.z;
			if(x != c * s.x || y != c * s.y || z != c * s.z)
				return 0;
			return c;
		}
//		public boolean equals(State s) {
//			if(!(s.x == 0 || s.y == 0 || s.z == 0) && x % s.x == 0 && y % s.y == 0 && z % s.z == 0)
//				return ((x / s.x == y / s.y) && y / s.y == z/s.z);
//			if(s.x == 0 && s.y != 0 && s.z != 0)
//				return y/s.y == z/s.z;
//			
//				
//		}
		public State multiplies(int num) {
			return new State(x * num, y * num, z * num);
		}
		public State add(State s) {
			return new State(x + s.x, y + s.y, z + s.z);
		}
	}
}
